package com.myenvoc.test.locators;

public class HomePageLocators implements Locators {

	private String locator;

	public HomePageLocators(String locator) {
		this.locator = locator;

	}

	@Override
	public String getLocator() {
		return locator;
	}

}
