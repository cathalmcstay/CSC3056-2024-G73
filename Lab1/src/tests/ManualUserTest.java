package tests;

import model.User;

public class ManualUserTest {
	
	public static void main(String[] args) {
		User testUser = new User("Mike", "my_password", "Mike", "Smith", "07428030031");
	
		System.out.println(testUser);
	}

}
