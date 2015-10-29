package com.myenvoc.test.model;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageController {
	private WebDriver webDriver;

	public PageController(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public void goToTestUrl() {
		webDriver.get(Constants.TESTING_URL);
		webDriver.manage().window().maximize();
	}

	public String getPageUrl() {

		return webDriver.getCurrentUrl();
	}

	public WebElement findElementByXPath(String xpathExpression) {
		WebElement webElement = (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
		return webElement;
	}

	public void hoverOverElement(String xpathExpression) {
		Actions builder = new Actions(webDriver);
		WebElement element = findElementByXPath(xpathExpression);
		builder.moveToElement(element).perform();
	}

	public List<WebElement> findElementsByXPath(String xpathExpression) {
		List<WebElement> results = (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathExpression)));
		// if (results == null) {
		// throw new ElementNotFoundException(String.format("Can't find element
		// by id '%s'", suggestedWordsPopUp));
		// }
		return results;
	}

	public WebElement findElementById(String idLocator) {
		return (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.id(idLocator)));
	}

	public WebElement findElementByLinkText(String linkText) {
		return (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));

	}

	public WebElement findElementByClassName(String className) {
		return (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));

	}

	public List<WebElement> findElementsByClass(String className) {
		return (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
	}

	public List<WebElement> findElementsByTagName(String tagName) {
		return (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS)
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.tagName(tagName))));

	}

	public WebElement findElementByTagName(String tagName) {
		return (new WebDriverWait(webDriver, Constants.TIME_OUT_IN_SECONDS)
				.until(ExpectedConditions.presenceOfElementLocated(By.tagName(tagName))));

	}

	public void driverWait(int seconds) throws InterruptedException {
		webDriver.wait(seconds);
	}

	class MyEx extends Exception {
	} // checked

	class MyEx2 extends RuntimeException {
	} // uncecked

	void f() throws MyEx {

		g();

	}

	private void g() throws MyEx {
		// throw new MyEx();
	}

}
