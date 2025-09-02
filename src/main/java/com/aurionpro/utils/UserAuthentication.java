package com.aurionpro.utils;

public class UserAuthentication {

	public static boolean validate(String name, String address, String email, String password, long mobile,
			long aadharNo) {
		// Name validation
		if (name == null || name.trim().isEmpty())
			return false;

		// Address validation
		if (address == null || address.trim().isEmpty())
			return false;

		// Email validation (simple regex)
		if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
			return false;

		// Password validation (at least 6 chars)
		if (password == null || password.length() < 6)
			return false;

		if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*"))
			return false;

		// Mobile validation (10 digits)
		if (String.valueOf(mobile).length() != 10)
			return false;

		// Aadhaar validation (12 digits)
		if (String.valueOf(aadharNo).length() != 12)
			return false;

		return true;
	}

	public boolean validate(String name, String address, String email, long mobile) {
		// Name validation
		if (name == null || name.trim().isEmpty())
			return false;

		// Address validation
		if (address == null || address.trim().isEmpty())
			return false;

		// Email validation (simple regex)
		if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
			return false;

		// Mobile validation (10 digits)
		if (String.valueOf(mobile).length() != 10)
			return false;

		return true;
	}
}
