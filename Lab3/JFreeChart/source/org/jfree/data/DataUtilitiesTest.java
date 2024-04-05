package org.jfree.data;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {

	private Values2D values2D;
	private Values2D negativeNegativeValues2D; // Array of negative array, negative array
	private Values2D positiveNegativeValues2D; // Array of positive array, negative array
	private Values2D negativePositiveValues2D; // Array of negative array, positive array
	private Values2D emptyValues2D; // Array of empty arrays
	private Values2D negativeEmptyValues2D; // Array of negative array, empty array
	private Values2D emptyPositiveValues2D; // Array of empty array, positive array

	@Before
	public void setUp() throws Exception {
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		values2D = testValues;
		testValues.addValue(1, 0, 0);
		testValues.addValue(4, 1, 0);

		DefaultKeyedValues2D testValues1 = new DefaultKeyedValues2D();
		negativeNegativeValues2D = testValues1;
		testValues1.addValue(-2147483648, -3, -5);
		testValues1.addValue(-12, -11, -2);

		DefaultKeyedValues2D testValues2 = new DefaultKeyedValues2D();
		positiveNegativeValues2D = testValues2;
		testValues2.addValue(2147483647, 10, 5);
		testValues2.addValue(-10, -20, -89);

		DefaultKeyedValues2D testValues3 = new DefaultKeyedValues2D();
		negativePositiveValues2D = testValues3;
		testValues3.addValue(-1, -8, -12);
		testValues3.addValue(18, 22, 34);

		DefaultKeyedValues2D testValues4 = new DefaultKeyedValues2D();
		emptyValues2D = testValues4;

		DefaultKeyedValues2D testValues5 = new DefaultKeyedValues2D();
		negativeEmptyValues2D = testValues5;
		testValues5.addValue(-19, -22, -3);

		DefaultKeyedValues2D testValues6 = new DefaultKeyedValues2D();
		emptyPositiveValues2D = testValues6;
		testValues6.addValue(10, 11, 22);

	}

	@After
	public void tearDown() throws Exception {
		values2D = null;
		negativeNegativeValues2D = null;
		positiveNegativeValues2D = null;
		negativePositiveValues2D = null;
		emptyValues2D = null;
		negativeEmptyValues2D = null;
		emptyPositiveValues2D = null;

	}

	// calculateColumnTotal
	// TC1: Valid Data and Column - Verifies that the method correctly calculates
	// the sum of values in the first column of a valid data set.
	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0),
				0.0000001d);
	}

	// TC2: Null Data - Checks if the method throws an IllegalArgumentException when
	// null data is passed, ensuring robust error handling.
	@Test
	public void testNullDataColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC3: Negative Data - Tests the method's behavior with a data set containing
	// negative values, expecting correct sum calculation.
	@Test
	public void testNegativeDataColumnTotal() {
		assertEquals("Incorrect sum for negative values, underflow should result in 0.", 0.0,
				DataUtilities.calculateColumnTotal(negativeNegativeValues2D, 0), 0.0000001d);
	}

	// TC4: Positive and Negative Data - Verifies that the method correctly handles
	// a mixture of positive and negative values in the data set.
	@Test
	public void testPositiveNegativeDataColumnTotal() {
		assertEquals("The sum should be within the valid int range.", 2147483637.0,
				DataUtilities.calculateColumnTotal(positiveNegativeValues2D, 0), 0.0000001d);
	}

	// TC5: Negative and Positive Data - Ensures that the method accurately
	// calculates the sum of a column with both negative and positive values.
	@Test
	public void testNegativePositiveDataColumnTotal() {
		try {
			double result = DataUtilities.calculateColumnTotal(negativePositiveValues2D, 2);
			assertEquals("The sum of the third column should be 22.", 22.0, result, 0.0000001d);
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC6: Empty Arrays - Checks the method's response to being given an entirely
	// empty data set, expecting an IllegalArgumentException.
	@Test
	public void testEmptyArraysColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(emptyValues2D, 2);
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC7: Negative Empty Arrays - Similar to TC6 but with a specific focus on
	// validating the method's error handling for negative scenarios.
	@Test
	public void testNegativeEmptyArraysColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(negativeEmptyValues2D, 2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC8: Empty Positive Arrays - Tests how the method handles empty arrays among
	// positive data values, expecting robust error handling.
	@Test
	public void testEmptyPositiveArraysColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(emptyPositiveValues2D, 2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC9: Null Input - Verifies the method's error handling when provided with
	// null input for data, expecting an IllegalArgumentException.
	@Test
	public void testNullInputColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 3);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC10: Invalid Negative Column Index - Checks the method's handling of an
	// invalid negative column index, expecting a return value of 0.0.
	@Test
	public void testInvalidNegativeColumnIndexColumnTotal() {
		try {
			double result = DataUtilities.calculateColumnTotal(values2D, -19);
			assertEquals("Sum should be 0 for invalid negative column index.", 0.0, result, 0.0000001d);
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC11: Negative Column Index with Negative Values - Tests the method's
	// behavior when given a negative column index in a dataset with negative
	// values.
	@Test
	public void testNegativeColumnIndexWithNegativeValuesColumnTotal() {
		try {
			double result = DataUtilities.calculateColumnTotal(negativeNegativeValues2D, -2);
			assertEquals("Sum should be 0 for invalid negative column index with negative values.", 0.0, result,
					0.0000001d);
		} catch (Exception e) {
			fail("An unexpected exception was thrown for an invalid negative column index with negative values: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC12: Test with Single Row Data - Verifies correct calculation when data
	// contains only one row.
	@Test
	public void testSingleRowDataColumnTotal() {
		DefaultKeyedValues2D singleRowData = new DefaultKeyedValues2D();
		singleRowData.addValue(5.5, 0, 0); // Assuming 5.5 is the value in the only row and first column
		assertEquals("Incorrect sum for single row data.", 5.5, DataUtilities.calculateColumnTotal(singleRowData, 0),
				0.0000001d);
	}

	// TC13: Test with Large Numbers - Ensures the method can handle large values
	// without overflow.
	@Test
	public void testLargeNumbersColumnTotal() {
		DefaultKeyedValues2D largeNumbersData = new DefaultKeyedValues2D();
		largeNumbersData.addValue(Double.MAX_VALUE, 0, 0);
		largeNumbersData.addValue(Double.MAX_VALUE, 1, 0);
		assertEquals("Incorrect sum with large numbers, potential overflow.", Double.POSITIVE_INFINITY,
				DataUtilities.calculateColumnTotal(largeNumbersData, 0), 0.0);
	}

	// TC14: Test with Floating Point Numbers - Verifies that the method accurately
	// calculates the total with floating point numbers.
	@Test
	public void testFloatingPointNumbersColumnTotal() {
		DefaultKeyedValues2D floatValuesData = new DefaultKeyedValues2D();
		floatValuesData.addValue(3.14, 0, 0);
		floatValuesData.addValue(2.71, 1, 0);
		assertEquals("Incorrect sum for floating point numbers.", 5.85,
				DataUtilities.calculateColumnTotal(floatValuesData, 0), 0.0000001d);
	}

	// TC15: Test with Multiple Columns - Verifies the method accurately calculates
	// the total for a specified column, ignoring data in other columns.
	@Test
	public void testMultipleColumnsColumnTotal() {
		DefaultKeyedValues2D multiColumnData = new DefaultKeyedValues2D();
		multiColumnData.addValue(1, 0, 0); // First column
		multiColumnData.addValue(2, 0, 1); // Second column, should not be included in the total for the first column
		assertEquals("Incorrect sum when multiple columns are present.", 1.0,
				DataUtilities.calculateColumnTotal(multiColumnData, 0), 0.0000001d);
	}

	// TC16: Test with a Valid Column but No Data - Ensures the method returns 0.0
	// when a valid column index is specified but the column has no data.
	@Test
	public void testValidColumnNoDataColumnTotal() {
		DefaultKeyedValues2D noDataColumn = new DefaultKeyedValues2D();
		assertEquals("Incorrect sum for a valid column with no data.", 0.0,
				DataUtilities.calculateColumnTotal(noDataColumn, 0), 0.0000001d);
	}

	// calculateRowTotal

	// TC17: Test a valid row with only positive numbers. The expected result should
	// be the sum of those numbers.
	@Test
	public void testValidDataAndRowRowTotal() {
		assertEquals("The sum of positive numbers in row 0 should be 1.0", 1.0,
				DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}

	// TC18: Test a row with the largest negative number. This should result in the
	// sum of those values if it doesn't underflow.
	@Test
	public void testNegativeDataRowTotal() {
		assertEquals("The sum of the largest negative numbers in row 0 should be correctly calculated.", -2147483651.0,
				DataUtilities.calculateRowTotal(negativeNegativeValues2D, 0), 0.0000001d);
	}

	// TC19: Test a row with a mix of the largest positive and negative numbers to
	// ensure they are added correctly.
	@Test
	public void testPositiveNegativeDataRowTotal() {
		assertEquals("The sum of mixed positive and negative numbers in row 1 should be correctly calculated.", -129.0,
				DataUtilities.calculateRowTotal(positiveNegativeValues2D, 1), 0.0000001d);
	}

	// TC20: Test a row with mixed negative and positive numbers to ensure the sum
	// is calculated as expected.
	@Test
	public void testNegativePositiveDataRowTotal() {
		assertEquals("The sum of mixed negative and positive numbers in row 1 should be correctly calculated.", 74.0,
				DataUtilities.calculateRowTotal(negativePositiveValues2D, 1), 0.0000001d);
	}

	// TC21: Test empty arrays to confirm that an IllegalArgumentException is thrown
	// as per the method contract.
	@Test
	public void testEmptyArraysRowTotal() {
		try {
			DataUtilities.calculateRowTotal(emptyValues2D, 0);
			fail("An exception was expected due to the empty arrays, but none was thrown.");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC22: Test a row with negative values and an empty row to confirm that an
	// IllegalArgumentException is thrown.
	@Test
	public void testNegativeEmptyArraysRowTotal() {
		try {
			DataUtilities.calculateRowTotal(negativeEmptyValues2D, 1);
			fail("An exception was expected due to the negative values followed by an empty row, but none was thrown.");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC23: Testing with an empty row followed by a row with positive values.
	@Test
	public void testEmptyPositiveRowTotal() {
		try {
			DataUtilities.calculateRowTotal(emptyPositiveValues2D, 1);
			fail("No exception thrown. An IllegalArgumentException was expected due to an empty first row.");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC24: Testing with null data which should throw IllegalArgumentException.
	@Test
	public void testNullDataRowTotal() {
		try {
			DataUtilities.calculateRowTotal(null, 1);
			fail("No exception thrown. An IllegalArgumentException was expected due to null data.");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC25: Testing with a valid data row and an invalid negative row index.
	@Test
	public void testValidDataNegativeRowIndexRowTotal() {
		try {
			// A negative row index is expected to result in a return value of 0, as
			// specified by the implementation.
			double result = DataUtilities.calculateRowTotal(values2D, -19);
			assertEquals("The row total should be 0 for a negative row index.", 0.0, result, 0.0000001d);
		} catch (Exception e) {
			fail("An unexpected exception was thrown: " + e.getMessage());
		}
	}

	// TC26: Testing with a data row containing negative values and an invalid
	// negative row index.
	public void testNegativeRowIndexWithNegativeValuesRowTotal() {
		try {
			DataUtilities.calculateRowTotal(negativeNegativeValues2D, -2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e instanceof IllegalArgumentException);
		}
	}

	// TC27: Test with a row containing only zero values to verify correct
	// calculation.
	@Test
	public void testRowWithOnlyZeroValuesRowTotal() {
		DefaultKeyedValues2D zeroValuesData = new DefaultKeyedValues2D();
		zeroValuesData.addValue(0, 0, 0);
		zeroValuesData.addValue(0, 0, 1);
		assertEquals("The sum of a row with only zero values should be 0.0", 0.0,
				DataUtilities.calculateRowTotal(zeroValuesData, 0), 0.0000001d);
	}

	// TC28: Test with a row index out of bounds to verify handling of non-existent
	// rows.
	@Test
	public void testRowIndexOutOfBoundsRowTotal() {
		try {
			DataUtilities.calculateRowTotal(values2D, 100); // Assuming row index 100 is out of bounds.
			fail("Expected IndexOutOfBoundsException for row index out of bounds.");
		} catch (IndexOutOfBoundsException e) {
			assertTrue("Correct exception thrown for row index out of bounds.", true);
		}
	}

	// TC29: Test with a row containing null and numeric values to ensure nulls are
	// safely ignored and not cause errors.
	@Test
	public void testRowWithNullAndNumericValuesRowTotal() {
		DefaultKeyedValues2D mixedNullNumericValues = new DefaultKeyedValues2D();
		mixedNullNumericValues.addValue(null, 0, 0);
		mixedNullNumericValues.addValue(3.5, 0, 1);
		assertEquals("The sum should only include numeric values, ignoring nulls.", 3.5,
				DataUtilities.calculateRowTotal(mixedNullNumericValues, 0), 0.0000001d);
	}

	// TC30: Test with a data structure that contains a very large number of columns
	// to verify performance and accuracy.
	@Test
	public void testRowWithManyColumnsRowTotal() {
		DefaultKeyedValues2D manyColumnsData = new DefaultKeyedValues2D();
		for (int i = 0; i < 1000; i++) {
			manyColumnsData.addValue(1, 0, i);
		}
		assertEquals("The sum of a row with many columns should be calculated correctly.", 1000.0,
				DataUtilities.calculateRowTotal(manyColumnsData, 0), 0.0000001d);
	}

	// TC31: Test with floating point precision to verify the method accurately
	// calculates totals with floating point numbers.
	@Test
	public void testRowWithFloatingPointPrecisionRowTotal() {
		DefaultKeyedValues2D floatingPointValues = new DefaultKeyedValues2D();
		floatingPointValues.addValue(2.3, 0, 0);
		floatingPointValues.addValue(4.7, 0, 1);
		assertEquals("The sum of floating point numbers in a row should be accurate.", 7.0,
				DataUtilities.calculateRowTotal(floatingPointValues, 0), 0.0000001d);
	}

	// createNumberArray

	// TC32: Test with the smallest possible double value.
	@Test
	public void testWithSmallestDoubleValueCreateNumberArray() {
		double[] data = { Double.MIN_VALUE };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should be the smallest double value", Double.MIN_VALUE, actual[0].doubleValue(),
				0.0);
	}

	// TC33: Test with the largest possible double value.
	@Test
	public void testWithLargestDoubleValueCreateNumberArray() {
		double[] data = { Double.MAX_VALUE };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should be the largest double value", Double.MAX_VALUE, actual[0].doubleValue(),
				0.0);
	}

	// TC34: Test with zero.
	@Test
	public void testWithZeroCreateNumberArray() {
		double[] data = { 0.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should be zero", 0.0, actual[0].doubleValue(), 0.0);
	}

	// TC35: Test with a mix of positive, negative, and zero values.
	@Test
	public void testWithMixedValuesCreateNumberArray() {
		double[] data = { -1.0, 0.0, 1.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should represent mixed values correctly", -1.0, actual[0].doubleValue(), 0.0);
	}

	// TC36: Test with an empty array.
	@Test
	public void testWithEmptyArrayCreateNumberArray() {
		double[] data = {};
		Number[] actual = DataUtilities.createNumberArray(data);
		assertNotNull("The result should not be null for an empty input array", actual);
		assertEquals("The length of the resulting array should be 0 for an empty input array", 0, actual.length);
	}

	// TC37: Test with null input (expected to throw IllegalArgumentException).
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullInputCreateNumberArray() {
		DataUtilities.createNumberArray(null);
	}

	// TC38: Test with high precision double values.
	@Test
	public void testWithHighPrecisionValuesCreateNumberArray() {
		double[] data = { 0.1234567890123456, -0.1234567890123456 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should accurately represent high precision values", 0.1234567890123456,
				actual[0].doubleValue(), 0.0);
	}

	// TC39: Test with subnormal double values.
	@Test
	public void testWithSubnormalValuesCreateNumberArray() {
		double[] data = { Double.MIN_NORMAL / 2, -Double.MIN_NORMAL / 2 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should accurately represent subnormal values", Double.MIN_NORMAL / 2,
				actual[0].doubleValue(), 0.0);
	}

	// TC40: Test with special double values (NaN, positive/negative infinity).
	@Test
	public void testWithSpecialDoubleValuesCreateNumberArray() {
		double[] data = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertTrue("First element should be NaN", Double.isNaN(actual[0].doubleValue()));
	}

	// TC41: Test with 'regular values'.
	@Test
	public void testRegularValueCreateNumberArray() {
		double[] data = { 1.0, 2.0, 3.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should accurately represent regular values", 1.0, actual[0].doubleValue(), 0.0);
	}

	// TC42: Test with an array containing only Double.MIN_VALUE and
	// Double.MAX_VALUE.
	@Test
	public void testExtremesCreateNumberArray() {
		double[] data = { Double.MIN_VALUE, Double.MAX_VALUE };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("Handling extremes: MIN_VALUE", Double.MIN_VALUE, actual[0].doubleValue(), 0.0);
		assertEquals("Handling extremes: MAX_VALUE", Double.MAX_VALUE, actual[1].doubleValue(), 0.0);
	}

	// TC43: Test with an array containing repetitive values.
	@Test
	public void testRepetitiveValuesCreateNumberArray() {
		double[] data = new double[10];
		Arrays.fill(data, 5.0);
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("Repetitive values should be handled correctly", 5.0, actual[0].doubleValue(), 0.0);
		assertEquals("Array size should match input", 10, actual.length);
	}

	// TC44: Test with an array of alternating positive and negative values.
	@Test
	public void testAlternatingSignsCreateNumberArray() {
		double[] data = { -1.0, 1.0, -2.0, 2.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("Alternating signs: Negative value", -1.0, actual[0].doubleValue(), 0.0);
		assertEquals("Alternating signs: Positive value", 1.0, actual[1].doubleValue(), 0.0);
	}

	// TC45: Test with an array of decreasing values.
	@Test
	public void testDecreasingValuesCreateNumberArray() {
		double[] data = { 3.0, 2.0, 1.0, 0.0, -1.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("Decreasing values should be correctly ordered", 3.0, actual[0].doubleValue(), 0.0);
		assertEquals("Last element should be -1.0", -1.0, actual[data.length - 1].doubleValue(), 0.0);
	}

	// TC46: Test with an array containing the same value as both positive and
	// negative.
	@Test
	public void testSymmetricValuesCreateNumberArray() {
		double[] data = { -42.0, 42.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("Symmetric values: Negative", -42.0, actual[0].doubleValue(), 0.0);
		assertEquals("Symmetric values: Positive", 42.0, actual[1].doubleValue(), 0.0);
	}

	// createNumberArray2D

	// TC47: Test with the smallest possible double value.
	@Test
	public void testWithSmallestDoubleValueCreateNumberArray2D() {
		double[][] data = { { Double.MIN_VALUE } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should be the smallest double value", Double.MIN_VALUE,
				actual[0][0].doubleValue(), 0.0);
	}

	// TC48: Test with the largest possible double value.
	@Test
	public void testWithLargestDoubleValueCreateNumberArray2D() {
		double[][] data = { { Double.MAX_VALUE } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should be the largest double value", Double.MAX_VALUE,
				actual[0][0].doubleValue(), 0.0);
	}

	// TC49: Test with zero.
	@Test
	public void testWithZeroCreateNumberArray2D() {
		double[][] data = { { 0.0 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should be zero", 0.0, actual[0][0].doubleValue(), 0.0);
	}

	// TC50: Test with a mix of positive, negative, and zero values.
	@Test
	public void testWithMixedValuesCreateNumberArray2D() {
		double[][] data = { { -1.0, 0.0, 1.0 }, { 1.0, -2.0, 3.0 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should correctly represent mixed values", -1.0,
				actual[0][0].doubleValue(), 0.0);
	}

	// TC51: A 2D array where the first inner array is empty
	@Test
	public void testCreateNumberArray2DWithEmptyAndNonEmptyInnerArrays() {
		double[][] data = { {}, { 1.0, 2.0 } };
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("The first inner array should be empty", 0, result[0].length);
	}

	// TC52: An entirely empty 2D array of Number objects.
	@Test
	public void testCreateNumberArray2DWithEntirelyEmptyArray() {
		double[][] data = new double[0][];
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("The outer array should be empty", 0, result.length);
	}

	// TC53: Test with null input (expected to throw IllegalArgumentException).
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullInputCreateNumberArray2D() {
		DataUtilities.createNumberArray2D(null);
	}

	// TC54: Test with high precision double values.
	@Test
	public void testWithHighPrecisionValuesCreateNumberArray2D() {
		double[][] data = { { 0.1234567890123456, -0.1234567890123456 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should accurately represent high precision values",
				0.1234567890123456, actual[0][0].doubleValue(), 0.0);
	}

	// TC55: Test with subnormal double values.
	@Test
	public void testWithSubnormalValuesCreateNumberArray2D() {
		double[][] data = { { Double.MIN_NORMAL / 2, -Double.MIN_NORMAL / 2 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should accurately represent subnormal values", Double.MIN_NORMAL / 2,
				actual[0][0].doubleValue(), 0.0);
	}

	// TC56: Test with special double values (NaN, positive/negative infinity).
	@Test
	public void testWithSpecialDoubleValuesCreateNumberArray2D() {
		double[][] data = { { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertTrue("First element in first array should be NaN", Double.isNaN(actual[0][0].doubleValue()));
	}

	// TC57: Test with a 2D array containing one row of increasing values.
	@Test
	public void testWithIncreasingValuesCreateNumberArray2D() {
		double[][] data = { { 1.0, 2.0, 3.0, 4.0 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("Last element in the first array should be 4.0", 4.0, actual[0][3].doubleValue(), 0.0);
	}

	// TC58: Test with multiple empty inner arrays.
	@Test
	public void testMultipleEmptyInnerArraysCreateNumberArray2D() {
		double[][] data = { {}, {}, {} };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("There should be three inner arrays", 3, actual.length);
	}

	// TC59: Test with an inner array being null.
	@Test
	public void testWithNullInnerArrayCreateNumberArray2D() {
		double[][] data = { null, { 1.0, 2.0 } };
		Number[][] actual = null;
		try {
			actual = DataUtilities.createNumberArray2D(data);
			// Test passes if no exception is thrown and execution reaches here.
			// Check if the method handled the null inner array as expected.
			assertNull("The first inner array should be null", actual[0]);
		} catch (Exception e) {
			fail("The method should handle null inner arrays without throwing an exception.");
		}
	}

	// TC60: Test with varying lengths of inner arrays.
	@Test
	public void testVaryingLengthsInnerArraysCreateNumberArray2D() {
		double[][] data = { { 1.0 }, { 2.0, 3.0 }, { 4.0, 5.0, 6.0 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("The length of the third inner array should be 3", 3, actual[2].length);
	}

	// TC61: Test with arrays containing only the value Double.MIN_VALUE and
	// Double.MAX_VALUE.
	@Test
	public void testExtremesInInnerArraysCreateNumberArray2D() {
		double[][] data = { { Double.MIN_VALUE }, { Double.MAX_VALUE } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in the first array should be Double.MIN_VALUE", Double.MIN_VALUE,
				actual[0][0].doubleValue(), 0.0);
		assertEquals("First element in the second array should be Double.MAX_VALUE", Double.MAX_VALUE,
				actual[1][0].doubleValue(), 0.0);
	}

	// getCumulativePercentages(KeyedValues data)

	// TC62: Test with regular data to ensure the method works in normal conditions.
	@Test
	public void testGetCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 6.0);
		keyvalues.addValue((Comparable) 1.0, 11.0);
		keyvalues.addValue((Comparable) 2.0, 3.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(2), 1.0, 0.0000001d);
	}

	// TC63: Test with a single positive value.
	@Test
	public void testSinglePositveValueCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 123.456);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(0), 1.0, 0.0000001d);
	}

	// TC64: Test with a single negative value.
	@Test
	public void testSingleNegativeValueCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, -123.456);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(0), 1.0, 0.0000001d);
	}

	// TC65: Test with a mix of positive and negative values.
	@Test
	public void testPositveAndNegativeCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, -50.0);
		keyvalues.addValue((Comparable) 1.0, 100.0);
		keyvalues.addValue((Comparable) 2.0, -25.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(0), -2.0, 0.0000001d);
	}

	// TC66: Test with descending order.
	@Test
	public void testDescendingCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 10.0);
		keyvalues.addValue((Comparable) 1.0, 5.0);
		keyvalues.addValue((Comparable) 2.0, 1.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(2), 1.0, 0.0000001d);
	}

	// TC67: Test with added zeros.
	@Test
	public void testMultipleZeroAndPositveCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 0.0);
		keyvalues.addValue((Comparable) 1.0, 10.0);
		keyvalues.addValue((Comparable) 2.0, 0.0);
		keyvalues.addValue((Comparable) 3.0, 5.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(3), 0.333333, 0.0000001d);
	}

	// TC68: Test Empty data set.
	@Test
	public void testEmptyDataSetCumulativePercentages() {
		try {
			DefaultKeyedValues keyvalues = new DefaultKeyedValues();
			KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

			assertEquals((double) object_under_test.getValue(0), 0, 0.0000001d);
		} catch (Exception e) {
			fail("Expected an input that wasn't empty : " + e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC69: Test with null value.
	@Test
	public void testNullCumulativePercentages() {
		try {
			DefaultKeyedValues keyvalues = new DefaultKeyedValues();
			KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);
			keyvalues.addValue((Comparable) null, null);

			assertEquals((double) object_under_test.getValue(0), 0, 0.0000001d);
		} catch (Exception e) {
			fail("Expected an input that wasn't null : " + e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC70: Test Non-Numeric value.s
	@Test
	public void testNonNumericCumulativePercentages() {
		try {
			DefaultKeyedValues keyvalues = new DefaultKeyedValues();
			KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);
			keyvalues.addValue((Comparable) 0.0, Double.NaN);
			keyvalues.addValue((Comparable) 1.0, Double.POSITIVE_INFINITY);

			assertEquals((double) object_under_test.getValue(0), 0, 0.0000001d);
		} catch (Exception e) {
			fail("Expected an input that was numeric : " + e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC71: Test duplicate keys.
	@Test
	public void testDuplicateKeysCumulativePercentages() {
		try {
			DefaultKeyedValues keyvalues = new DefaultKeyedValues();
			KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);
			keyvalues.addValue((Comparable) 0.0, 10.0);
			keyvalues.addValue((Comparable) 0.0, 20.0);

			assertEquals((double) object_under_test.getValue(0), 1.0, 0.0000001d);
		} catch (Exception e) {
			fail("Expected an input that didn't duplicate keys : "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC72: Test with large values.
	@Test
	public void testLargeValuesCumulativePercentages() {
		try {
			DefaultKeyedValues keyvalues = new DefaultKeyedValues();
			KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);
			keyvalues.addValue((Comparable) 0.0, 1000000000.0);
			keyvalues.addValue((Comparable) 1.0, 2000000000.0);
			keyvalues.addValue((Comparable) 2.0, 3000000000.0);

			assertEquals((double) object_under_test.getValue(1), 0.5, 0.0000001d);
		} catch (Exception e) {
			fail("Expected an input that wasn't as large : " + e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC73: Test with null value
	@Test(expected = IllegalArgumentException.class)
	public void testNullDataCumulativePercentages() {
		DataUtilities.getCumulativePercentages(null);
	}

	// TC74: Test with non-null values
	@Test
	public void testNonNullValuesCumulativePercentages() {
		// Create a KeyedValues instance with all non-null values
		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		keyedValues.addValue("A", 20.0);
		keyedValues.addValue("B", 30.0);
		keyedValues.addValue("C", 50.0);

		// Call the method under test
		KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

		// Assertions to check if the cumulative percentages are calculated correctly
		assertEquals(0.2, (double) result.getValue("A"), 0.01);
		assertEquals(0.5, (double) result.getValue("B"), 0.01);
		assertEquals(1.0, (double) result.getValue("C"), 0.01);
	}

	// TC74: Test with some null valuess
	@Test
	public void testWithNullValuesCumulativePercentages() {
		// Create a KeyedValues instance with some null values
		DefaultKeyedValues keyedValues = new DefaultKeyedValues();
		keyedValues.addValue("A", 20.0);
		keyedValues.addValue("B", null);
		keyedValues.addValue("C", 30.0);
		keyedValues.addValue("D", 50.0);

		// Call the method under test
		KeyedValues result = DataUtilities.getCumulativePercentages(keyedValues);

		// Assertions to check if the method correctly skips null values
		assertEquals(0.2, (double) result.getValue("A"), 0.01);
		assertEquals(0.2 + 0.3, (double) result.getValue("C"), 0.01); // Cumulative percentage including "A" and "C"
		assertEquals(1.0, (double) result.getValue("D"), 0.01); // Final cumulative percentage
	}

	// TC75: Test with extreme positive and negative values to ensure method
	// accuracy.
	@Test
	public void testExtremeValuesCumulativePercentages() {
		DefaultKeyedValues keyValues = new DefaultKeyedValues();
		keyValues.addValue("Very Negative", -Double.MAX_VALUE);
		keyValues.addValue("Zero", 0.0);
		keyValues.addValue("Very Positive", Double.MAX_VALUE);

		KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
		assertNotNull(result);

	}
	
	// TC76: Test with a large number of items to ensure method performance and scalability.
	@Test
	public void testLargeNumberOfItemsCumulativePercentages() {
	    DefaultKeyedValues keyValues = new DefaultKeyedValues();
	    int itemCount = 10000; // Large number of items
	    for (int i = 0; i < itemCount; i++) {
	        keyValues.addValue("Item " + i, 1.0); // Each item has a value of 1.0
	    }
	    
	    KeyedValues result = DataUtilities.getCumulativePercentages(keyValues);
	    
	    // Verify that the last item's cumulative percentage is 1.0
	    assertEquals(1.0, (double) result.getValue("Item " + (itemCount - 1)), 0.0001);
	}

}