package org.jfree.data.test;

import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.jfree.data.Range;
import org.junit.*;

public class RangeTest {

	private Range rangeObjectUnderTest;

	@Before
	public void setUp() throws Exception {
		rangeObjectUnderTest = new Range(0, 0);
	}

	@After
	public void tearDown() {
		rangeObjectUnderTest = new Range(0, 0);
	}

	// Method Name: getCentralValue()

	// TC1: Testing 2 positive whole numbers
	@Test
	public void testCentralValuePositiveWholeNumbers() {
		rangeObjectUnderTest = new Range(4, 9);
		assertEquals("getCentralValue: Testing 2 positive whole numbers", 6.5, rangeObjectUnderTest.getCentralValue(),
				0.01);
	}

	// TC2: Testing 2 positive numbers with maximum input
	@Test
	public void testCentralValuePositiveNumbersWithMaximumInput() {
		rangeObjectUnderTest = new Range(4, 2147483647);
		assertEquals("getCentralValue: Testing 2 positive numbers with maximum input", 1.0737418255E9,
				rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC3: Testing 2 negative whole numbers
	@Test
	public void testCentralValueNegativeWholeNumbers() {
		rangeObjectUnderTest = new Range(-9, -6);
		assertEquals("getCentralValue: Testing 2 negative whole numbers", -7.5, rangeObjectUnderTest.getCentralValue(),
				0.01);
	}

	// TC4: Testing 2 whole negative numbers with negative maximum input
	@Test
	public void testCentralValueWholeNegativeNumbersWithNegativeMaximumInput() {
		rangeObjectUnderTest = new Range(-2147483647, 6);
		assertEquals("getCentralValue: Testing 2 whole negative numbers with negative maximum input", -1.0737418205E9,
				rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC5: Testing 2 positive floating numbers
	@Test
	public void testCentralValuePositiveFloatingNumbers() {
		rangeObjectUnderTest = new Range(4.76, 9.73);
		assertEquals("getCentralValue: Testing 2 positive floating numbers", 7.245,
				rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC6: Testing 2 negative floating point
	@Test
	public void testCentralValueNegativeFloatingNumbers() {
		rangeObjectUnderTest = new Range(-9.73, -5.76);
		assertEquals("getCentralValue: Testing 2 negative floating point", -7.745,
				rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC7: Testing 1 positive and negative whole number
	@Test
	public void testCentralValuePositiveAndNegativeWholeNumber() {
		rangeObjectUnderTest = new Range(-9, 10);
		assertEquals("getCentralValue: Testing 1 positive and negative whole number", 0.5,
				rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC8: Testing Zero and negative floating number
	@Test
	public void testCentralValueZeroAndNegativeFloatingNumber() {
		rangeObjectUnderTest = new Range(-8, 0);
		assertEquals("getCentralValue: Testing Zero and negative floating number", -4,
				rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC9: Testing upper bound less than lower bound
	@Test(expected = IllegalArgumentException.class)
	public void testCentralValueUpperBoundLessThanLowerBound() {
		rangeObjectUnderTest = new Range(10, -23.45);
	}

	// TC11: Testing 1 positive whole number with positive high floating number
	@Test
	public void testCentralValuePositiveWholeNumberWithPositiveHighFloatingNumber() {
		rangeObjectUnderTest = new Range(1, 9656.7378209372819);
		assertEquals("getCentralValue: Testing 1 positive whole number with positive high floating number",
				4828.868910468641, rangeObjectUnderTest.getCentralValue(), 0.01);
	}

	// TC12: Testing when both values are Zero
	@Test
	public void testCentralValueBothValuesZero() {
		rangeObjectUnderTest = new Range(0, 0);
		assertEquals("getCentralValue: Testing when both values are Zero", 0, rangeObjectUnderTest.getCentralValue(),
				0.01);
	}

	// Method Name: constrain(double value)

	// TC13: Testing input is outside the upper value
	@Test
	public void testConstrainInputOutsideUpperValue() {
		Range range = new Range(0, 10);
		double constrainedValue = range.constrain(15);
		assertEquals("constrain: Input is outside the upper value", 10, constrainedValue, 0.01);
	}

	// TC14: Testing input is outside the lower value
	@Test
	public void testConstrainInputOutsideLowerValue() {
		Range range = new Range(10, 50);
		double constrainedValue = range.constrain(4);
		assertEquals("constrain: Input is outside the lower value", 10, constrainedValue, 0.01);
	}

	// TC15: Testing input is inside the range
	@Test
	public void testConstrainInputInsideRange() {
		Range range = new Range(10, 50);
		double constrainedValue = range.constrain(25);
		assertEquals("constrain: Input is inside the range", 25, constrainedValue, 0.01);
	}

	// TC16: Testing input is a range value
	@Test
	public void testConstrainInputIsRangeValue() {
		Range range = new Range(10, 50);
		double constrainedValue = range.constrain(50);
		assertEquals("constrain: Input is a range value", 50, constrainedValue, 0.01);
	}

	// TC18: Testing input is outside negative upper value
	@Test
	public void testConstrainInputOutsideNegativeUpperValue() {
		Range range = new Range(-19, -8);
		double constrainedValue = range.constrain(-6);
		assertEquals("constrain: Input is outside negative upper value", -8, constrainedValue, 0.01);
	}

	// TC19: Testing input is outside negative lower value
	@Test
	public void testConstrainInputOutsideNegativeLowerValue() {
		Range range = new Range(-19, -8);
		double constrainedValue = range.constrain(-20.22);
		assertEquals("constrain: Input is outside negative lower value", -19, constrainedValue, 0.01);
	}

	// TC20: Testing input when it's zero for a zero, zero range
	@Test
	public void testConstrainInputZeroForZeroZeroRange() {
		Range range = new Range(0, 0);
		double constrainedValue = range.constrain(0);
		assertEquals("constrain: Input is zero for a zero, zero range", 0, constrainedValue, 0.01);
	}

	// TC21: Testing floating point input is outside floating point upper value
	@Test
	public void testConstrainFloatingPointInputOutsideFloatingPointUpperValue() {
		Range range = new Range(3.45, 56.3282);
		double constrainedValue = range.constrain(100.87939);
		assertEquals("constrain: Floating point input is outside floating point upper value", 56.3282, constrainedValue,
				0.01);
	}

	// TC22: Testing positive floating point input is outside negative floating
	// point upper value
	@Test
	public void testConstrainPositiveFloatingPointInputOutsideNegativeFloatingPointUpperValue() {
		Range range = new Range(-72.7591, -4.3819);
		double constrainedValue = range.constrain(64);
		assertEquals("constrain: Positive floating point input is outside negative floating point upper value", -4.3819,
				constrainedValue, 0.01);
	}

	// TC23: Testing whole number input when range is invalid
	@Test(expected = NullPointerException.class)
	public void testConstrainWholeNumberInputWhenRangeIsInvalid() {
		Range range = new Range((Double) null, 10);
		double constrainedValue = range.constrain(12);
	}

	// TC24: Testing input is NULL
	@Test(expected = NullPointerException.class)
	public void testConstrainInputIsNull() {
		Range range = new Range(10, 50);
		double constrainedValue = range.constrain((Double) null);
	}

	// TC25: Testing input at maximum int value
	@Test
	public void testConstrainInputAtMaximumIntValue() {
		Range range = new Range(10, 50.45);
		double constrainedValue = range.constrain(Integer.MAX_VALUE);
		assertEquals("constrain: Input at maximum int value", 50.45, constrainedValue, 0.01);
	}

	// TC26: Testing input is "empty"
	@Test(expected = IllegalArgumentException.class)
	public void testConstrainInputIsEmpty() {
		Range range = new Range(10, 50);
		double constrainedValue = range.constrain(Double.NaN);
	}

}