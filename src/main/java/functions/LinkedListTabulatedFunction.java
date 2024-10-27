package functions;

public class LinkedListTabulatedFunction extends AbstractTabulatedFunction {
    private Node head;
    private int count = 0;

    static final class Node {
        public Node next;
        public Node prev;
        public double x;
        public double y;

        public Node(double x, double y) {
            this.x = x;
            this.y = y;
        }
        @Override
        public String toString() {
            return "("+ x+ "; " + y+ ")";
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Node node = (Node) o;

            return Double.compare(node.x, x) == 0 && Double.compare(node.y, y) == 0;
        }

        @Override
        public int hashCode() {
            int result = 17;
            long xBits = Double.doubleToLongBits(x);
            long yBits = Double.doubleToLongBits(y);
            result = 31 * result + (int) (xBits ^ (xBits >>> 32));
            result = 31 * result + (int) (yBits ^ (yBits >>> 32));
            return result;
        }

        @Override
        public Object clone() {
            return new Node(this.x, this.y);
        }
    }

    protected void addNode(double x, double y) {
        Node p = new Node(x, y);
        if (head != null) {
            Node last = head.prev;
            p.prev = last;
            p.next = head;
            last.next = p;
            head.prev = p;
        } else {
            head = p;
            head.next = head;
            head.prev = head;
        }
        count++;
    }

    public LinkedListTabulatedFunction(double[] xValues, double[] yValues) {
        for (int i = 0; i < xValues.length; i++) {
            addNode(xValues[i], yValues[i]);
        }
    }

    public LinkedListTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double temp = xFrom;
            xFrom = xTo;
            xTo = temp;
        }
        if (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                addNode(xFrom, source.apply(xFrom));
            }
        }
        else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                addNode(xFrom + i * step, source.apply(xFrom + i * step));
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    private Node getNode(int index) {
        if (index < count / 2) {
            Node current = head;
            for (int i = 0; i < index; i++) {
                current = current.next;
            }
            return current;
        } else {
            Node current = head.prev;
            for (int i = count - 1; i > index; i--) {
                current = current.prev;
            }
            return current;
        }
    }

    @Override
    public double getX(int index) {
        return getNode(index).x;
    }

    @Override
    public double getY(int index) {
        return getNode(index).y;
    }

    public void setY(int index, double value) {
        getNode(index).y = value;
    }

    @Override
    public double leftBound() {
        return head.x;
    }

    @Override
    public double rightBound() {
        return head.prev.x;
    }

    @Override
    public int indexOfX(double x) {
        Node current = head;
        for (int i = 0; i<count; i++){
            if (current.x == x){
                return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        Node current = head;
        for (int i = 0; i<count; i++){
            if (current.y == y){
                return i;
            }
            current = current.next;
        }
        return -1;
    }
    @Override
    protected int floorIndexOfX(double x) {
        if (x <= head.x) {
            return 0;
        }
        if (x >= head.prev.x) {
            return count - 1;
        }
        Node current = head;
        int index = 0;
        while (current != head.prev && current.x <= x) {
            current = current.next;
            ++index;
        }
        return index - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return interpolate(x, head.x, head.next.x, head.y, head.next.y);
    }

    @Override
    protected double extrapolateRight(double x) {
        return interpolate(x, head.prev.prev.x, head.prev.x, head.prev.prev.y, head.prev.y);
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        Node left = getNode(floorIndex);
        Node right = left.next;
        return interpolate(x, left.x, right.x, left.y, right.y);
    }
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = head;
        builder.append('[');
        for (int i = 0; i < count; i++) {
            builder.append(current.x)
                    .append(", ")
                    .append(current.y);
            if (i < count - 1) {
                builder.append("], [");
            }
            current = current.next;
        }
        builder.append(']');
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LinkedListTabulatedFunction that = (LinkedListTabulatedFunction) o;
        if (count != that.count) return false;
        Node thisNode = this.head;
        Node thatNode = that.head;
        for (int i = 0; i < count; i++) {
            if (Double.compare(thisNode.x, thatNode.x) != 0 ||
                    Double.compare(thisNode.y, thatNode.y) != 0) {
                return false;
            }
            thisNode = thisNode.next;
            thatNode = thatNode.next;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = 31 * count;
        Node current = head;
        for (int i = 0; i < count; i++) {
            long temp = Double.doubleToLongBits(current.x);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            temp = Double.doubleToLongBits(current.y);
            result = 31 * result + (int) (temp ^ (temp >>> 32));
            current = current.next;
        }
        return result;
    }

    @Override
    public Object clone() {
        double[] xValues = new double[count];
        double[] yValues = new double[count];
        Node current = head;
        for (int i = 0; i < count; i++) {
            xValues[i] = current.x;
            yValues[i] = current.y;
            current = current.next;
        }
        return new LinkedListTabulatedFunction(xValues, yValues);
    }
}
