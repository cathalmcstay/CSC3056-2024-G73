package org.jfree.data;

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

	// Method Name: getLowerBound()

	// TC27: Testing 2 positive whole numbers
	@Test
	public void testGetLowerBoundPositiveWholeNumbers() {
		Range range = new Range(4, 9);
		assertEquals("getLowerBound: Testing 2 positive whole numbers", 4.0, range.getLowerBound(), 0.01);
	}

	// TC28: Testing 2 positive numbers with maximum input
	@Test
	public void testGetLowerBoundPositiveNumbersWithMaxInput() {
		Range range = new Range(4, 2147483647);
		assertEquals("getLowerBound: Testing 2 positive numbers with maximum input", 4.0, range.getLowerBound(), 0.01);
	}

	// TC29: Testing 2 negative whole numbers
	@Test
	public void testGetLowerBoundNegativeWholeNumbers() {
		Range range = new Range(-9, -6);
		assertEquals("getLowerBound: Testing 2 negative whole numbers", -9.0, range.getLowerBound(), 0.01);
	}

	// TC30: Testing 2 whole negative numbers with negative maximum input
	@Test
	public void testGetLowerBoundNegativeNumbersWithNegativeMaxInput() {
		Range range = new Range(-2147483647, 6);
		assertEquals("getLowerBound: Testing 2 whole negative numbers with negative maximum input", -2147483647.0,
				range.getLowerBound(), 0.01);
	}

	// TC31: Testing 2 positive floating numbers
	@Test
	public void testGetLowerBoundPositiveFloatingNumbers() {
		Range range = new Range(4.76, 9.73);
		assertEquals("getLowerBound: Testing 2 positive floating numbers", 4.76, range.getLowerBound(), 0.01);
	}

	// TC32: Testing 2 negative floating point
	@Test
	public void testGetLowerBoundNegativeFloatingPoint() {
		Range range = new Range(-9.73, -5.76);
		assertEquals("getLowerBound: Testing 2 negative floating point", -9.73, range.getLowerBound(), 0.01);
	}

	// TC33: Testing 1 positive and negative whole number
	@Test
	public void testGetLowerBoundPositiveAndNegativeWholeNumber() {
		Range range = new Range(-9, 10);
		assertEquals("getLowerBound: Testing 1 positive and negative whole number", -9.0, range.getLowerBound(), 0.01);
	}

	// TC34: Testing Zero and negative floating number
	@Test
	public void testGetLowerBoundZeroAndNegativeFloatingNumber() {
		Range range = new Range(-8, 0);
		assertEquals("getLowerBound: Testing Zero and negative floating number", -8.0, range.getLowerBound(), 0.01);
	}

	// TC35: Testing upper bound less than lower bound
	@Test(expected = IllegalArgumentException.class)
	public void testGetLowerBoundUpperBoundLessThanLowerBound() {
		Range range = new Range(10, -23.45);
		range.getLowerBound();
	}

	// TC36: Testing null as input with a valid input
	@Test(expected = NullPointerException.class)
	public void testGetLowerBoundNullAsInputWithValidInput() {
		Range range = new Range((Double) null, 10);
		range.getLowerBound();
	}

	// TC37: Testing 1 positive whole number with positive high floating number
	@Test
	public void testGetLowerBoundPositiveWholeNumberWithPositiveHighFloatingNumber() {
		Range range = new Range(1, 9656.7378209372819);
		assertEquals("getLowerBound: Testing 1 positive whole number with positive high floating number", 1.0,
				range.getLowerBound(), 0.01);
	}

	// TC38: Testing when both values are Zero
	@Test
	public void testGetLowerBoundWhenBothValuesAreZero() {
		Range range = new Range(0, 0);
		assertEquals("getLowerBound: Testing when both values are Zero", 0.0, range.getLowerBound(), 0.01);
	}

	// Method Name: getUpperBound()

	// TC38.5: Testing 2 positive whole numbers
	@Test
	public void testGetUpperBoundPositiveWholeNumbers() {
		Range range = new Range(4, 9);
		assertEquals("getUpperBound: Testing 2 positive whole numbers", 9.0, range.getUpperBound(), 0.01);
	}

	// TC39: Testing 2 positive numbers with maximum input
	@Test
	public void testGetUpperBoundPositiveNumbersWithMaxInput() {
		Range range = new Range(4, 2147483647);
		assertEquals("getUpperBound: Testing 2 positive numbers with maximum input", 2147483647.0,
				range.getUpperBound(), 0.01);
	}

	// TC40: Testing 2 negative whole numbers
	@Test
	public void testGetUpperBoundNegativeWholeNumbers() {
		Range range = new Range(-9, -6);
		assertEquals("getUpperBound: Testing 2 negative whole numbers", -6.0, range.getUpperBound(), 0.01);
	}

	// TC41: Testing 2 whole negative numbers with negative maximum input
	@Test
	public void testGetUpperBoundNegativeNumbersWithNegativeMaxInput() {
		Range range = new Range(-2147483647, 6);
		assertEquals("getUpperBound: Testing 2 whole negative numbers with negative maximum input", 6.0,
				range.getUpperBound(), 0.01);
	}

	// TC42: Testing 2 positive floating numbers
	@Test
	public void testGetUpperBoundPositiveFloatingNumbers() {
		Range range = new Range(4.76, 9.73);
		assertEquals("getUpperBound: Testing 2 positive floating numbers", 9.73, range.getUpperBound(), 0.01);
	}

	// TC43: Testing 2 negative floating point
	@Test
	public void testGetUpperBoundNegativeFloatingPoint() {
		Range range = new Range(-9.73, -5.76);
		assertEquals("getUpperBound: Testing 2 negative floating point", -5.76, range.getUpperBound(), 0.01);
	}

	// TC44: Testing 1 positive and negative whole number
	@Test
	public void testGetUpperBoundPositiveAndNegativeWholeNumber() {
		Range range = new Range(-9, 10);
		assertEquals("getUpperBound: Testing 1 positive and negative whole number", 10.0, range.getUpperBound(), 0.01);
	}

	// TC45: Testing Zero and negative floating number
	@Test
	public void testGetUpperBoundZeroAndNegativeFloatingNumber() {
		Range range = new Range(-8, 0);
		assertEquals("getUpperBound: Testing Zero and negative floating number", 0.0, range.getUpperBound(), 0.01);
	}

	// TC46: Testing upper bound less than lower bound
	@Test(expected = IllegalArgumentException.class)
	public void testGetUpperBoundUpperBoundLessThanLowerBound() {
		Range range = new Range(10, -23.45);
		range.getUpperBound();
	}

	// TC47: Testing null as input with a valid input
	@Test(expected = NullPointerException.class)
	public void testGetUpperBoundNullAsInputWithValidInput() {
		Range range = new Range((Double) null, 10);
		range.getUpperBound();
	}

	// TC48: Testing 1 positive whole number with positive high floating number
	@Test
	public void testGetUpperBoundPositiveWholeNumberWithPositiveHighFloatingNumber() {
		Range range = new Range(1, 9656.7378209372819);
		assertEquals("getUpperBound: Testing 1 positive whole number with positive high floating number",
				9656.7378209372819, range.getUpperBound(), 0.01);
	}

	// TC49: Testing when both values are Zero
	@Test
	public void testGetUpperBoundWhenBothValuesAreZero() {
		Range range = new Range(0, 0);
		assertEquals("getUpperBound: Testing when both values are Zero", 0.0, range.getUpperBound(), 0.01);
	}

	// Method Name: toString()
	// TC50: Testing 2 positive whole numbers
	@Test
	public void testToStringPositiveWholeNumbers() {
		Range range = new Range(4, 9);
		assertEquals("toString: Testing 2 positive whole numbers", "Range[4.0,9.0]", range.toString());
	}

	// TC51: Testing 2 positive numbers with maximum input
	@Test
	public void testToStringPositiveNumbersWithMaxInput() {
		Range range = new Range(4, 2147483647);
		assertEquals("toString: Testing 2 positive numbers with maximum input", "Range[4.0,2.147483647E9]",
				range.toString());
	}

	// TC52: Testing 2 negative whole numbers
	@Test
	public void testToStringNegativeWholeNumbers() {
		Range range = new Range(-9, -6);
		assertEquals("toString: Testing 2 negative whole numbers", "Range[-9.0,-6.0]", range.toString());
	}

	// TC53: Testing 2 whole negative numbers with negative maximum input
	@Test
	public void testToStringNegativeNumbersWithNegativeMaxInput() {
		Range range = new Range(-2147483647, 6);
		assertEquals("toString: Testing 2 whole negative numbers with negative maximum input",
				"Range[-2.147483647E9,6.0]", range.toString());
	}

	// TC54: Testing 2 positive floating numbers
	@Test
	public void testToStringPositiveFloatingNumbers() {
		Range range = new Range(4.76, 9.73);
		assertEquals("toString: Testing 2 positive floating numbers", "Range[4.76,9.73]", range.toString());
	}

	// TC55: Testing 2 negative floating point
	@Test
	public void testToStringNegativeFloatingPoint() {
		Range range = new Range(-9.73, -5.76);
		assertEquals("toString: Testing 2 negative floating point", "Range[-9.73,-5.76]", range.toString());
	}

	// TC56: Testing 1 positive and negative whole number
	@Test
	public void testToStringPositiveAndNegativeWholeNumber() {
		Range range = new Range(-9, 10);
		assertEquals("toString: Testing 1 positive and negative whole number", "Range[-9.0,10.0]", range.toString());
	}

	// TC57: Testing Zero and negative floating number
	@Test
	public void testToStringZeroAndNegativeFloatingNumber() {
		Range range = new Range(-8, 0);
		assertEquals("toString: Testing Zero and negative floating number", "Range[-8.0,0.0]", range.toString());
	}

	// TC58: Testing upper bound less than lower bound
	@Test(expected = IllegalArgumentException.class)
	public void testToStringUpperBoundLessThanLowerBound() {
		Range range = new Range(10, -23.45);
		range.toString();
	}

	// TC59: Testing null as input with a valid input
	@Test(expected = NullPointerException.class)
	public void testToStringNullAsInputWithValidInput() {
		Range range = new Range((Double) null, 10);
		range.toString();
	}

	// TC60: Testing 1 positive whole number with positive high floating number
	@Test
	public void testToStringPositiveWholeNumberWithPositiveHighFloatingNumber() {
		Range range = new Range(1, 9656.7378209372819);
		assertEquals("toString: Testing 1 positive whole number with positive high floating number",
				"Range[1.0,9.656737820937282E3]", range.toString());
	}

	// TC61: Testing when both values are Zero
	@Test
	public void testToStringWhenBothValuesAreZero() {
		Range range = new Range(0, 0);
		assertEquals("toString: Testing when both values are Zero", "Range[0.0,0.0]", range.toString());
	}

	// TC62
	@Test(expected = IllegalArgumentException.class)
	public void testToStringWhenGivenBlankDoubles() {
		Range range = new Range(Double.NaN, Double.NaN);
		assertEquals("toString: Testing when both values are Zero", "Range[NaN,NaN]", range.toString());
	}
	
	// TC63: Testing getLength method for correct length calculation
	@Test
	public void testGetLengthForCorrectCalculation() {
	    // Arrange: Create a range with known lower and upper bounds
	    double lowerBound = 5.0;
	    double upperBound = 15.0;
	    Range range = new Range(lowerBound, upperBound); // Assuming Range(lower, upper) constructor

	    // Act: Calculate the length of this range
	    double length = range.getLength();

	    // Assert: Verify that the length is as expected
	    double expectedLength = upperBound - lowerBound;
	    assertEquals("getLength: Testing for correct length calculation", expectedLength, length, 0.0);
	}

	
	// TC64: Testing intersects method when lower is less than or equal to
	// this.lower and upper is greater than this.lower
	@Test
	public void testIntersectsWhenLowerIsLessThanOrEqualAndUpperIsGreaterThanThisLower() {
		// Arrange: Create a range where this.lower is known and both test lower and
		// upper are defined relative to it
		Range range = new Range(10, 20); // Assuming Range(lower, upper) constructor

		// Act: Check if the given lower and upper values intersect with this range
		boolean intersects = range.intersects(8, 15); // lower <= this.lower and upper > this.lower

		// Assert: Verify that the method returns true, as upper is greater than
		// this.lower
		assertEquals("intersects: Testing when lower <= this.lower and upper > this.lower", true, intersects);
	}

	// TC65: Testing intersects method when lower is less than or equal to
	// this.lower and upper is not greater than this.lower
	@Test
	public void testIntersectsWhenLowerIsLessThanOrEqualAndUpperIsNotGreaterThanThisLower() {
		// Arrange: Create a range with a known this.lower and define both test lower
		// and upper in relation to it
		Range range = new Range(10, 20); // Assuming Range(lower, upper) constructor

		// Act: Check if the given lower and upper values intersect with this range
		boolean intersects = range.intersects(9, 10); // lower <= this.lower and upper <= this.lower

		// Assert: Verify that the method returns false, as upper is not greater than
		// this.lower
		assertEquals("intersects: Testing when lower <= this.lower and upper <= this.lower", false, intersects);
	}

	// TC66: Testing intersects method when the lower bound is above this.lower and
	// the upper bound is below this.upper
	@Test
	public void testIntersectsOverlapWithLowerAboveThisLowerAndUpperBelowThisUpper() {
		Range range = new Range(5, 10);
		boolean result = range.intersects(6, 9);
		assertEquals("intersects: Testing with overlap and lower above this.lower and upper below this.upper", true,
				result);
	}

	// TC67: Testing intersects method for a case where the second range is
	// completely above the first range
	@Test
	public void testIntersectsNoOverlapWithLowerAboveThisLowerAndUpperAboveThisUpper() {
		Range range = new Range(5, 10);
		boolean result = range.intersects(11, 15);
		assertEquals("intersects: Testing with no overlap and lower above this.upper", false, result);
	}

	// TC68: Testing intersects method for a case where the second range's upper
	// bound is less than its lower bound
	@Test
	public void testIntersectsNoOverlapWithUpperBelowThisUpperAndLower() {
		Range range = new Range(5, 10);
		boolean result = range.intersects(6, 5);
		assertEquals("intersects: Testing with no overlap and upper below both this.upper and lower", false, result);
	}

	// TC69: Testing intersects method for a case where the second range is
	// completely below the first range
	@Test
	public void testIntersectsNoOverlapWithUpperBelowThisLower() {
		Range range = new Range(5, 10);
		boolean result = range.intersects(2, 4);
		assertEquals("intersects: Testing with no overlap and upper below this.lower", false, result);
	}

	// TC70: Testing intersects method for a case where the second range's lower
	// bound is above this.lower and the upper bound is not greater than lower
	@Test
	public void testIntersectsNoOverlapWithLowerAboveThisLowerAndUpperLessThanLower() {
		Range range = new Range(5, 10);
		boolean result = range.intersects(7, 6);
		assertEquals("intersects: Testing with no overlap and lower above this.lower and upper less than lower", false,
				result);
	}


}