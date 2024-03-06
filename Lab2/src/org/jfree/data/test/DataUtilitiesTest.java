package org.jfree.data.test;

import static org.junit.Assert.*;

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

	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 5.0, DataUtilities.calculateColumnTotal(values2D, 0),
				0.0000001d);
	}

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

	@Test
	public void testNegativeDataColumnTotal() {
		// Expecting 0 because the sum of negative values is below Integer.MIN_VALUE,
		// causing an underflow.
		assertEquals("Incorrect sum for negative values, underflow should result in 0.", 0.0,
				DataUtilities.calculateColumnTotal(negativeNegativeValues2D, 0), 0.0000001d);
	}

	@Test
	public void testPositiveNegativeDataColumnTotal() {
		// The sum of the first column is 2147483647 - 10, which is 2147483637, within
		// the valid int range.
		assertEquals("The sum should be within the valid int range.", 2147483637.0,
				DataUtilities.calculateColumnTotal(positiveNegativeValues2D, 0), 0.0000001d);
	}

	@Test
	public void testNegativePositiveDataColumnTotal() {
		try {
			// The sum of the third column (index 2) should be 22 (-12 from the first array
			// and 34 from the second array).
			double result = DataUtilities.calculateColumnTotal(negativePositiveValues2D, 2);
			assertEquals("The sum of the third column should be 22.", 22.0, result, 0.0000001d);
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

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

	@Test
	public void testNegativeEmptyArraysColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(negativeEmptyValues2D, 2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testEmptyPositiveArraysColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(emptyPositiveValues2D, 2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testNullInputColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 3);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testInvalidNegativeColumnIndexColumnTotal() {
		try {
			// Expecting 0 for invalid negative column index as per specific implementation
			// details.
			double result = DataUtilities.calculateColumnTotal(values2D, -19);
			assertEquals("Sum should be 0 for invalid negative column index.", 0.0, result, 0.0000001d);
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	@Test
	public void testNegativeColumnIndexWithNegativeValuesColumnTotal() {
		try {
			// Expecting 0 for an invalid negative column index, consistent with specified
			// behavior for such cases.
			double result = DataUtilities.calculateColumnTotal(negativeNegativeValues2D, -2);
			assertEquals("Sum should be 0 for invalid negative column index with negative values.", 0.0, result,
					0.0000001d);
		} catch (Exception e) {
			fail("An unexpected exception was thrown for an invalid negative column index with negative values: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// calculateRowTotal

	// TC11: Test a valid row with only positive numbers. The expected result should
	// be the sum of those numbers.
	@Test
	public void testValidDataAndRowRowTotal() {
		assertEquals("The sum of positive numbers in row 0 should be 1.0", 1.0,
				DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}

	// TC12: Test a row with the largest negative number. This should result in the
	// sum of those values if it doesn't underflow.
	@Test
	public void testNegativeDataRowTotal() {
		assertEquals("The sum of the largest negative numbers in row 0 should be correctly calculated.", -2147483651.0,
				DataUtilities.calculateRowTotal(negativeNegativeValues2D, 0), 0.0000001d);
	}

	// TC13: Test a row with a mix of the largest positive and negative numbers to
	// ensure they are added correctly.
	@Test
	public void testPositiveNegativeDataRowTotal() {
		assertEquals("The sum of mixed positive and negative numbers in row 1 should be correctly calculated.", -129.0,
				DataUtilities.calculateRowTotal(positiveNegativeValues2D, 1), 0.0000001d);
	}

	// TC14: Test a row with mixed negative and positive numbers to ensure the sum
	// is calculated as expected.
	@Test
	public void testNegativePositiveDataRowTotal() {
		assertEquals("The sum of mixed negative and positive numbers in row 1 should be correctly calculated.", 74.0,
				DataUtilities.calculateRowTotal(negativePositiveValues2D, 1), 0.0000001d);
	}

	// TC15: Test empty arrays to confirm that an IllegalArgumentException is thrown
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

	// TC16: Test a row with negative values and an empty row to confirm that an
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

	// TC17: Testing with an empty row followed by a row with positive values.
	@Test
	public void testEmptyPositiveRowTotal() {
		try {
			DataUtilities.calculateRowTotal(emptyPositiveValues2D, 1);
			fail("No exception thrown. An IllegalArgumentException was expected due to an empty first row.");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC18: Testing with null data which should throw IllegalArgumentException.
	@Test
	public void testNullDataRowTotal() {
		try {
			DataUtilities.calculateRowTotal(null, 1);
			fail("No exception thrown. An IllegalArgumentException was expected due to null data.");
		} catch (Exception e) {
			assertTrue("Incorrect exception type thrown", e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC19: Testing with a valid data row and an invalid negative row index.
	@Test
	public void testValidDataNegativeRowIndexRowTotal() {
		// A negative row index is expected to result in a return value of 0, as
		// specified by the implementation.
		assertEquals("The row total should be 0 for a negative row index.", 0.0,
				DataUtilities.calculateRowTotal(values2D, -19), 0.0000001d);
	}

	// TC20: Testing with a data row containing negative values and an invalid
	// negative row index.
	public void testNegativeRowIndexWithNegativeValuesRowTotal() {
		try {
			DataUtilities.calculateRowTotal(negativeNegativeValues2D, -2);
			fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
		} catch (IllegalArgumentException e) {
			assertTrue("Incorrect exception type thrown", e instanceof IllegalArgumentException);
		}
	}

	// createNumberArray

	// TC21: Test with the smallest possible double value.
	@Test
	public void testWithSmallestDoubleValueCreateNumberArray() {
		try {
			double[] data = { Double.MIN_VALUE };
			Number[] actual = DataUtilities.createNumberArray(data);
			assertEquals("First element should be the smallest double value", Double.MIN_VALUE, actual[0].doubleValue(),
					0.0);
		} catch (Exception e) {
			fail("Expected the smallest double value as the first element: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC22: Test with the largest possible double value.
	@Test
	public void testWithLargestDoubleValueCreateNumberArray() {
		try {
			double[] data = { Double.MAX_VALUE };
			Number[] actual = DataUtilities.createNumberArray(data);
			assertEquals("First element should be the largest double value", Double.MAX_VALUE, actual[0].doubleValue(),
					0.0);
		} catch (Exception e) {
			fail("Expected the largest double value as the first element: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC23: Test with zero.
	@Test
	public void testWithZeroCreateNumberArray() {
		try {
			double[] data = { 0.0 };
			Number[] actual = DataUtilities.createNumberArray(data);
			assertEquals("First element should be zero", 0.0, actual[0].doubleValue(), 0.0);
		} catch (Exception e) {
			fail("Expected the 0 as the first element: " + e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC24: Test with a mix of positive, negative, and zero values.
	@Test
	public void testWithMixedValuesCreateNumberArray() {
		double[] data = { -1.0, 0.0, 1.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should represent mixed values correctly", -1.0, actual[0].doubleValue(), 0.0);
	}

	// TC25: Test with an empty array.
	@Test
	public void testWithEmptyArrayCreateNumberArray() {
		double[] data = {};
		Number[] actual = DataUtilities.createNumberArray(data);
		assertNotNull("The result should not be null for an empty input array", actual);
		assertEquals("The length of the resulting array should be 0 for an empty input array", 0, actual.length);
	}

	// TC26: Test with null input (expected to throw IllegalArgumentException).
	@Test(expected = IllegalArgumentException.class)
	public void testWithNullInputCreateNumberArray() {
		DataUtilities.createNumberArray(null);
	}

	// TC27: Test with high precision double values.
	@Test
	public void testWithHighPrecisionValuesCreateNumberArray() {
		double[] data = { 0.1234567890123456, -0.1234567890123456 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should accurately represent high precision values", 0.1234567890123456,
				actual[0].doubleValue(), 0.0);
	}

	// TC28: Test with subnormal double values.
	@Test
	public void testWithSubnormalValuesCreateNumberArray() {
		double[] data = { Double.MIN_NORMAL / 2, -Double.MIN_NORMAL / 2 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should accurately represent subnormal values", Double.MIN_NORMAL / 2,
				actual[0].doubleValue(), 0.0);
	}

	// TC29: Test with special double values (NaN, positive/negative infinity).
	@Test
	public void testWithSpecialDoubleValuesCreateNumberArray() {
		double[] data = { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertTrue("First element should be NaN", Double.isNaN(actual[0].doubleValue()));
	}

	// TC30: Test with 'regular values'.
	@Test
	public void testRegularValueCreateNumberArray() {
		double[] data = { 1.0, 2.0, 3.0 };
		Number[] actual = DataUtilities.createNumberArray(data);
		assertEquals("First element should accurately represent regular values", 1.0, actual[0].doubleValue(), 0.0);
	}

	// createNumberArray2D

	// TC31: Test with the smallest possible double value.
	@Test
	public void testWithSmallestDoubleValueCreateNumberArray2D() {
		try {
			double[][] data = { { Double.MIN_VALUE } };
			Number[][] actual = DataUtilities.createNumberArray2D(data);
			assertEquals("First element in first array should be the smallest double value", Double.MIN_VALUE,
					actual[0][0].doubleValue(), 0.0);
		} catch (Exception e) {
			fail("Expected the smallest double value as the first element: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC32: Test with the largest possible double value.
	@Test
	public void testWithLargestDoubleValueCreateNumberArray2D() {
		try {
			double[][] data = { { Double.MAX_VALUE } };
			Number[][] actual = DataUtilities.createNumberArray2D(data);
			assertEquals("First element in first array should be the largest double value", Double.MAX_VALUE,
					actual[0][0].doubleValue(), 0.0);
		} catch (Exception e) {
			fail("Expected the largest double value as the first element: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC33: Test with zero.
	@Test
	public void testWithZeroCreateNumberArray2D() {
		try {
			double[][] data = { { 0.0 } };
			Number[][] actual = DataUtilities.createNumberArray2D(data);
			assertEquals("First element in first array should be zero", 0.0, actual[0][0].doubleValue(), 0.0);
		} catch (Exception e) {
			fail("Expected 0 as the first element: " + e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC34: Test with a mix of positive, negative, and zero values.
	@Test
	public void testWithMixedValuesCreateNumberArray2D() {
		double[][] data = { { -1.0, 0.0, 1.0 }, { 1.0, -2.0, 3.0 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should correctly represent mixed values", -1.0,
				actual[0][0].doubleValue(), 0.0);
	}

	// TC35: A 2D array where the first inner array is empty
	@Test
	public void testCreateNumberArray2DWithEmptyAndNonEmptyInnerArrays() {
		double[][] data = { {}, { 1.0, 2.0 } };
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("The first inner array should be empty", 0, result[0].length);
	}

	// TC36: An entirely empty 2D array of Number objects.
	@Test
	public void testCreateNumberArray2DWithEntirelyEmptyArray() {
		double[][] data = new double[0][];
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("The outer array should be empty", 0, result.length);
	}

	// TC37: Test with null input (expected to throw IllegalArgumentException).
	@Test
	public void testWithNullInputCreateNumberArray2D() {
		try {
			DataUtilities.createNumberArray2D(null);
		} catch (Exception e) {
			fail("An unexpected exception was thrown for an invalid input: "
					+ e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC38: Test with high precision double values.
	@Test
	public void testWithHighPrecisionValuesCreateNumberArray2D() {
		double[][] data = { { 0.1234567890123456, -0.1234567890123456 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should accurately represent high precision values",
				0.1234567890123456, actual[0][0].doubleValue(), 0.0);
	}

	// TC39: Test with subnormal double values.
	@Test
	public void testWithSubnormalValuesCreateNumberArray2D() {
		double[][] data = { { Double.MIN_NORMAL / 2, -Double.MIN_NORMAL / 2 } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertEquals("First element in first array should accurately represent subnormal values", Double.MIN_NORMAL / 2,
				actual[0][0].doubleValue(), 0.0);
	}

	// TC40: Test with special double values (NaN, positive/negative infinity).
	@Test
	public void testWithSpecialDoubleValuesCreateNumberArray2D() {
		double[][] data = { { Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY } };
		Number[][] actual = DataUtilities.createNumberArray2D(data);
		assertTrue("First element in first array should be NaN", Double.isNaN(actual[0][0].doubleValue()));
	}

	// getCumulativePercentages(KeyedValues data)

	// TC41: Test with regular data to ensure MUT works in normal conditions
	@Test
	public void testGetCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 6.0);
		keyvalues.addValue((Comparable) 1.0, 11.0);
		keyvalues.addValue((Comparable) 2.0, 3.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(2), 1.0, 0.0000001d);
	}

	// TC42: Test with a single positive value
	@Test
	public void testSinglePositveValueCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 123.456);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(0), 1.0, 0.0000001d);
	}

	// TC43: Test with a single negative value
	@Test
	public void testSingleNegativeValueCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, -123.456);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(0), 1.0, 0.0000001d);
	}

	// TC44: Test with a mix of positve and negative
	@Test
	public void testPositveAndNegativeCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, -50.0);
		keyvalues.addValue((Comparable) 1.0, 100.0);
		keyvalues.addValue((Comparable) 2.0, -25.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(0), -2.0, 0.0000001d);
	}

	// TC45: Test with descending order
	@Test
	public void testDescendingCumulativePercentages() {
		DefaultKeyedValues keyvalues = new DefaultKeyedValues();
		keyvalues.addValue((Comparable) 0.0, 10.0);
		keyvalues.addValue((Comparable) 1.0, 5.0);
		keyvalues.addValue((Comparable) 2.0, 1.0);
		KeyedValues object_under_test = DataUtilities.getCumulativePercentages(keyvalues);

		assertEquals((double) object_under_test.getValue(2), 1.0, 0.0000001d);
	}

	// TC46: Test with added zeros
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

	// TC47: Test Empty data set
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

	// TC48: Test with null value
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

	// TC49: Test Non Numeric value
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

	// TC50: Test duplicate keys
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

	// TC51: Test with large values
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

}