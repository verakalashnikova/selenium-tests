import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;

import com.myenvoc.test.model.ElementNotFoundException;

public class MyWordsPage {
	// constants
	private static final String USER_WORD = "apple";
	// locators
	private static final String WORD_TYPING_FIELD_TO_TRANSLATE_PART_CLASS_NAME = "gwt-SuggestBox";
	private static final String SUGGESTED_WORDS_POP_UP_CLASS_NAME = "item";
	private static final String TRANSLATE_BUTTON_PART_CLASS_NAME = "gwt-Button";
	private static final String USER_WORD_IN_MY_WORDS_XPATH = "//a[contains(@title, 'Click to edit')]";
	private static final String CHECK_BOXES = "//input[@type='checkbox']";
	private static final String DELETE_BUTTON_XPATH = "//button[contains(., 'Delete')]";

	private PageNavigator pageNavigator;

	public MyWordsPage(PageNavigator pageNavigator) {
		this.pageNavigator = pageNavigator;
	}

	public void typeWordForTranslation() {
		typeWord();
	}

	private void typeWord() {
		WebElement wordForTranslation = pageNavigator
				.findElementByClassName(WORD_TYPING_FIELD_TO_TRANSLATE_PART_CLASS_NAME);
		if (!wordForTranslation.getAttribute("value").isEmpty()) {
			wordForTranslation.clear();
		}
		wordForTranslation.sendKeys(USER_WORD);

	}

	public WordPage selectUserWordFromSuggestedDialogOrTapTranslateButton() {
		int userWordPositionInSuggestedDialog = findUserWordInSuggestedDialog();
		if (userWordPositionInSuggestedDialog == -1) {
			clickOnTranslateButton();
		} else {
			clickOnUserWord(userWordPositionInSuggestedDialog);
		}

		return new WordPage(pageNavigator);
	}

	private void clickOnUserWord(int userWordPositionInSuggestedDialog) {
		List<WebElement> suggestedWordsList = pageNavigator.findElementsByClass(SUGGESTED_WORDS_POP_UP_CLASS_NAME);
		suggestedWordsList.get(userWordPositionInSuggestedDialog).click();
	}

	private int findUserWordInSuggestedDialog() {
		// return the word position in the suggested list is word is displayed
		// or -1 in other case
		List<WebElement> suggestedWordsList = pageNavigator.findElementsByClass(SUGGESTED_WORDS_POP_UP_CLASS_NAME);
		for (int i = 0; i < suggestedWordsList.size(); i++) {
			if ((suggestedWordsList.get(i).getText()).equals(USER_WORD)) {
				return i;
			}
		}
		return -1;

	}

	private void clickOnTranslateButton() {
		pageNavigator.findElementByClassName(TRANSLATE_BUTTON_PART_CLASS_NAME).click();

	}

	public void deleteWord() {
		checkThatWordIsLocatedOnPage();
		checkWordForDelete();
		pressDeleteButton();
		confirmDeletionInPopUpDialog();
	}

	private void confirmDeletionInPopUpDialog() {
		Alert alert = pageNavigator.switchToAlertWindow();
		alert.accept();
		// alert.dismiss(); //click on Cancel button

	}

	private void checkWordForDelete() {
		List<WebElement> listOfCheckboxes = pageNavigator.findElementsByXPath(CHECK_BOXES);
		// take the second element from the list. This is the checkbox for User
		// Word
		listOfCheckboxes.get(1).click();

	}

	private void pressDeleteButton() {
		pageNavigator.findElementByXPath(DELETE_BUTTON_XPATH).click();

	}

	private void checkThatWordIsLocatedOnPage() {
		if (!wordIsCreated()) {
			throw new ElementNotFoundException("User word is not found in My Words table");
		}

	}

	public void ifWordAlreadyIsCreatedDeleteIt() {
		if (wordIsCreated()) {
			deleteWord();
		}

	}

	private boolean wordIsCreated() {
		List<WebElement> checkboxes = pageNavigator.findElementsByXPath(CHECK_BOXES);
		// if noone word is added only one checkbox will be found
		if (checkboxes.size() > 1) {
			// At least one word presents in the page. We need check that it is
			// the right word
			WebElement word = pageNavigator.findElementByXPath(USER_WORD_IN_MY_WORDS_XPATH);
			if (word.getText().equals(USER_WORD)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
