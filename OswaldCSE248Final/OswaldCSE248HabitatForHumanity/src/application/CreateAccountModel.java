package application;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccountModel {

	public boolean checkPassword(String password, String rePassword) {
		if (password.equals(rePassword)) {
			return true;
		}
		return false;
	}
	
	public boolean checkPasswordCriteria(String password){
		if (password.length() < 5 || checkForUppercase(password) == false ) {
				return false;
		}
		return true;

	}
	
	public boolean checkForUppercase(String password) {
		for (int i = 0; i < password.length(); i++) {
			if (Character.isUpperCase(password.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean checkEmailFormat(String email) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches()) {
			return true;
		}
		return false;
	}
	
	public boolean checkEmail(String email){
		// check with database if the email is already used.
		return true;
	}

	public boolean checkUsername(String userName) {
		// check with the database if the username already exists
		return true;
	}

	public void createNewAccount() {
		// will run after the account has been verified as not a duplicate and
		// all correct

		// inserts the new account into the right format into the database.
	}
}
