package functions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class LinkedListTabulatedFunctionTest {
    @Test
    public void testConstructorWithArrays() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(3, function.getCount());
        assertEquals(1, function.getX(0), 0.01);
        assertEquals(4, function.getY(0), 0.01);
    }

    @Test
    public void testConstructorWithFunction() {
        MathFunction source = x -> x * x;
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(source, 0, 10, 11);
        assertEquals(11, function.getCount());
        assertEquals(0, function.getX(0), 0.01);
        assertEquals(100, function.getY(10), 0.01);
    }

    @Test
    public void testGetCount() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(3, function.getCount());
    }

    @Test
    public void testGetX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(1, function.getX(0), 0.01);
        assertEquals(2, function.getX(1), 0.01);
        assertEquals(3, function.getX(2), 0.01);
    }

    @Test
    public void testGetY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(4, function.getY(0), 0.01);
        assertEquals(5, function.getY(1), 0.01);
        assertEquals(6, function.getY(2), 0.01);
    }

    @Test
    public void testLeftBound() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(1, function.leftBound(), 0.01);
    }

    @Test
    public void testRightBound() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(3, function.rightBound(), 0.01);
    }

    @Test
    public void testIndexOfX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(0, function.indexOfX(1));
        assertEquals(1, function.indexOfX(2));
        assertEquals(2, function.indexOfX(3));
        assertEquals(-1, function.indexOfX(4));
    }

    @Test
    public void testIndexOfY() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(0, function.indexOfY(4));
        assertEquals(1, function.indexOfY(5));
        assertEquals(2, function.indexOfY(6));
        assertEquals(-1, function.indexOfY(7));
    }

    @Test
    public void testFloorIndexOfX() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(0, function.floorIndexOfX(1));
        assertEquals(0, function.floorIndexOfX(1.5));
        assertEquals(1, function.floorIndexOfX(2.5));
        assertEquals(2, function.floorIndexOfX(3));
    }

    @Test
    public void testExtrapolateLeft() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(3, function.extrapolateLeft(0), 0.01);
        assertEquals(3.5, function.extrapolateLeft(0.5), 0.01);
    }
    @Test
    public void testExtrapolateRight() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(7, function.extrapolateRight(4), 0.01);
        assertEquals(6.5, function.extrapolateRight(3.5), 0.01);
    }
    @Test
    public void testToString() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);

        String expected = "[1.0, 2.0], [2.0, 4.0], [3.0, 6.0]";
        assertEquals(expected, function.toString());

        double[] xValues2 = {1.23, 2.45, 3.67};
        double[] yValues2 = {4.56, 5.78, 6.90};
        function = new LinkedListTabulatedFunction(xValues2, yValues2);

        String expected2 = "[1.23, 4.56], [2.45, 5.78], [3.67, 6.9]";
        assertEquals(expected2, function.toString());
    }
    @Test
    public void testEquals() {
        double[] xValues1 = {1.0, 2.0, 3.0};
        double[] yValues1 = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xValues1, yValues1);
        assertTrue(function1.equals(function1));
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues1, yValues1);
        assertTrue(function1.equals(function2));
        double[] yValues2 = {2.0, 4.0, 6.1};
        LinkedListTabulatedFunction function3 = new LinkedListTabulatedFunction(xValues1, yValues2);
        assertFalse(function1.equals(function3));
        double[] xValues3 = {1.0, 2.0};
        double[] yValues3 = {2.0, 4.0};
        LinkedListTabulatedFunction function4 = new LinkedListTabulatedFunction(xValues3, yValues3);
        assertFalse(function1.equals(function4));

        assertFalse(function1.equals(null));

        assertFalse(function1.equals("not a function"));
    }

    @Test
    public void testHashCode() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction function2 = new LinkedListTabulatedFunction(xValues, yValues);

        assertEquals(function1.hashCode(), function2.hashCode());

        double[] yValues2 = {2.0, 4.0, 6.1};
        LinkedListTabulatedFunction function3 = new LinkedListTabulatedFunction(xValues, yValues2);
        assertNotEquals(function1.hashCode(), function3.hashCode());
    }

    @Test
    public void testClone() {
        double[] xValues = {1.0, 2.0, 3.0};
        double[] yValues = {2.0, 4.0, 6.0};
        LinkedListTabulatedFunction original = new LinkedListTabulatedFunction(xValues, yValues);
        LinkedListTabulatedFunction clone = (LinkedListTabulatedFunction) original.clone();
        assertNotSame(original, clone);
        assertEquals(original, clone);
        clone.setY(0, 10.0);
        assertNotEquals(original.getY(0), clone.getY(0));
        assertEquals(2.0, original.getY(0));
        assertEquals(10.0, clone.getY(0));
    }
}
