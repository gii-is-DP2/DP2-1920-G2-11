
package org.springframework.samples.petclinic.ui.escenariosNegativos;

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
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext
public class HU3UITest {

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
	public void testPruebaCasoNegativoSicknessShow() throws Exception {
		this.as("vet1").whenIamLoggedIntheSystemAsVet().thenICantShowSickness();
	}

	private HU3UITest as(final String username) {
		this.username = username;
		this.driver.get("http://localhost:" + this.port + "/");
		this.driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		this.driver.findElement(By.id("username")).click();
		this.driver.findElement(By.id("username")).clear();
		this.driver.findElement(By.id("username")).sendKeys("vet1");
		this.driver.findElement(By.id("password")).click();
		this.driver.findElement(By.id("password")).clear();
		this.driver.findElement(By.id("password")).sendKeys("v3t");
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		return this;
	}

	private HU3UITest whenIamLoggedIntheSystemAsVet() {
		this.driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/a/strong")).click();
		Assert.assertEquals("vet1", this.driver.findElement(By.xpath("//div[@id='main-navbar']/ul[2]/li/ul/li/div/div/div[2]/p/strong")).getText());
		return this;
	}

	private void thenICantShowSickness() {
		this.driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[3]/a/span[2]")).click();
		this.driver.findElement(By.linkText("Create a sickness for a pet type")).click();
		this.driver.findElement(By.id("name")).click();
		this.driver.findElement(By.id("name")).clear();
		this.driver.findElement(By.id("name")).sendKeys("Name X");
		this.driver.findElement(By.id("cause")).click();
		this.driver.findElement(By.id("symptom")).click();
		this.driver.findElement(By.id("severity")).click();
		this.driver.findElement(By.id("severity")).clear();
		this.driver.findElement(By.id("severity")).sendKeys("0");
		new Select(this.driver.findElement(By.id("type"))).selectByVisibleText("cat");
		this.driver.findElement(By.xpath("//option[@value='cat']")).click();
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		this.driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[2]/a/span[2]")).click();
		this.driver.findElement(By.xpath("//button[@type='submit']")).click();
		this.driver.findElement(By.linkText("George Franklin")).click();
		Assert.assertEquals("cat", this.driver.findElement(By.xpath("//dd[3]")).getText());
		this.driver.findElement(By.linkText("See Sicknesses")).click();
		this.driver.findElement(By.linkText("Name X")).click();
		Assert.assertTrue(this.driver.findElement(By.xpath("//p[contains(text(), 'La enfermedad aún no ha sido detallada en la web')]")).isDisplayed());
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
