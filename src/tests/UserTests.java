package tests;

import model.User;
import utils.TestUtils;

public class UserTests {

	public static void main(String[] args) {
		testUserConstructor();

	}
	
	public static void testUserConstructor() {
        String test_username = "Mike";
        String test_password = "my_password";
        String test_first_name = "Mike";
        String test_last_name = "Smith";
        String test_mobile_number = "07428030031";

        User testUser = new User(test_username, test_password, test_first_name, test_last_name, test_mobile_number);

        System.out.println("Starting the assertions of the test method: testUserConstructor");

        // Testing username
        if (testUser.getUsername() == (test_username)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUsername-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUsername-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }

        // Testing password
        if (testUser.getPassword() == (test_password)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getPassword-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getPassword-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }

        // Testing first name
        if (testUser.getFirst_name() == (test_first_name)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getFirst_name-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getFirst_name-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }

        // Testing last name
        if (testUser.getLast_name() == (test_last_name)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getLast_name-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getLast_name-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }

        // Testing mobile number
        if (testUser.getMobile_number() == (test_mobile_number)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC5-getMobile_number-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC5-getMobile_number-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //assert Username
        assert testUser.getUsername() == test_username;
        //assert Password
        assert testUser.getPassword() == test_password;
        //assert First Name
        assert testUser.getFirst_name() == test_first_name;
        //assert Second Name
        assert testUser.getLast_name() == test_last_name;
        //assert Mobile Number
        assert testUser.getMobile_number() == test_mobile_number;
        
        System.out.println("All Java assertations in the test suite passed (None failed)");
        
    }

}
