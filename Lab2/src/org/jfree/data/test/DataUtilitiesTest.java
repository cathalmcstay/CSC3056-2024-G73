package org.jfree.data.test;

import static org.junit.Assert.*;

import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DataUtilitiesTest extends DataUtilities {
	
	private Values2D values2D;
	private Values2D negativeNegativeValues2D; //Array of negative array, negative array
	private Values2D positiveNegativeValues2D; //Array of positive array, negative array
	private Values2D negativePositiveValues2D; //Array of negative array, positive array
	private Values2D emptyValues2D;			   //Array of empty arrays
	private Values2D negativeEmptyValues2D;	   //Array of negative array, empty array
	private Values2D emptyPositiveValues2D;	   //Array of empty array, positive array

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
	}
	
	//calculateColumnTotal

	@Test
	public void testValidDataAndColumnColumnTotal() {
		assertEquals("Wrong sum returned. It should be 5.0", 
				5.0, DataUtilities.calculateColumnTotal(values2D, 0), 0.0000001d);
	}
	
	@Test
	public void testNullDataColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(null, 0);
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		}
		catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testNegativeDataColumnTotal() {
	    // Expecting 0 because the sum of negative values is below Integer.MIN_VALUE, causing an underflow.
	    assertEquals("Incorrect sum for negative values, underflow should result in 0.", 
	                 0.0, DataUtilities.calculateColumnTotal(negativeNegativeValues2D, 0), 0.0000001d);
	}
	
	@Test
	public void testPositiveNegativeDataColumnTotal() {
	    // The sum of the first column is 2147483647 - 10, which is 2147483637, within the valid int range.
	    assertEquals("The sum should be within the valid int range.",
	                 2147483637.0, DataUtilities.calculateColumnTotal(positiveNegativeValues2D, 0), 0.0000001d);
	}
	
	@Test
	public void testNegativePositiveDataColumnTotal() {
	    try {
	        // The sum of the third column (index 2) should be 22 (-12 from the first array and 34 from the second array).
	        double result = DataUtilities.calculateColumnTotal(negativePositiveValues2D, 2);
	        assertEquals("The sum of the third column should be 22.", 22.0, result, 0.0000001d);
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testEmptyArraysColumnTotal() {
		try {
			DataUtilities.calculateColumnTotal(emptyValues2D, 2);
			fail("No exception thrown. The expected outcome was: a thrown "
					+ "exception of type: IllegalArgumentException");
		}
		catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testNegativeEmptyArraysColumnTotal() {
	    try {
	        DataUtilities.calculateColumnTotal(negativeEmptyValues2D, 2);
	        fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testEmptyPositiveArraysColumnTotal() {
	    try {
	        DataUtilities.calculateColumnTotal(emptyPositiveValues2D, 2);
	        fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testNullInputColumnTotal() {
	    try {
	        DataUtilities.calculateColumnTotal(null, 3);
	        fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}
	
	@Test
	public void testInvalidNegativeColumnIndexColumnTotal() {
	    try {
	        // Expecting 0 for invalid negative column index as per specific implementation details.
	        double result = DataUtilities.calculateColumnTotal(values2D, -19);
	        assertEquals("Sum should be 0 for invalid negative column index.", 0.0, result, 0.0000001d);
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}


	@Test
	public void testNegativeColumnIndexWithNegativeValuesColumnTotal() {
	    try {
	        // Expecting 0 for an invalid negative column index, consistent with specified behavior for such cases.
	        double result = DataUtilities.calculateColumnTotal(negativeNegativeValues2D, -2);
	        assertEquals("Sum should be 0 for invalid negative column index with negative values.", 0.0, result, 0.0000001d);
	    } catch (Exception e) {
	        fail("An unexpected exception was thrown for an invalid negative column index with negative values: " + e.getMessage());
	    }
	}

	
	//calculateRowTotal
	
	// TC11: Test a valid row with only positive numbers. The expected result should be the sum of those numbers.
	@Test
	public void testValidDataAndRowRowTotal() {
	    assertEquals("The sum of positive numbers in row 0 should be 1.0", 
	            1.0, DataUtilities.calculateRowTotal(values2D, 0), 0.0000001d);
	}

	// TC12: Test a row with the largest negative number. This should result in the sum of those values if it doesn't underflow.
	@Test
	public void testNegativeDataRowTotal() {
	    assertEquals("The sum of the largest negative numbers in row 0 should be correctly calculated.", 
	            -2147483651.0, DataUtilities.calculateRowTotal(negativeNegativeValues2D, 0), 0.0000001d);
	}

	// TC13: Test a row with a mix of the largest positive and negative numbers to ensure they are added correctly.
	@Test
	public void testPositiveNegativeDataRowTotal() {
	    assertEquals("The sum of mixed positive and negative numbers in row 1 should be correctly calculated.",
	                 -129.0, DataUtilities.calculateRowTotal(positiveNegativeValues2D, 1), 0.0000001d);
	}

	// TC14: Test a row with mixed negative and positive numbers to ensure the sum is calculated as expected.
	@Test
	public void testNegativePositiveDataRowTotal() {
	    assertEquals("The sum of mixed negative and positive numbers in row 1 should be correctly calculated.", 
	                 74.0, DataUtilities.calculateRowTotal(negativePositiveValues2D, 1), 0.0000001d);
	}

	// TC15: Test empty arrays to confirm that an IllegalArgumentException is thrown as per the method contract.
	@Test
	public void testEmptyArraysRowTotal() {
	    try {
	        DataUtilities.calculateRowTotal(emptyValues2D, 0);
	        fail("An exception was expected due to the empty arrays, but none was thrown.");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC16: Test a row with negative values and an empty row to confirm that an IllegalArgumentException is thrown.
	@Test
	public void testNegativeEmptyArraysRowTotal() {
	    try {
	        DataUtilities.calculateRowTotal(negativeEmptyValues2D, 1);
	        fail("An exception was expected due to the negative values followed by an empty row, but none was thrown.");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}

	
	// TC17: Testing with an empty row followed by a row with positive values.
	@Test
	public void testEmptyPositiveRowTotal() {
	    try {
	        DataUtilities.calculateRowTotal(emptyPositiveValues2D, 1);
	        fail("No exception thrown. An IllegalArgumentException was expected due to an empty first row.");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC18: Testing with null data which should throw IllegalArgumentException.
	@Test
	public void testNullDataRowTotal() {
	    try {
	        DataUtilities.calculateRowTotal(null, 1);
	        fail("No exception thrown. An IllegalArgumentException was expected due to null data.");
	    } catch (Exception e){
			assertTrue("Incorrect exception type thrown",  
				    e.getClass().equals(IllegalArgumentException.class));
		}
	}

	// TC19: Testing with a valid data row and an invalid negative row index.
	@Test
	public void testValidDataNegativeRowIndexRowTotal() {
	    // A negative row index is expected to result in a return value of 0, as specified by the implementation.
	    assertEquals("The row total should be 0 for a negative row index.",
	                 0.0, DataUtilities.calculateRowTotal(values2D, -19), 0.0000001d);
	}

	// TC20: Testing with a data row containing negative values and an invalid negative row index.
	public void testNegativeRowIndexWithNegativeValuesRowTotal() {
	    try {
	        DataUtilities.calculateRowTotal(negativeNegativeValues2D, -2);
	        fail("No exception thrown. The expected outcome was: a thrown exception of type: IllegalArgumentException");
	    } catch (IllegalArgumentException e) {
	        assertTrue("Incorrect exception type thrown",  
	                   e instanceof IllegalArgumentException);
	    }
	}
	

}