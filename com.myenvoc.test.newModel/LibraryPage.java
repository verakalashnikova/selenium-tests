public class LibraryPage {
	// constants
	private static final String MY_WORDS_PAGE_PART_URL = "!my-words";
	// locators
	private static final String MY_WORDS_MENU_ITEM_TEXT_LINK = "My words";
	private static final String INTERFACE_LANGUAGE_OPTIONS_ENGLISH = "//select[@class='gwt-ListBox']/option[1]";
	private static final String INTERFACE_LANGUAGE_OPTIONS = "//select[@class='gwt-ListBox']";
	private PageNavigator pageNavigator;

	public LibraryPage(PageNavigator pageNavigator) {
		this.pageNavigator = pageNavigator;
	}

	public MyWordsPage goToMyWordsPage() {
		selectEnglishInterfaceLanguage();
		clickOnMyWordsTab();
		if (myWordsPageIsDisplayed()) {
			return new MyWordsPage(pageNavigator);
		} else {
			return null;
		}
	}

	private boolean myWordsPageIsDisplayed() {
		String currentPageURL = pageNavigator.getPageUrl();
		if (currentPageURL.contains(MY_WORDS_PAGE_PART_URL)) {
			return true;
		} else {
			return false;
		}
	}

	private void clickOnMyWordsTab() {
		pageNavigator.findElementByLinkText(MY_WORDS_MENU_ITEM_TEXT_LINK).click();

	}

	private void selectEnglishInterfaceLanguage() {
		pageNavigator.findElementByXPath(INTERFACE_LANGUAGE_OPTIONS).click();
		pageNavigator.findElementByXPath(INTERFACE_LANGUAGE_OPTIONS_ENGLISH).click();

	}

}
