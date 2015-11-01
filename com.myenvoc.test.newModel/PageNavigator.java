import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageNavigator {
	private static final String TESTING_URL = "http://localhost:8080/myenvoc-webapp";
	private static final int TIME_OUT_IN_SECONDS = 20;
	protected WebDriver webDriver;

	public PageNavigator(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public HomePage goToHomePageAndMaximizeWindow() {
		webDriver.get(TESTING_URL);
		webDriver.manage().window().maximize();
		return new HomePage(this);
	}

	public String getPageUrl() {
		return webDriver.getCurrentUrl();
	}

	public WebElement findElementById(String idLocator) {
		return (new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.id(idLocator)));
	}

	public WebElement findElementByLinkText(String linkText) {
		return (new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.linkText(linkText)));
	}

	public WebElement findElementByXPath(String xpathExpression) {
		WebElement webElement = (new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpathExpression)));
		return webElement;
	}

	public WebElement findElementByClassName(String className) {
		return (new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfElementLocated(By.className(className)));
	}

	public List<WebElement> findElementsByClass(String className) {
		return (new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className(className)));
	}

	public List<WebElement> findElementsByXPath(String xpathExpression) {
		List<WebElement> results = (new WebDriverWait(webDriver, TIME_OUT_IN_SECONDS))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathExpression)));
		return results;
	}

	public void hoverOverElement(String xpathExpression) {
		Actions builder = new Actions(webDriver);
		WebElement element = findElementByXPath(xpathExpression);
		builder.moveToElement(element).perform();

	}

	public Alert switchToAlertWindow() {
		return webDriver.switchTo().alert();
	}

}
