package org.springframework.samples.petclinic.ui.escenariosPositivos;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class HUVaccines {

	@LocalServerPort
	private int port;
	private String username;
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		String pathToGeckoDriver="C:\\Users\\jaime\\Downloads";
		System.setProperty("webdriver.gecko.driver", pathToGeckoDriver+ "\\geckodriver.exe");
		//System.setProperty("webdriver.gecko.driver", System.getenv("webdriver.gecko.driver"));
		driver = new FirefoxDriver();
		baseUrl = "https://www.google.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCreateShowAndListAsVaccine() throws Exception {
		this.as("admin1").whenIamLoggedIntheSystemAsAdmin().thenICanCreateAVaccine().thenICanListAVaccine();
	}
	
	
	private HUVaccines thenICanListAVaccine() {
		driver.findElement(By.xpath("//a[contains(@href, '/vets/edit/24')]")).click();
		driver.findElement(By.id("components")).click();
		driver.findElement(By.id("components")).clear();
		driver.findElement(By.id("components")).sendKeys("oiddc8ll");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		driver.findElement(By.xpath("//a[contains(@href, '/vets/delete/24')]")).click();
		return this;
	}

	private HUVaccines thenICanCreateAVaccine() {
		driver.findElement(By.linkText("Add Vaccine")).click();
		driver.findElement(By.id("name")).click();
		driver.findElement(By.id("name")).clear();
		driver.findElement(By.id("name")).sendKeys("1223kds");
		driver.findElement(By.id("components")).clear();
		driver.findElement(By.id("components")).sendKeys("oiddc8");
		driver.findElement(By.id("months")).clear();
		driver.findElement(By.id("months")).sendKeys("67");
		driver.findElement(By.id("sickness")).click();
		new Select(driver.findElement(By.id("sickness"))).selectByVisibleText("Leucemia felina");
		driver.findElement(By.xpath("//option[@value='4']")).click();
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		return this;
	}

	private HUVaccines whenIamLoggedIntheSystemAsAdmin() {
		driver.findElement(By.xpath("//li[5]/a/span[2]")).click();
		return this;
	}

	private HUVaccines as(final String username) {
		this.username= username; 
		driver.get("http://localhost:" + this.port + "/");
		driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("4dm1n");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("admin1");
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
