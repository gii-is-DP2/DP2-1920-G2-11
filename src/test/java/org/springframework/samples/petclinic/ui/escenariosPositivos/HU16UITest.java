package org.springframework.samples.petclinic.ui.escenariosPositivos;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HU16UITest {
	
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
	public void testPruebaCasoNegativoCreate() throws Exception {
		this.afterILogAsAdmin().thenICantCreateAProduct();
	}
	
  @Test
  public HU16UITest afterILogAsAdmin() throws Exception {
    driver.get("http://localhost:" + this.port + "/");
    this.driver.findElement(By.xpath("//a[contains(@href, '/login')]")).click();
    driver.findElement(By.id("username")).click();
    driver.findElement(By.id("username")).sendKeys("admin1"); 
    driver.findElement(By.xpath("//div")).click();
    driver.findElement(By.id("password")).click();
    driver.findElement(By.id("password")).sendKeys("4dm1n"); 
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    return this;
  }
  @Test
  public HU16UITest thenICantCreateAProduct() throws Exception {
	  this.driver.findElement(By.xpath("//a[contains(@href, '/products')]")).click(); 
	  this.driver.findElement(By.xpath("//a[contains(@href, '/products/new')]")).click();
    driver.findElement(By.id("name")).click();
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("Producto 1");
    driver.findElement(By.id("description")).clear();
    driver.findElement(By.id("description")).sendKeys("Producto nuevo1");
    driver.findElement(By.id("price")).clear();
    driver.findElement(By.id("price")).sendKeys("1.0");
    driver.findElement(By.id("stock")).clear();
    driver.findElement(By.id("stock")).sendKeys("1");
    new Select(driver.findElement(By.id("ProductType"))).selectByVisibleText("1");
    driver.findElement(By.xpath("//option[@value='1']")).click();
    new Select(driver.findElement(By.id("clinic"))).selectByVisibleText("1");
    driver.findElement(By.xpath("(//option[@value='1'])[2]")).click();
    driver.findElement(By.xpath("//button[@type='submit']")).click();
    return this;
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
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