package org.jfree.data;

import junit.framework.TestCase;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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

	// TC63: Testing with a positive delta value
	@Test
	public void testShiftWithPositiveDelta() {
		Range range = new Range(1, 5);
		Range expectedRange = new Range(6.0, 10.0);
		assertEquals("shift: Testing with a positive range and positive delta",
				expectedRange, Range.shift(range, 5));
	}

	// TC63: Testing with a negative delta value
	@Test
	public void testShiftWithNegativeDelta() {
		Range range = new Range(1, 5);
		Range expectedRange = new Range(0.0, 3.0);
		assertEquals("shift: Testing with a positive range and negative delta",
				expectedRange, Range.shift(range, -2));
	}

	// TC64: Testing with a negative delta value and allowZeroCrossing enabled
	@Test
	public void testShiftWithNegativeDeltaAndAllowZeroCrossing() {
		Range range = new Range(1, 5);
		Range expectedRange = new Range(-1.0, 3.0);
		assertEquals("shift: Testing with a positive range and negative delta",
				expectedRange, Range.shift(range, -2, true));

	}

	// TC65: Testing with a positive delta value and allowZeroCrossing enabled
	@Test
	public void testShiftWithPositiveDeltaAndNoAllowZeroCrossing() {
		Range range = new Range(-10, -1);
		Range expectedRange = new Range(-7.0, 0.0);
		assertEquals("shift: Testing with a positive range and positive delta",
				expectedRange, Range.shift(range, 3, false));
	}

	// TC65: Testing with a positive delta value, allowZeroCrossing disabled and a
	// zero Range
	@Test
	public void testShiftWithPositiveDeltaAndAllowZeroCrossing() {
		Range range = new Range(0.0, 0.0);
		Range expectedRange = new Range(3.0, 6.0);
		assertEquals("shift: Testing with a positive range and positive delta",
				expectedRange, Range.shift(range, 3, true));
	}

	// TC66: Testing Intersect with positive range with value inside range
	@Test
	public void testIntersectWithValueInside() {
		Range range = new Range(3, 7);
		assertEquals("intersect: Testing with a positive range and a range that is fully within the range",
				true, range.intersects(2, 5));
	}

	// TC67: Testing Intersect with positive range with higher value less than or
	// equal to given lower range
	@Test
	public void testIntersectWithUpperEqualToLower() {
		Range range = new Range(3, 7);
		assertEquals("intersect: Testing with a positive range and upper value is equal to this.lower",
				false, range.intersects(2, 3));
	}

	// TC68: Testing Intersect with lower greater than this.lower
	@Test
	public void testIntersectWithLowerGreaterThanLower() {
		Range range = new Range(3, 7);
		assertEquals("intersect: Testing with a positive range and lower is equal to this.lower",
				true, range.intersects(4, 6));
	}

	// TC69: Testing Intersect with upper equal to this.upper
	@Test
	public void testIntersectWithUpperEqualToUpper() {
		Range range = new Range(3, 7);
		assertEquals("intersect: Testing with a positive range and range including this range's upper bound",
				false, range.intersects(4, 7));
	}

	// TC70: Testing Intersect with upper greater than this.upper
	@Test
	public void testIntersectWithUpperGreaterThanUpper() {
		Range range = new Range(3, 7);
		assertEquals("intersect: Testing with a positive range and range greater than this range",
				false, range.intersects(6, 8));
	}

	// TC71: Testing Intersect with upper greater than this.upper
	@Test
	public void testIntersectWithRangeOutOfRange() {
		Range range = new Range(3, 7);
		assertEquals("intersect: Testing with a positive range and range greater than this range",
				false, range.intersects(4, 3));
	}

	// TC72: Testing getLength if Upper > Lower
	@Test
	public void testGetLengthWithUpperGreaterThanLower() {
		Range range = new Range(3.0, 7.0);
		assertEquals("getLength: Testing with upper greater than lower", 4.0, range.getLength(), 0.001);
	}

	// TC73: Testing getLength if Upper == Lower
	@Test
	public void testGetLengthWithUpperEqualToLower() {
		Range range = new Range(5.0, 5.0);
		assertEquals("getLength: Testing with upper equal to lower", 0.0, range.getLength(), 0.001);
	}

	// TC74: Testing hashCode gives the same value for the same range
	@Test
	public void testHashCodeConsistency() {
		Range range = new Range(3.0, 7.0);
		int hash1 = range.hashCode();
		int hash2 = range.hashCode();
		assertEquals("hashCode: Testing consistency of hash code", hash1, hash2);
	}

	// TC74.5: Testing hashCode gives the different values for different ranges
	@Test
	public void testHashCodeDifferentRanges() {
		Range range = new Range(3.0, 7.0);
		int hash1 = range.hashCode();
		int hash2 = range.hashCode();
		assertFalse("hashCode: Testing consistency of hash code", hash1 == hash2);
	}

	// TC75: Testing Equals if given a non-Range object
	@Test
	public void testEqualsWithNonRangeObject() {
		Range range = new Range(3.0, 7.0);
		Object nonRangeObject = new Object();
		assertEquals("equals: Testing with a non-Range object", false, range.equals(nonRangeObject));
	}

	// TC76: Testing Equals if the lower of given range is not equal to the lower of
	// defined range
	@Test
	public void testEqualsWithDifferentLower() {
		Range range1 = new Range(3.0, 7.0);
		Range range2 = new Range(2.0, 7.0);
		assertEquals("equals: Testing with a different lower bound", false, range1.equals(range2));
	}

	// TC77: Testing Expand when range is not null
	@Test
	public void testExpandWithNonNullRange() {
		Range inputRange = new Range(2, 6);
		Range expectedRange = new Range(1, 8);
		assertEquals("expand: Testing with non-null range", expectedRange, Range.expand(inputRange, 0.25, 0.5));
	}

	// TC78: Testing Expand when range is null
	@Test(expected = IllegalArgumentException.class)
	public void testExpandWithNullRange() {
		Range inputRange = null;
		Range.expand(inputRange, 0.25, 0.5);
	}

	// TC79: Testing ExpandToInclude with null range
	@Test
	public void testExpandToIncludeWithNullRange() {
		Range expectedRange = new Range(5.0, 5.0);
		assertEquals("expandToInclude: Testing with null range", expectedRange, Range.expandToInclude(null, 5.0));
	}

	// TC80: Testing ExpandToInclude with value less than lower bound
	@Test
	public void testExpandToIncludeWithValueLessThanLowerBound() {
		Range inputRange = new Range(3.0, 7.0);
		Range expectedRange = new Range(2.0, 7.0);
		assertEquals("expandToInclude: Testing with value less than lower bound", expectedRange,
				Range.expandToInclude(inputRange, 2.0));
	}

	// TC81: Testing ExpandToInclude with value greater than upper bound
	@Test
	public void testExpandToIncludeWithValueGreaterThanUpperBound() {
		Range inputRange = new Range(3.0, 7.0);
		Range expectedRange = new Range(3.0, 8.0);
		assertEquals("expandToInclude: Testing with value greater than upper bound", expectedRange,
				Range.expandToInclude(inputRange, 8.0));
	}

	// TC82: Testing ExpandToInclude with value within range
	@Test
	public void testExpandToIncludeWithValueWithinRange() {
		Range inputRange = new Range(3.0, 7.0);
		assertEquals("expandToInclude: Testing with value within range", inputRange,
				Range.expandToInclude(inputRange, 5.0));
	}

	// TC83: Testing Combine with null range1 and non-null range2
	@Test
	public void testCombineWithNullRange1() {
		Range range2 = new Range(3.0, 7.0);
		assertEquals("combine: Testing with null range1", range2, Range.combine(null, range2));
	}

	// TC84: Testing Combine with non-null range1 and null range2
	@Test
	public void testCombineWithNullRange2() {
		Range range1 = new Range(3.0, 7.0);
		assertEquals("combine: Testing with null range2", range1, Range.combine(range1, null));
	}

	// TC85: Testing Combine with both ranges null
	@Test
	public void testCombineWithBothRangesNull() {
		assertEquals("combine: Testing with both ranges null", null, Range.combine(null, null));
	}

	// TC86: Testing Combine with both ranges non-null
	@Test
	public void testCombineWithNonNullRanges() {
		Range range1 = new Range(3.0, 7.0);
		Range range2 = new Range(1.0, 5.0);
		Range expectedRange = new Range(1.0, 7.0);
		assertEquals("combine: Testing with non-null ranges", expectedRange, Range.combine(range1, range2));
	}

}