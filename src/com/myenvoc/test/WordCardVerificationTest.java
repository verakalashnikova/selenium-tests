package com.myenvoc.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.myenvoc.test.model.AuthenticationRequiredException;
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
		webDriver.close();
	}

	// @Test
	public void shouldNotGoToMywordsPageIfNotLoggedIn() throws NoSuchElementException, InterruptedException {
		try {
			pageController.goToTestUrl();
			homePage.goToMyWordsPage(user);
			fail("Should not go here, as the user wasn't logged in");
		} catch (AuthenticationRequiredException e) {
			// Expected, as the use was not logged in
		}
	}

	// @Test
	public void shouldGoToMywordsPageIfLoggedIn() throws InterruptedException {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
	}

	// @Test
	public void goToWordPage() throws Exception {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		assertEquals(wordPage.amInWordPage(), true);
	}

	// @Test
	public void newHoveredTranslationIsDisplayedInWordTranslationSection() {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		assertEquals(wordPage.hoverTranslationAndCheckItInTranslationsField(), true);

	}

	// @Test
	public void newHoveredDefinitionIsDisplayedInWordDefinitionSection() {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnDefinitionTab();
		assertEquals(wordPage.hoverDefinitionAndCheckItInDefinitionsField(), true);
	}

	// @Test
	public void newTranslationFromSuggestedTranslationsIsAdded() {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		assertEquals(wordPage.addOneOfSuggestedTranslations(), true);
	}

	// @Test
	public void newDefinitionFromSuggestedDefinitionsIsAdded() {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnDefinitionTab();
		assertEquals(wordPage.addOneOfSuggestedDefinitions(), true);
	}

	// @Test
	public void newImageIsAddedByClickOnImagePlace() {// shouldAddImageToWordCard
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnImageTabByClickOnImagePlace();
		assertEquals(wordPage.addImage(), true);
	}

	// @Test
	public void newImageIsAddedFromImageTab() {// shouldAddImageToWordCard
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnImageTab();
		assertEquals(wordPage.addImage(), true);
	}

	// @Test
	public void shouldWordBeAddedToUserDictionaryTest() {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		wordPage.addOneOfSuggestedTranslations();
		wordPage.switchOnDefinitionTab();
		wordPage.addOneOfSuggestedDefinitions();
		wordPage.switchOnImageTabByClickOnImagePlace();
		wordPage.addImage();
		wordPage.addTag();
		wordPage.addWord();
		homePage.isWordAdded();
	}

	// @Test
	public void shouldNotWordBeAddedToUserDictionaryTest() {
		// TODO: check that the word is not added
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		wordPage.addOneOfSuggestedTranslations();
		wordPage.switchOnDefinitionTab();
		wordPage.addOneOfSuggestedDefinitions();
		wordPage.switchOnImageTabByClickOnImagePlace();
		wordPage.addImage();
		wordPage.addTag();
		wordPage.cancelAddingWord();
		homePage.isWordAdded(); // test should not pass because the word should
								// not be added
	}

	@Test
	public void shouldBeWordDeletedFromMyWordsTableTest() {
		pageController.goToTestUrl();
		user.loginUser();
		homePage.goToMyWordsPage(user);
		myWordsPage.typeWordForTranslation();
		myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		wordPage.addOneOfSuggestedTranslations();
		wordPage.switchOnDefinitionTab();
		wordPage.addOneOfSuggestedDefinitions();
		wordPage.switchOnImageTabByClickOnImagePlace();
		wordPage.addImage();
		wordPage.addTag();
		wordPage.addWord();
		homePage.isWordAdded();
		homePage.deleteWord();
	}

}