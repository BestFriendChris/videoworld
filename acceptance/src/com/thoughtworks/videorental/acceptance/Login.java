package com.thoughtworks.videorental.acceptance;

// JUnit Assert framework can be used for verification

import net.sf.sahi.client.Browser;

public class Login {

	private Browser browser;

	public Login(Browser browser) {
		this.browser = browser;
	}

	public void loginAsWithPassword(String userName, String password) throws Exception {
		browser.navigateTo("http://localhost:8081/login");
		browser.textbox("username").setValue(userName);
		browser.password("password").setValue(password);
		browser.submit("login").click();
	}

}
