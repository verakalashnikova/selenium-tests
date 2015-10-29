package com.myenvoc.test.model;

import java.util.List;

import org.openqa.selenium.WebElement;

public class WordPage {

	private PageController pageEngine;

	public WordPage(PageController pageEngine) {
		this.pageEngine = pageEngine;
	}

	public boolean amInWordPage() {
		String currentPageURL = pageEngine.getPageUrl();
		System.out.println("currentPageURL = " + currentPageURL);
		if (currentPageURL.contains(Constants.WORD_PAGE_PART_URL_FOR_VALIDATION)) {
			return true;
		} else {
			return false;
		}
	}

	public void switchOnTranslationTab() {
		pageEngine.findElementByLinkText(MyLocators.TRANSLATION_TAB_LINK_TEXT).click();
	}

	public void switchOnDefinitionTab() {
		pageEngine.findElementByLinkText(MyLocators.DEFINITION_TAB_LINK_TEXT).click();
	}

	public boolean hoverTranslationAndCheckItInTranslationsField() {
		pageEngine.hoverOverElement(MyLocators.WORD_TRANSLATION_OPTIONS_ON_TRANSLATION_TAB_XPATH);
		String newTranslationValue = pageEngine.findElementByXPath(MyLocators.TRANSLATION_TEXT_AREA_XPATH)
				.getAttribute("value");
		System.out.println("newTranslationValue = " + newTranslationValue);
		if (newTranslationValue.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean addOneOfSuggestedTranslations() {
		int indexOfTranslationForAdding = 1;
		List<WebElement> suggestedTranslationsList = pageEngine
				.findElementsByXPath(MyLocators.TRANSLATION_CLICK_TO_ADD_XPATH);
		if (suggestedTranslationsList.size() > 1) {
			suggestedTranslationsList.get(indexOfTranslationForAdding).click();
		} else {
			throw new ElementNotFoundException(
					"Only one translation was found for this word. I'm not able to add another translation to the word card");
		}
		if (isNewTranslationAddedToWordCard()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isNewTranslationAddedToWordCard() {
		List<WebElement> userTranslationsList = pageEngine
				.findElementsByXPath(MyLocators.TRANSLATIONS_ON_USER_WORD_CARD);
		if (userTranslationsList.size() > 1) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addOneOfSuggestedDefinitions() {
		int indexOfDefinitionForAdding = 0; // the first one definition from the
											// list
		List<WebElement> suggestesDefinitionsList = pageEngine
				.findElementsByXPath(MyLocators.WORD_DEFINITION_OPTIONS_ON_DEFINITION_TAB_XPATH);
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
		List<WebElement> userDefinitionsList = pageEngine.findElementsByXPath(MyLocators.DEFINITIONS_ON_USER_WORD_CARD);
		System.out.println("size=" + userDefinitionsList.size());
		if (userDefinitionsList.size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean addImage() {
		int indexOfImage = 1;

		List<WebElement> suggestedImagesList = pageEngine.findElementsByXPath(MyLocators.IMAGES_XPATH);
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
		WebElement imagePlace = pageEngine.findElementByXPath(MyLocators.IMAGE_PLACE_XPATH);
		if (imagePlace.getAttribute("src").contains("http://t")) {
			return true;
		} else {
			return false;
		}
	}

	public void switchOnImageTabByClickOnImagePlace() {
		pageEngine.findElementByXPath(MyLocators.IMAGE_PLACE_XPATH).click();
	}

	public void switchOnImageTab() {
		pageEngine.findElementByLinkText(MyLocators.IMAGES_TAB_LINK_TEXT).click();

	}

	public void addTag() {
		WebElement tagsField = pageEngine.findElementByXPath(MyLocators.TAGS_INPUT_FIELD_XPATH);
		tagsField.clear();
		tagsField.sendKeys(Constants.TAGS);

	}

	public void addWord() {
		pageEngine.findElementByXPath(MyLocators.ADD_WORD_BUTTON_XPATH).click();
	}

	public void cancelAddingWord() {
		pageEngine.findElementByLinkText(MyLocators.CANCEL_LINK_TEXT).click();
	}

	public boolean hoverDefinitionAndCheckItInDefinitionsField() {
		pageEngine.hoverOverElement(MyLocators.WORD_DEFINITION_OPTIONS_ON_DEFINITION_TAB_XPATH);
		String newDefinitionValue = pageEngine.findElementByXPath(MyLocators.DEFINITION_TEXT_AREA_XPATH)
				.getAttribute("value");
		System.out.println("newDefinitionValue = " + newDefinitionValue);
		if (newDefinitionValue.isEmpty()) {
			return false;
		} else {
			return true;
		}
	}

}
