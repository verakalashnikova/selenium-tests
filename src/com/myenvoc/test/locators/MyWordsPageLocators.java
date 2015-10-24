package com.myenvoc.test.locators;

public class MyWordsPageLocators implements Locators {
	private String locator;

	public MyWordsPageLocators(String locator) {
		this.locator = locator;
	}

	@Override
	public String getLocator() {
		return locator;
	}

}
