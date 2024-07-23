package com.chainsys.creditcard.validation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class Validation {

	public boolean fName(String fName) {

		String pattern = "[A-Za-z]{2,20}";

		if (fName.matches(pattern)) {

			return true;
		}

		return false;

	}

	public boolean lName(String lName) {

		String pattern = "[A-Za-z]{2,20}";

		if (lName.matches(pattern)) {

			return true;
		}

		return false;

	}

	public String validDOB(String dobStr) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Date dob = sdf.parse(dobStr);

		return dob.toString();

	}

	public boolean aadhaar(String aadhaar) {

		String pattern = "[1-9]{1}[0-9]{11}";

		if (aadhaar.matches(pattern)) {

			return true;
		}

		return false;

	}

	public boolean pan(String pan) {

		String pattern = "[A-Z]{5}[0-9]{4}[A-Z]{1}";

		if (pan.matches(pattern)) {

			return true;
		}

		return false;

	}

	public boolean mail(String mail) {

		String pattern = "^[a-zA-Z0-9+_.-]+@gmail.com$";

		if (mail.matches(pattern)) {

			return true;
		}

		return false;

	}

	public boolean phone(String phone) {

		String pattern = "[0-9]{10}";

		if (phone.matches(pattern)) {

			return true;
		}

		return false;

	}

	public boolean password(String password) {

		String pattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+.])(?=.*\\d).{8,}$";

		if (password.matches(pattern)) {

			return true;
		}

		return false;

	}

}
