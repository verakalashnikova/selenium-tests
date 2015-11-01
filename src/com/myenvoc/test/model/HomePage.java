package com.myenvoc.test.model;

import java.util.List;

import org.openqa.selenium.WebElement;

public class HomePage {
	private final PageController pageEngine;

	public HomePage(PageController pageEngine) {
		this.pageEngine = pageEngine;
	}

	public void goToMyWordsPage(UserPage user) {
		if (user.isUserLoggedIn()) {
			pageEngine.findElementByXPath(MyLocators.INTERFACE_LANGUAGE_OPTIONS).click();
			pageEngine.findElementByXPath(MyLocators.INTERFACE_LANGUAGE_OPTIONS_ENGLISH).click();
			pageEngine.findElementByLinkText(MyLocators.MY_WORDS_MENU_ITEM_TEXT_LINK).click();
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

	public void isWordAdded() {
		pageEngine.findElementsByXPath(MyLocators.MY_WORDS_LIST_XPATH);

	}

	public void deleteWord() {
		List<WebElement> tableElementsInThePage = pageEngine.findElementsByTagName("table");
		System.out.println(tableElementsInThePage.size());
	}
}
