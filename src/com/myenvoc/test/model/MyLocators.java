package com.myenvoc.test.model;

public class MyLocators {
	public static final String LANGUAGE_SELECTOR_ID = "langSelect";
	public static final String INTERFACE_LANGUAGE_OPTIONS_ENGLISH = "//select[@class='gwt-ListBox']/option[1]";
	public static final String INTERFACE_LANGUAGE_OPTIONS = "//select[@class='gwt-ListBox']";
	public static final String LOGIN_ID = "loginPanelTrigger";
	public static final String LOGIN_FIELD_ID = "j_username";
	public static final String PASSWORD_FIELD_ID = "j_password";
	public static final String LOGIN_BUTTON_ID = "loginSubmitBtn";
	public static final String SIGN_OUT_PART_LINK_TEXT = "Sign out";
	public static final String MY_WORDS_MENU_ITEM_TEXT_LINK = "My words";
	// My words page
	public static final String SUGGESTED_WORDS_POP_UP_XPath = "//td[@class='item']";
	public static final String SUGGESTED_WORDS_POP_UP_CLASS_NAME = "item";
	public static final String WORD_TYPING_FIELD_TO_TRANSLATE_PART_CLASS_NAME = "gwt-SuggestBox";
	public static final String WORD_FIELD_FOR_TRANSLATION_XPATH = "//input[@type='text']";
	public static final String TRANSLATE_BUTTON_PART_CLASS_NAME = "gwt-Button";
	public static final String MY_WORDS_LIST_XPATH = "//tbody/tr/td/a";
	// Word page
	public static final String TRANSLATION_CLICK_TO_ADD_XPATH = "//span[@title='Click to add translation']/a";
	public static final String TRANSLATION_TAB_LINK_TEXT = "Translation";
	public static final String DEFINITION_TAB_LINK_TEXT = "Definition";
	public static final String IMAGES_TAB_LINK_TEXT = "Images";
	public static final String TRANSLATION_TEXT_AREA_XPATH = "//span[contains(., 'Translation')]/../div/textarea";
	public static final String DEFINITION_TEXT_AREA_XPATH = "//span[contains(., 'My Context')]/../div[last()-1]/textarea";
	public static final String TRANSLATION_MY_CONTEXT_AREA_XPATH = "//span[contains(., 'My Context')]/../div/textarea";
	public static final String WORD_TRANSLATION_OPTIONS_ON_TRANSLATION_TAB_XPATH = "//span[@title='Click to add translation']";
	public static final String TRANSLATIONS_ON_USER_WORD_CARD = "//div[@class='gwt-Label']";
	public static final String WORD_DEFINITION_OPTIONS_ON_DEFINITION_TAB_XPATH = "//span[@title='Click to add context']";
	public static final String DEFINITIONS_ON_USER_WORD_CARD = "//span[@class='gwt-InlineLabel']";
	public static final String IMAGE_PLACE_XPATH = "//div[@title='Click to edit']/img";
	public static final String IMAGES_XPATH = "//td/img[@src[contains(., 'http://t')]]";
	public static final String TAGS_INPUT_FIELD_XPATH = "//input[@title='Comma separated list of tags']";
	public static final String ADD_WORD_BUTTON_XPATH = "//button[contains(., 'Add word')]";
	public static final String CANCEL_LINK_TEXT = "Cancel";

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
