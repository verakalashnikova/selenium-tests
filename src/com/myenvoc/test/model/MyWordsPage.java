package com.myenvoc.test.model;

import java.util.List;

import org.openqa.selenium.WebElement;

public class MyWordsPage {

	private PageController pageController;

	public MyWordsPage(PageController pageController) {
		this.pageController = pageController;
	}

	public void selectUserWordFromSuggestedDialogOrTapTranslateButton() {
		int indexOfSuggestedWordInList = -1;
		List<WebElement> suggestedWordsList = pageController
				.findElementsByClass(MyLocators.SUGGESTED_WORDS_POP_UP_CLASS_NAME);
		for (int i = 0; i < suggestedWordsList.size(); i++) {
			if ((suggestedWordsList.get(i).getText()).equals(Constants.USER_WORD)) {
				indexOfSuggestedWordInList = i;
				break;
			}
		}
		if (indexOfSuggestedWordInList != -1) {
			suggestedWordsList.get(indexOfSuggestedWordInList).click();
		} else {
			// throw new ElementNotFoundException(
			// "SUGGESTED_WORDS_POP_UP_CLASS_NAME doesn't contain " +
			// Constants.USER_WORD + "word");
			pageController.findElementByClassName(MyLocators.TRANSLATE_BUTTON_PART_CLASS_NAME).click();// ????
		}
	}

	public void typeWordForTranslation() {
		WebElement wordForTranslation = pageController
				.findElementByClassName(MyLocators.WORD_TYPING_FIELD_TO_TRANSLATE_PART_CLASS_NAME);
				// .findElementByXPath(MyLocators.WORD_FIELD_FOR_TRANSLATION_PART_CLASS_NAME);

		// wordForTranslation.clear();

		String partOfTheWord = Constants.USER_WORD;
		wordForTranslation.sendKeys(partOfTheWord);
	}

}
