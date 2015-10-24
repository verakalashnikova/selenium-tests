package com.myenvoc.test.model;

import com.myenvoc.test.locators.MyLocators;

public class HomePage {
	private final PageController pageEngine;

	public HomePage(PageController pageEngine) {
		this.pageEngine = pageEngine;
	}

	public void goToMyWordsPage(UserPage user) {
		if (user.isUserLoggedIn()) {
			// TODO: Go to MyWords Page
			pageEngine.findElementByXPath(MyLocators.INTERFACE_LANGUAGE_OPTIONS).click();
			pageEngine.findElementByXPath(MyLocators.INTERFACE_LANGUAGE_OPTIONS_ENGLISH).click();
			// 7. Assumption: Library page is displayed
			// go to My words page
			pageEngine.findElementByLinkText(MyLocators.MY_WORDS_MENU_ITEM_TEXT_LINK).click();
			// check that My words page is displayed
			if (isUserOnHomePage()) {
				// TODO: method has to be implemented
			} else {
				return;
			}
		} else {
			return;
		}
	}

	public boolean isUserOnHomePage() {
		String currentPageURL = pageEngine.getPageUrl();
		if (currentPageURL.endsWith(Constants.MY_WORDS_PAGE_PART_URL_FOR_VALIDATION)) {
			return true;
		} else {
			return false;
		}
	}
}
