package org.jfree.data.test;

import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;

import org.jfree.data.Range; 
import org.junit.*; 

public class RangeTest {
	
	private Range rangeObjectUnderTest; 
		
	@Before 
	public void setUp() throws Exception { 
		rangeObjectUnderTest = new Range(0, 0); 
	} 
	
	@After
	public void tearDown(){
		rangeObjectUnderTest = new Range(0, 0); 
	}

	//Method Name: getCentralValue()
	
    // TC1: Testing 2 positive whole numbers
    @Test
    public void testCentralValuePositiveWholeNumbers() {
        rangeObjectUnderTest = new Range(4, 9);
        assertEquals("getCentralValue: Testing 2 positive whole numbers", 6.5, rangeObjectUnderTest.getCentralValue(), 0.01);
    }
    
    // TC2: Testing 2 positive numbers with maximum input
    @Test
    public void testCentralValuePositiveNumbersWithMaximumInput() {
        rangeObjectUnderTest = new Range(4, 354748989);
        assertEquals("getCentralValue: Testing 2 positive numbers with maximum input", 1.773744965E8, rangeObjectUnderTest.getCentralValue(), 0.01);
    }
    
    // TC3: Testing 2 negative whole numbers
    @Test
    public void testCentralValueNegativeWholeNumbers() {
        rangeObjectUnderTest = new Range(-9, -6);
        assertEquals("getCentralValue: Testing 2 negative whole numbers", -7.5, rangeObjectUnderTest.getCentralValue(), 0.01);
    }
    
    // TC4: Testing 2 whole negative numbers with negative maximum input
    @Test
    public void testCentralValueWholeNegativeNumbersWithNegativeMaximumInput() {
        rangeObjectUnderTest = new Range(-354748989, 6);
        assertEquals("getCentralValue: Testing 2 whole negative numbers with negative maximum input", -1.773744975E8, rangeObjectUnderTest.getCentralValue(), 0.01);
    }
    
    // TC5: Testing 2 positive floating numbers
    @Test
    public void testCentralValuePositiveFloatingNumbers() {
        rangeObjectUnderTest = new Range(4.76, 9.73);
        assertEquals("getCentralValue: Testing 2 positive floating numbers", 7.245, rangeObjectUnderTest.getCentralValue(), 0.01);
    }
    
    // TC6: Testing 2 negative floating point
    @Test
    public void testCentralValueNegativeFloatingNumbers() {
        rangeObjectUnderTest = new Range(-9.73, -5.76);
        assertEquals("getCentralValue: Testing 2 negative floating point", -7.745, rangeObjectUnderTest.getCentralValue(), 0.01);
    }
} 