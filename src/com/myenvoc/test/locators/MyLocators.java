package com.myenvoc.test.locators;

public class MyLocators {
	public static final String SUGGESTED_WORDS_POP_UP_XPath = "//td[@class='item']";
	public static final String SUGGESTED_WORDS_POP_UP_CLASS_NAME = "item";
	public static final String WORD_TYPING_FIELD_TO_TRANSLATE_PART_CLASS_NAME = "gwt-SuggestBox";
	public static final String WORD_FIELD_FOR_TRANSLATION_XPATH = "//input[@type='text']";
	public static final String TRANSLATE_BUTTON_PART_CLASS_NAME = "gwt-Button";
	public static final String LANGUAGE_SELECTOR_ID = "langSelect";
	public static final String INTERFACE_LANGUAGE_OPTIONS_ENGLISH = "//select[@class='gwt-ListBox']/option[1]";
	public static final String INTERFACE_LANGUAGE_OPTIONS = "//select[@class='gwt-ListBox']";
	public static final String LOGIN_ID = "loginPanelTrigger";
	public static final String LOGIN_FIELD_ID = "j_username";
	public static final String PASSWORD_FIELD_ID = "j_password";
	public static final String LOGIN_BUTTON_ID = "loginSubmitBtn";
	public static final String SIGN_OUT_PART_LINK_TEXT = "Sign out";
	public static final String MY_WORDS_MENU_ITEM_TEXT_LINK = "My words";
	// My word page
	public static final String TRANSLATION_CLICK_TO_ADD_XPATH = "//span[@title='Click to add translation']";
	public static final String TRANSLATION_TAB_LINK_TEXT = "Translation";
	public static final String DEFINITION_TAB_LINK_TEXT = "Definition";
	public static final String TRANSLATION_TEXT_AREA_XPATH = "//span[contains(., 'Translation')]/../div/textarea";
	public static final String TRANSLATION_MY_CONTEXT_AREA_XPATH = "//span[contains(., 'My Context')]/../div/textarea";
	public static final String WORD_TRANSLATION_OPTIONS_XPATH = "//span[@title='Click to add translation']";

	private MyLocators() {

	}

	public static final Locator TRANSLATE_BUTTON_PART_CLASS_NAME_LOC = new SimpleLocator("gwt-Button");
	public static final Locator TRANSLATION_CLICK_TO_ADD_XPATH_LOC = new LanguageDependentLocator(
			"//span[@title='Click to add translation']");

	interface Locator {
		String getLocator();
	}

	static class SimpleLocator implements Locator {
		private final String locator;

		public SimpleLocator(String locator) {
			this.locator = locator;
		}

		@Override
		public String getLocator() {
			return locator;
		}

	}

	static class LanguageDependentLocator implements Locator {
		private final String locator;

		public LanguageDependentLocator(String locator) {
			this.locator = locator;
		}

		@Override
		public String getLocator() {
			// TODO: check that user iface is in ENG
			return locator;
		}

	}

}
