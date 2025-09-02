package runner;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utils.EventHandler;

public class TestRunner {
	public static WebDriver driver;
	static int PAGE_LOAD_TIME = 20;

	@BeforeMethod
	public WebDriver beforeMethod() throws MalformedURLException {
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions);
		driver.get("url");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));
		WebDriverListener listener = new EventHandler();
		driver = new EventFiringDecorator<>(listener).decorate(driver);
		return driver;

	}

	@Test
	public void test_one() throws InterruptedException {
		driver.navigate().refresh();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		try {
			// WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("wiz-iframe-intent")));
			WebElement popup = wait.until(
					ExpectedConditions.visibilityOfElementLocated(By.id("closeButton")));
			popup.click();
			System.out.println("Popup closed ✅");
			driver.switchTo().defaultContent();
		} catch (Exception e) {
			System.out.println("Popup not found ❌ (skipping...)");
			driver.switchTo().defaultContent();
		}
		Thread.sleep(2000);
		WebElement oneway = driver
				.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[1]/div[1]/div/button[2]"));
		oneway.click();
		WebElement fromBox = driver
				.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[1]/div"));
		fromBox.click();
		WebElement fromInput = driver.findElement(
				By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]/div[2]/div/div/div[2]/input"));
		fromInput.sendKeys("IDP-Independence");

		WebElement toBox = driver
				.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[1]/div"));
		toBox.click();
		WebElement toInput = driver.findElement(
				By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[1]/div[2]/div[2]/div/div/div[2]/input"));
		toInput.sendKeys("DEL - New Delhi");

		// WebElement
		// dateBox=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[1]/div/div"));
		// dateBox.click();
		// WebElement
		// toDate=driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[3]/div/div[1]/div[2]/div[1]/div/div/div[2]/button[5]/abbr"));
		// toDate.click();

		WebElement returnS = driver
				.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[2]/div[2]/div/div[1]"));
		returnS.click();
		// Thread.sleep(2000);
		WebElement clickOnClass = driver
				.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div/div"));
		clickOnClass.click();
		Thread.sleep(2000);
		WebElement clickOnBusiness = driver
				.findElement(By.xpath(
						"/html/body/main/div[2]/div[1]/div[3]/div[2]/div[3]/div[2]/div/div[1]/div[5]/div/div[3]/span"));
		clickOnBusiness.click();

		WebElement button = driver.findElement(By.xpath("/html/body/main/div[2]/div[1]/div[3]/div[2]/button"));
		button.click();

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}

}
