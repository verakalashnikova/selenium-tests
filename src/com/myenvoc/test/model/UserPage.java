package com.myenvoc.test.model;

public class UserPage {
	private final PageController pageEngine;

	public UserPage(PageController pageEngine) {
		this.pageEngine = pageEngine;
	}

	/**
	 * Logs in a user. The method is safe to call even if a user has already
	 * been logged in, in which case he will be logged out.
	 * 
	 * @throws InterruptedException
	 */
	public boolean loginUser() {
		if (isUserLoggedIn()) {
			logOut();
		}
		// Interface language should be English or we would not be
		// able to check anything
		changeInterfaceLanguage();

		login(Constants.USER_LOGIN, Constants.USER_PASSWORD);
		if (isUserLoggedIn()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isUserLoggedIn() {
		String currentPageURL = pageEngine.getPageUrl();
		if (currentPageURL.endsWith(Constants.HOME_PAGE_PART_URL_FOR_VALIDATION)) {
			return true;
		} else {
			return false;
		}
	}

	public void checkUserIsLoggedIn() {
		if (!isUserLoggedIn()) {
			throw new AuthenticationRequiredException("User must be logged in");
		}
	}

	public void logOut() {
		pageEngine.findElementByLinkText(MyLocators.SIGN_OUT_PART_LINK_TEXT).click();
	}

	private void login(String userLogin, String userPassword) {
		pageEngine.findElementById(MyLocators.LOGIN_ID).click();
		// 4. Type user name and password
		pageEngine.findElementById(MyLocators.LOGIN_FIELD_ID).sendKeys(userLogin);
		pageEngine.findElementById(MyLocators.PASSWORD_FIELD_ID).sendKeys(userPassword);
		// 5. Press Log in button
		pageEngine.findElementById(MyLocators.LOGIN_BUTTON_ID).click();
	}

	private void changeInterfaceLanguage() {
		pageEngine.findElementById(MyLocators.LANGUAGE_SELECTOR_ID).click();
		pageEngine.findElementByLinkText(Constants.INTERFACE_LANGUAGE).click();
	}

}
