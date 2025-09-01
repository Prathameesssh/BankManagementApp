package com.aurionpro.utils;

import com.aurionpro.model.Account;

public class AuthBalance {

	public static boolean checkSenderBalance(Account fromAccount, double transferAmount) {

		if (fromAccount == null || transferAmount<=0 || !fromAccount.isFreezed()) {
			return false;
		}

		return fromAccount.getBalance() >= transferAmount;
	}

}
