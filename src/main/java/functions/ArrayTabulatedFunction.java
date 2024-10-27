package functions;

import java.util.Arrays;

public class ArrayTabulatedFunction extends AbstractTabulatedFunction {
    private double[] xValues;
    private double[] yValues;
    private int count;

    public ArrayTabulatedFunction(double[] xValues, double[] yValues) {
        this.count = xValues.length;
        this.xValues = Arrays.copyOf(xValues, xValues.length);
        this.yValues = Arrays.copyOf(yValues, yValues.length);
    }

    public ArrayTabulatedFunction(MathFunction source, double xFrom, double xTo, int count) {
        if (xFrom > xTo) {
            double current = xFrom;
            xFrom = xTo;
            xTo = current;
        }
        this.count = count;
        this.xValues = new double[count];
        this.yValues = new double[count];
        if (xFrom == xTo) {
            for (int i = 0; i < count; i++) {
                this.xValues[i] = xFrom;
                this.yValues[i] = source.apply(xFrom);
            }
        }
        else {
            double step = (xTo - xFrom) / (count - 1);
            for (int i = 0; i < count; i++) {
                this.xValues[i] = xFrom + i * step;
                this.yValues[i] = source.apply(this.xValues[i]);
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double getX(int ind) {
        return xValues[ind];
    }

    @Override
    public double getY(int ind) {
        return yValues[ind];
    }

    public void setY(int ind, double value) {
        yValues[ind] = value;
    }

    @Override
    public double leftBound() {
        return xValues[0];
    }

    @Override
    public double rightBound() {
        return xValues[count - 1];
    }

    @Override
    public int indexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] == x) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int indexOfY(double y) {
        for (int i = 0; i < count; i++) {
            if (yValues[i] == y) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected int floorIndexOfX(double x) {
        for (int i = 0; i < count; i++) {
            if (xValues[i] > x) {
                return i - 1;
            }
        }
        return count - 1;
    }

    @Override
    protected double extrapolateLeft(double x) {
        return yValues[0];

    }

    @Override
    protected double extrapolateRight(double x) {
        return yValues[count-1];
    }

    @Override
    protected double interpolate(double x, int floorIndex) {
        return interpolate(x, xValues[floorIndex], xValues[floorIndex + 1], yValues[floorIndex], yValues[floorIndex + 1]);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("ArrayTabulatedFunction:\n");
        for (int i = 0; i < count; i++) {
            builder.append("x: ").append(xValues[i]).append(", y: ").append(yValues[i]).append("\n");
        }
        return builder.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArrayTabulatedFunction)) return false;
        ArrayTabulatedFunction other = (ArrayTabulatedFunction) o;
        return Arrays.equals(this.xValues, other.xValues) && Arrays.equals(this.yValues, other.yValues);
    }
    @Override
    public int hashCode() {
        int result = Arrays.hashCode(xValues);
        result = 31 * result + Arrays.hashCode(yValues);
        return result;
    }
    @Override
    public ArrayTabulatedFunction clone() {
        ArrayTabulatedFunction clone = new ArrayTabulatedFunction(
                Arrays.copyOf(this.xValues, this.xValues.length),
                Arrays.copyOf(this.yValues, this.yValues.length)
        );
        return clone;
    }
}
