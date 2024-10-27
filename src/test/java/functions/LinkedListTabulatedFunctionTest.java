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

    public void testExtrapolateRight() {
        double[] xValues = {1, 2, 3};
        double[] yValues = {4, 5, 6};
        LinkedListTabulatedFunction function = new LinkedListTabulatedFunction(xValues, yValues);
        assertEquals(7, function.extrapolateRight(4), 0.01);
        assertEquals(6.5, function.extrapolateRight(3.5), 0.01);
    }
    @Test
    public void testToString() {
        double[] xArray = {9, 6.7, 4.5, 10};
        double[] yArray = {8, 7, 2, 1.8};
        LinkedListTabulatedFunction linkedList = new LinkedListTabulatedFunction(xArray, yArray);

        String expectedString = "(9.0; 8.0); (6.7; 7.0); (4.5; 2.0); (10.0; 1.8)";
        assertEquals(expectedString, linkedList.toString());
    }
    @Test
    public void testEquals() {
        double[] xArray1 = {1, 2, 4.5, 10};
        double[] yArray1 = {0, 3, 2, 1.1};
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xArray1, yArray1);

        double[] xArray2 = {1, 2, 4.5, 10};
        double[] yArray2 = {0, 3, 2, 1.1};
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xArray2, yArray2);

        Assertions.assertTrue(linkedList1.equals(linkedList2));
    }
    @Test
    public void testNotEquals() {
        double[] xArray1 = {1, 2, 4.5, 10};
        double[] yArray1 = {0, 3, 2, 1.1};
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xArray1, yArray1);

        double[] xArray2 = {1, 3, 4.5, 10};
        double[] yArray2 = {0, 2, 2, 1.1};
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xArray2, yArray2);

        Assertions.assertFalse(linkedList1.equals(linkedList2));
    }
    @Test
    public void testHashCode() {
        double[] xArray = {1, 2, 4.5, 10};
        double[] yArray = {0, 3, 2, 1.1};
        LinkedListTabulatedFunction linkedList1 = new LinkedListTabulatedFunction(xArray, yArray);
        LinkedListTabulatedFunction linkedList2 = new LinkedListTabulatedFunction(xArray, yArray);
        assertEquals(linkedList1.hashCode(), linkedList2.hashCode());
    }
    @Test
    public void testClone() {
        double[] xArray = {1, 2, 4.5, 10};
        double[] yArray = {0, 3, 2, 1.1};
        LinkedListTabulatedFunction function1 = new LinkedListTabulatedFunction(xArray, yArray);
        LinkedListTabulatedFunction clonedFunction = (LinkedListTabulatedFunction) function1.clone();
        assertNotSame(function1, clonedFunction);
        assertTrue(function1.equals(clonedFunction));
        clonedFunction.setY(0, 99.0);
        assertNotEquals(function1.getY(0), clonedFunction.getY(0));
    }
}
