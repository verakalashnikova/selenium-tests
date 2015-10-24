package com.myenvoc.test.model;

import org.openqa.selenium.WebElement;

import com.myenvoc.test.locators.MyLocators;

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

	public boolean hoverNewTranslationandCheckItInTranslationsField() {
		// String selectedNewTranslationValue = pageEngine.;
		pageEngine.howerOverElement(MyLocators.WORD_TRANSLATION_OPTIONS_XPATH);
		WebElement translationInputFueld = pageEngine.findElementByXPath(MyLocators.TRANSLATION_TEXT_AREA_XPATH);
		translationInputFueld.sendKeys("blablabla");
		String newTranslationValue = pageEngine.findElementByXPath(MyLocators.TRANSLATION_TEXT_AREA_XPATH).getText();
		System.out.println("newTranslationValue = " + newTranslationValue);
		return false;
	}

}
