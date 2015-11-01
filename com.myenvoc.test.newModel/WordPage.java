import java.util.List;

import org.openqa.selenium.WebElement;

import com.myenvoc.test.model.ElementNotFoundException;

public class WordPage {
	// constants
	private static final String TAGS = "fruits, snack";
	// locators
	private static final String TRANSLATION_CLICK_TO_ADD_XPATH = "//span[@title='Click to add translation']/a";
	private static final String TRANSLATION_TAB_LINK_TEXT = "Translation";
	private static final String DEFINITION_TAB_LINK_TEXT = "Definition";
	private static final String IMAGES_TAB_LINK_TEXT = "Images";
	private static final String TRANSLATION_TEXT_AREA_XPATH = "//span[contains(., 'Translation')]/../div/textarea";
	private static final String DEFINITION_TEXT_AREA_XPATH = "//span[contains(., 'My Context')]/../div[last()-1]/textarea";
	private static final String TRANSLATION_MY_CONTEXT_AREA_XPATH = "//span[contains(., 'My Context')]/../div/textarea";
	private static final String WORD_TRANSLATION_OPTIONS_ON_TRANSLATION_TAB_XPATH = "//span[@title='Click to add translation']";
	private static final String TRANSLATIONS_ON_USER_WORD_CARD = "//div[@class='gwt-Label']";
	private static final String WORD_DEFINITION_OPTIONS_ON_DEFINITION_TAB_XPATH = "//span[@title='Click to add context']";
	private static final String DEFINITIONS_ON_USER_WORD_CARD = "//span[@class='gwt-InlineLabel']";
	private static final String IMAGE_PLACE_XPATH = "//div[@title='Click to edit']/img";
	private static final String IMAGES_XPATH = "//td/img[@src[contains(., 'http://t')]]";
	private static final String TAGS_INPUT_FIELD_XPATH = "//input[@title='Comma separated list of tags']";
	private static final String ADD_WORD_BUTTON_XPATH = "//button[contains(., 'Add word')]";
	private static final String CANCEL_LINK_TEXT = "Cancel";

	private PageNavigator pageNavigator;

	public WordPage(PageNavigator pageNavigator) {
		this.pageNavigator = pageNavigator;
	}

	public void switchOnTranslationTab() {
		pageNavigator.findElementByLinkText(TRANSLATION_TAB_LINK_TEXT).click();

	}

	public void addOneOfSuggestedTranslations() {
		pageNavigator.findElementByLinkText(TRANSLATION_TAB_LINK_TEXT).click();

	}

	public void switchOnDefinitionTab() {
		pageNavigator.findElementByLinkText(DEFINITION_TAB_LINK_TEXT).click();
	}

	public boolean addOneOfSuggestedDefinitions() {
		int indexOfDefinitionForAdding = 0; // the first one definition from the
		// list
		List<WebElement> suggestesDefinitionsList = pageNavigator
				.findElementsByXPath(WORD_DEFINITION_OPTIONS_ON_DEFINITION_TAB_XPATH);
		if (suggestesDefinitionsList.size() > 0) {
			suggestesDefinitionsList.get(indexOfDefinitionForAdding).click();
		} else {
			throw new ElementNotFoundException(
					"Noone definition was found for this word. I'm not able to add a definition to the word card");
		}
		if (isNewDefinitionAddedToWordCard()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean isNewDefinitionAddedToWordCard() {
		List<WebElement> userDefinitionsList = pageNavigator.findElementsByXPath(DEFINITIONS_ON_USER_WORD_CARD);
		if (userDefinitionsList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void switchOnImageTabByClickOnImagePlace() {
		pageNavigator.findElementByXPath(IMAGE_PLACE_XPATH).click();

	}

	public boolean addImage() {
		int indexOfImage = 1;

		List<WebElement> suggestedImagesList = pageNavigator.findElementsByXPath(IMAGES_XPATH);
		if (suggestedImagesList.size() > 0) {
			suggestedImagesList.get(indexOfImage).click();
			if (isNewImageAddedToTheCard()) {
				return true;
			} else {
				return false;
			}
		} else {
			throw new ElementNotFoundException(
					"Noone image is not displayed. I'm not able to add image to theword card");
		}

	}

	private boolean isNewImageAddedToTheCard() {
		WebElement imagePlace = pageNavigator.findElementByXPath(IMAGE_PLACE_XPATH);
		if (imagePlace.getAttribute("src").contains("http://t")) {
			return true;
		} else {
			return false;
		}
	}

	public void addTag() {
		WebElement tagsField = pageNavigator.findElementByXPath(TAGS_INPUT_FIELD_XPATH);
		tagsField.clear();
		tagsField.sendKeys(TAGS);

	}

	public void clickOnAddWord() {
		pageNavigator.findElementByXPath(ADD_WORD_BUTTON_XPATH).click();

	}

	public boolean hoverTranslationAndCheckItInTranslationsField() {
		pageNavigator.hoverOverElement(WORD_TRANSLATION_OPTIONS_ON_TRANSLATION_TAB_XPATH);
		String newTranslationValue = pageNavigator.findElementByXPath(TRANSLATION_TEXT_AREA_XPATH)
				.getAttribute("value");
		if (newTranslationValue.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hoverDefinitionAndCheckItInDefinitionsField() {
		pageNavigator.hoverOverElement(WORD_DEFINITION_OPTIONS_ON_DEFINITION_TAB_XPATH);
		String newDefinitionValue = pageNavigator.findElementByXPath(DEFINITION_TEXT_AREA_XPATH).getAttribute("value");
		if (newDefinitionValue.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public void cancelAddingWord() {
		pageNavigator.findElementByLinkText(CANCEL_LINK_TEXT).click();

	}

}
