package com.myenvoc.test.model;
import org.openqa.selenium.WebDriver;

public class MyWebDriver {
	public WebDriver webDriver;
	
	private MyWebDriver(){}
	
	public MyWebDriver(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	public WebDriver getWebdriver() {
		return webDriver;
	}


}
