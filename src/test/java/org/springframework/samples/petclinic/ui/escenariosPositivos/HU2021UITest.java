
package org.springframework.samples.petclinic.ui.escenariosPositivos;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HU2021UITest {

	@LocalServerPort
	private int				port;

	private String			username;
	private WebDriver		driver;
	private String			baseUrl;
	private boolean			acceptNextAlert		= true;
	private StringBuffer	verificationErrors	= new StringBuffer();


	@BeforeEach
	public void setUp() throws Exception {
		System.setProperty("webdriver.gecko.driver", System.getenv("webdriver.gecko.driver"));
		this.driver = new FirefoxDriver();
		this.baseUrl = "https://www.google.com/";
		this.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testPruebaCasoPositivoOwnerMedicinesFilterList() throws Exception {
		this.as("owner1").whenIamLoggedIntheSystemAsOwner().thenICanListMedicines().thenICanFilterMedicines().thenICanOpenMedicines();
	}

	private HU2021UITest as(final String username) {
		this.username = username;
		this.driver.get("http://localhost:" + this.port + "/");
		this.driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		this.driver.findElement(By.id("username")).click();
		this.driver.findElement(By.id("username")).clear();
		this.driver.findElement(By.id("username")).sendKeys("owner1");
		this.driver.findElement(By.id("password")).click();
		this.driver.findElement(By.id("password")).clear();
		this.driver.findElement(By.id("password")).sendKeys("0wn3r");
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		return this;
	}

	private HU2021UITest whenIamLoggedIntheSystemAsOwner() {
		this.driver.findElement(By.xpath("//a[contains(@href, '/owner/medicines/')]")).click();
		return this;
	}

	private HU2021UITest thenICanListMedicines() {
		Assert.assertEquals("Medicines", this.driver.findElement(By.xpath("//h2")).getText());
		Assert.assertTrue(this.driver.findElement(By.xpath("//table[contains(@id, 'medicinesTable')]")).isDisplayed());
		return this;
	}

	private HU2021UITest thenICanFilterMedicines() {

		 this.driver.findElement(By.id("medicinesform")).click();
		    this.driver.findElement(By.id("petTypeId")).click();
		    this.driver.findElement(By.xpath("//option[@value='2']")).click();
		    this.driver.findElement(By.id("sicknessId")).click();
		    this.driver.findElement(By.xpath("(//option[@value='1'])[2]")).click();
		    this.driver.findElement(By.xpath("//input[@type='submit']")).click();
			Assert.assertEquals("Medicines", this.driver.findElement(By.xpath("//h2")).getText());
			Assert.assertTrue(this.driver.findElement(By.xpath("//table[contains(@id, 'medicinesTable')]")).isDisplayed());
			Assert.assertTrue(driver.findElement(By.cssSelector("td")).getText().matches("Medicina C"));
			return this;
	}
	
	private void thenICanOpenMedicines() {
			this.driver.findElement(By.linkText("Go")).click();
			Assert.assertTrue(driver.findElement(By.cssSelector("th")).getText().matches("Name"));
			Assert.assertTrue(driver.findElement(By.cssSelector("td")).getText().matches("Medicina C"));
	}
	
	
	
	@AfterEach
	public void tearDown() throws Exception {
		this.driver.quit();
		String verificationErrorString = this.verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			Assert.fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(final By by) {
		try {
			this.driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			this.driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = this.driver.switchTo().alert();
			String alertText = alert.getText();
			if (this.acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			this.acceptNextAlert = true;
		}
	}
}
