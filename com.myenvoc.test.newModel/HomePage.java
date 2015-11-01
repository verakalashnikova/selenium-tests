import java.util.concurrent.ThreadLocalRandom;

import org.openqa.selenium.support.ui.Select;

public class HomePage {
	// constants
	public static final String USER_LOGIN = "tester1@test.com";
	public static final String USER_PASSWORD = "111111";
	public static final String LIBRARY_PAGE_PART_URL_FOR_VALIDATION = "#!library";
	public static final String INTERFACE_LANGUAGE = "English";
	// locators
	public static final String LOGIN_ID = "loginPanelTrigger";
	// public static final String LOGIN_FIELD_ID = "j_username";
	// public static final String PASSWORD_FIELD_ID = "j_password";
	public static final String SIGN_UP_ID = "signUpPanelTrigger";
	private static final String EMAIL_FIELD_ID = "email";
	private static final String PASSWORD_FIELD_ID = "password";
	public static final String LOGIN_BUTTON_ID = "loginSubmitBtn";
	public static final String SIGN_OUT_PART_LINK_TEXT = "Sign out";
	public static final String LANGUAGE_SELECTOR_ID = "langSelect";
	private static final String SIGN_UP_BUTTON_ID = "registerSubmitBtn";
	private static final String NATIVE_LANGUAGE_SELECT_ID = "nativeLanguage";
	private static final String NATIVE_LANGUAGE_VALUE = "ru";

	private PageNavigator pageNavigator;

	public HomePage(PageNavigator pageNavigator) {
		this.pageNavigator = pageNavigator;
	}

	public LibraryPage loginUser() {
		if (isUserLoggedIn()) {
			logOut();
		}

		return login();
	}

	private LibraryPage login() {
		clickOnLogIn();
		typeUserLogin();
		typeUserPassword();
		return clickLoginAndGoToLibraryPage();
	}

	public boolean isUserLoggedIn() {
		if (userLocatesOnLibraryPage()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean userLocatesOnLibraryPage() {
		String currentPageURL = pageNavigator.getPageUrl();
		if (currentPageURL.endsWith(LIBRARY_PAGE_PART_URL_FOR_VALIDATION)) {
			return true;
		} else {
			return false;
		}
	}

	private LibraryPage clickLoginAndGoToLibraryPage() {
		pageNavigator.findElementById(LOGIN_BUTTON_ID).click();
		return new LibraryPage(pageNavigator);
	}

	private void typeUserPassword() {
		String userPassword = generateUserPassword();
		pageNavigator.findElementById(PASSWORD_FIELD_ID).sendKeys(userPassword);

	}

	private String generateUserPassword() {
		return "password" + generateRandomNumber();
	}

	private void typeUserLogin() {
		String userLogin = generateUserLogin();
		pageNavigator.findElementById(EMAIL_FIELD_ID).sendKeys(userLogin);

	}

	private String generateUserLogin() {
		return "User" + generateRandomNumber();
	}

	public int generateRandomNumber() {
		int min = 1;
		int max = 100;
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

	private void clickOnLogIn() {
		pageNavigator.findElementById(LOGIN_ID).click();

	}

	public void logOut() {
		pageNavigator.findElementByLinkText(SIGN_OUT_PART_LINK_TEXT).click();
	}

	public void changeInterfaceLanguage() {
		pageNavigator.findElementById(LANGUAGE_SELECTOR_ID).click();
		pageNavigator.findElementByLinkText(INTERFACE_LANGUAGE).click();
	}

	public LibraryPage userRegistration() {
		clickOnRegistrate();
		typeUserLogin();// user has to be a new one every time
		typeUserPassword();// password has to be a new one every time
		selectNativeLanguage();
		return clickSignUpAndGoToLibraryPage();
	}

	private void clickOnRegistrate() {
		pageNavigator.findElementById(SIGN_UP_ID).click();

	}

	private void selectNativeLanguage() {
		Select dropDown = new Select(pageNavigator.findElementById(NATIVE_LANGUAGE_SELECT_ID));
		dropDown.selectByValue(NATIVE_LANGUAGE_VALUE);
	}

	private LibraryPage clickSignUpAndGoToLibraryPage() {
		pageNavigator.findElementById(SIGN_UP_BUTTON_ID).click();
		return new LibraryPage(pageNavigator);
	}

}
