package com.myenvoc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.myenvoc.test.model.AuthenticationRequiredException;
import com.myenvoc.test.model.Constants;
import com.myenvoc.test.model.HomePage;
import com.myenvoc.test.model.MyWebDriver;
import com.myenvoc.test.model.MyWordsPage;
import com.myenvoc.test.model.PageController;
import com.myenvoc.test.model.UserPage;
import com.myenvoc.test.model.WordPage;

/**
 * Add new word test.
 */
public class WordCardVerificationTest {
	public WebDriver webDriver;
	private PageController pageController;
	private HomePage homePage;
	private WordPage wordPage;
	private UserPage user;
	private MyWordsPage myWordsPage;

	@Before
	public void setUp() throws Exception {
		webDriver = (new MyWebDriver(new FirefoxDriver())).getWebdriver();
		pageController = new PageController(webDriver);
		homePage = new HomePage(pageController);
		user = new UserPage(pageController);
		wordPage = new WordPage(pageController);
		myWordsPage = new MyWordsPage(pageController);
	}

	@After
	public void tearDown() throws Exception {
		// webDriver.close();
	}

	// @Test
	public void shouldNotGoToMywordsPageIfNotLoggedIn() throws NoSuchElementException, InterruptedException {
		try {
			pageController.goToTestUrl(Constants.TESTING_URL);
			homePage.goToMyWordsPage(user);
			fail("Should not go here, as the user wasn't logged in");
		} catch (AuthenticationRequiredException e) {
			// Expected, as the use was not logged in
		}
	}

	// @Test
	public void shouldGoToMywordsPageIfLoggedIn() throws InterruptedException {
		pageController.goToTestUrl(Constants.TESTING_URL);
		user.loginUser();
		homePage.goToMyWordsPage(user);
	}

	@Ignore
	public void wordCardUIVerificationTest() {
		// I'm not sure what assertion should I make
		fail("Not yet implemented");
	}

	// @Test
	public void goToWordPage() throws Exception {
		pageController.goToTestUrl(Constants.TESTING_URL);
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		assertEquals(wordPage.amInWordPage(), true);
	}

	@Ignore
	public void newHoveredTranslationIsDisplayedInWordTranslationSection() {
		pageController.goToTestUrl(Constants.TESTING_URL);
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		// TODO: I was not able to implement this method because the webdriver
		// cannot see the value in the field for translation
		assertEquals(wordPage.hoverNewTranslationandCheckItInTranslationsField(), true);

	}

	@Ignore
	public void newHoveredDefinitionIsDisplayedInWordDefinitionSection() {
		pageController.goToTestUrl(Constants.TESTING_URL);
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnDefinitionTab();
	}

	@Ignore
	public void newImageIsDisplayedInWordImageSection() {

	}

	@Test
	public void newTranslationIsAdded() {
		// Step 3. Tap Translation tab
		// pageEngine.clickOnElement(MyLocators.TRANSLATION_TAB);
		// Step 4. Tap Translation tab and select a new translation and add it
		pageController.goToTestUrl(Constants.TESTING_URL);
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
	}

	@Ignore
	public void newDefinitionIsAdded() {
		// Step 5. Tap Definition tab and select a new definition and add it
	}

	@Ignore
	public void newImageIsAdded() {// shouldAddImageToWordCard
		// steps to reproduce
		// Step 6. Tap Image tab. Select the image and click on it
	}

	@Ignore
	public void newTagIsAdded() {
		// Step 7. Type a tag in the "tag" field. Example: "fruit"
	}

	@Ignore
	public void isWordaddedToUserDictionaryTest() {

		// Step 8. Click on Add button

		// assertArrayEquals(USER_WORD, actuals);//(expecteds, actuals);
		// fail("Not yet implemented");
	}

}