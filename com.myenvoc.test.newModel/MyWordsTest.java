import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

@RunWith(Parameterized.class)
public class MyWordsTest {

	private static final String TESTING_URL = "http://localhost:8080/myenvoc-webapp";

	private WebDriver webDriver;
	private PageNavigator pageNavigator;
	private HomePage homePage;
	private LibraryPage libraryPage;
	private MyWordsPage myWordsPage;
	private WordPage wordPage;

	private String browser;

	@Parameters(name = "browser")
	public static Collection<Object[]> browsers() {
		List<Object[]> params = new ArrayList<>();
		params.add(new String[] { "firefox" });
		params.add(new String[] { "chrome" });

		return params;
	}

	public MyWordsTest(String browser) {
		this.browser = browser;
	}

	@Before
	public void setUp() throws Exception {
		if (browser.equalsIgnoreCase("firefox")) {
			generateFirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			generateChromeDriver();
		} else

		{
			throw new IllegalArgumentException("The Browser Type is Undefined");
		}

		pageNavigator = new PageNavigator(webDriver);
		homePage = pageNavigator.goToHomePageAndMaximizeWindow();

	}

	public void generateChromeDriver() throws MalformedURLException {
		System.out.println(" Executing on CHROME");
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setBrowserName("chrome");
		String Node = "http://192.168.1.7:5557/wd/hub";
		webDriver = new RemoteWebDriver(new URL(Node), cap);
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	public void generateFirefoxDriver() throws MalformedURLException {
		System.out.println(" Executing on FireFox");
		String Node = "http://192.168.1.7:5555/wd/hub";
		DesiredCapabilities cap = DesiredCapabilities.firefox();
		cap.setBrowserName("firefox");

		webDriver = new RemoteWebDriver(new URL(Node), cap);
		// Puts an Implicit wait, Will wait for 10 seconds before throwing
		// exception
		webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@After
	public void tearDown() throws Exception {
		webDriver.close();
	}

	// @Test
	public void newHoveredTranslationIsDisplayedInWordTranslationSection() {
		// Interface language should be English or we would not be
		// able to check anything
		homePage.changeInterfaceLanguage();
		libraryPage = homePage.loginUser();
		myWordsPage = libraryPage.goToMyWordsPage();
		myWordsPage.typeWordForTranslation();
		wordPage = myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		assertEquals(wordPage.hoverTranslationAndCheckItInTranslationsField(), true);

	}

	// @Test
	public void newHoveredDefinitionIsDisplayedInWordDefinitionSection() {
		// Interface language should be English or we would not be
		// able to check anything
		homePage.changeInterfaceLanguage();
		libraryPage = homePage.loginUser();
		myWordsPage = libraryPage.goToMyWordsPage();
		myWordsPage.typeWordForTranslation();
		wordPage = myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		wordPage.addOneOfSuggestedTranslations();
		wordPage.switchOnDefinitionTab();
		assertEquals(wordPage.hoverDefinitionAndCheckItInDefinitionsField(), true);
	}

	// @Test
	public void testNewWordCanBeCreated() {
		// Interface language should be English or we would not be
		// able to check anything
		homePage.changeInterfaceLanguage();
		libraryPage = homePage.loginUser();
		myWordsPage = libraryPage.goToMyWordsPage();
		myWordsPage.typeWordForTranslation();
		wordPage = myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		wordPage.addOneOfSuggestedTranslations();
		wordPage.switchOnDefinitionTab();
		wordPage.addOneOfSuggestedDefinitions();
		wordPage.switchOnImageTabByClickOnImagePlace();
		wordPage.addImage();
		wordPage.addTag();
		wordPage.clickOnAddWord();
	}

	@Test
	public void testNewWordShouldNotBeCreated() {
		// Interface language should be English or we would not be
		// able to check anything
		homePage.changeInterfaceLanguage();
		// libraryPage = homePage.loginUser();
		libraryPage = homePage.userRegistration();
		myWordsPage = libraryPage.goToMyWordsPage();
		myWordsPage.ifWordAlreadyIsCreatedDeleteIt();
		myWordsPage.typeWordForTranslation();
		wordPage = myWordsPage.selectUserWordFromSuggestedDialogOrTapTranslateButton();
		wordPage.switchOnTranslationTab();
		wordPage.addOneOfSuggestedTranslations();
		wordPage.switchOnDefinitionTab();
		wordPage.addOneOfSuggestedDefinitions();
		wordPage.switchOnImageTabByClickOnImagePlace();
		wordPage.addImage();
		wordPage.addTag();
		wordPage.cancelAddingWord();
	}

}
