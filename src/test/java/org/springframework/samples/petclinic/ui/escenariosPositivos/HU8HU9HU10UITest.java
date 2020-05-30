package org.springframework.samples.petclinic.ui.escenariosPositivos;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HU8HU9HU10UITest {

	@LocalServerPort
	private int port;
	private String username;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {

		System.setProperty("webdriver.gecko.driver", System.getenv("webdriver.gecko.driver"));
		driver = new FirefoxDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testShowClinicListProductsClinicAndShowProduct() throws Exception {
		this.as("owner1").whenIamLoggedIntheSystemAsOwner().thenICanShowClinic().thenICanListProductsClinic()
				.thenICanShowAProduct();
		Assert.assertEquals("OWNER1", this.driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a/strong")).getText());

	}

	private HU8HU9HU10UITest thenICanShowClinic() {
		
		driver.findElement(By.xpath("//a[contains(@href, '/clinics/1')]")).click();
		Assert.assertEquals("Winston Pet Cares", this.driver.findElement(By.xpath("//b")).getText());
		return this;
	}

	private HU8HU9HU10UITest thenICanListProductsClinic() {
		driver.findElement(By.xpath("//a[contains(@href, '/clinics')]")).click();
		Assert.assertEquals("Winston Pet Cares", this.driver.findElement(By.xpath("//a[contains(text(),'Winston Pet Cares')]")).getText());
		driver.findElement(By.xpath("//a[contains(@href, '/clinics/5/products')]")).click();
		return this;
	}

	private HU8HU9HU10UITest thenICanShowAProduct() {
		Assert.assertEquals("champu hidratante", this.driver.findElement(By.xpath("//a[contains(text(),'champu hidratante')]")).getText());
		driver.findElement(By.xpath("//a[contains(@href, '/products/6')]")).click();
		return this;
	}

	private HU8HU9HU10UITest whenIamLoggedIntheSystemAsOwner() {
		driver.findElement(By.xpath("//a[contains(@href, '/clinics')]")).click();
		return this;
	}

	private HU8HU9HU10UITest as(final String username) {
		this.username = username;
		driver.get("http://localhost:" + this.port + "/");
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("0wn3r");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("owner1");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		return this;
	}

	@AfterEach
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

	private boolean isElementPresent(final By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
