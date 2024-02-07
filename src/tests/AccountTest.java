package tests;

import model.Account;
import utils.TestUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AccountTest {

	public static void main(String[] args) {
		testConstructor();
		testSetters();

	}
	
	public static void testConstructor() {
		System.out.println("Starting the assertions of the test method: testConstructor");
        
        String test_account_number = "12345";
        String test_username = "Cathal";
        String test_account_type = "Standard";
        Date test_account_opening_date = new Date();
        
        Account testAccount = new Account(test_account_number, test_username, test_account_type, test_account_opening_date);
        
        // Test account number
        if (testAccount.getAccount_number() == test_account_number) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccount_number-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccount_number-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        // Test username
        if (testAccount.getUsername_of_account_holder() == test_username) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getUsername_of_account_holder-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getUsername_of_account_holder-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        // Test account type
        if (testAccount.getAccount_type() == test_account_type) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getAccount_type-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getAccount_type-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        // Test account opening date
        if (testAccount.getAccount_opening_date() == test_account_opening_date) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getAccount_opening_date-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getAccount_opening_date-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //assert Account Number
        assert testAccount.getAccount_number() == test_account_number : "Account number assertion failed";
        
        //assert Username of Account Holder
        assert testAccount.getUsername_of_account_holder() == test_username : "Username of account holder assertion failed";
        
        //assert Account Type
        assert testAccount.getAccount_type() == test_account_type : "Account type assertion failed";
        
        //assert Account Opening Date
        assert testAccount.getAccount_opening_date() == test_account_opening_date : "Account opening date assertion failed";
        
        System.out.println("All Java assertions in the test suite passed (None failed)");
    
		
	}
	
	public static void testSetters() {
		System.out.println("Starting the assertions of the test method: testSetters");
		
		String test_account_number = "6789";
        String test_username = "Justin";
        String test_account_type = "Savings";
        Date test_account_opening_date = new Date();
        
        Account testAccount = new Account(null, null, null, null);
        
        testAccount.setAccount_number(test_account_number);
        testAccount.setUsername_of_account_holder(test_username);
        testAccount.setAccount_type(test_account_type);
        testAccount.setAccount_opening_date(test_account_opening_date);
        
     // Test account number
        if (testAccount.getAccount_number() == test_account_number) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccount_number-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccount_number-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        // Test username
        if (testAccount.getUsername_of_account_holder() == test_username) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getUsername_of_account_holder-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getUsername_of_account_holder-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        // Test account type
        if (testAccount.getAccount_type() == test_account_type) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getAccount_type-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getAccount_type-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        // Test account opening date
        if (testAccount.getAccount_opening_date() == test_account_opening_date) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getAccount_opening_date-Passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getAccount_opening_date-FAILED" + TestUtils.TEXT_COLOR_RESET);
        }
        
        //assert Account Number
        assert testAccount.getAccount_number() == test_account_number : "Account number assertion failed";
        
        //assert Username of Account Holder
        assert testAccount.getUsername_of_account_holder() == test_username : "Username of account holder assertion failed";
        
        //assert Account Type
        assert testAccount.getAccount_type() == test_account_type : "Account type assertion failed";
        
        //assert Account Opening Date
        assert testAccount.getAccount_opening_date() == test_account_opening_date : "Account opening date assertion failed";
        
        System.out.println("All Java assertions in the test suite passed (None failed)");
		
	} 	

}
