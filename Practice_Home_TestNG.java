package Groww_Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import utils.EventHandler;

public class Test_groww {
	public static WebDriver driver;
	public static int PAGE_LOAD_TIME = 10;

	@BeforeMethod
	public WebDriver beforeMethod() throws MalformedURLException {
		ChromeOptions chromeOptions = new ChromeOptions();
		driver = new RemoteWebDriver(new URL("http://localhost:4444/"), chromeOptions);

		driver.get("https://groww.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(PAGE_LOAD_TIME));
		WebDriverListener listener = new EventHandler();
		driver = new EventFiringDecorator<>(listener).decorate(driver);
		return driver;
	}

	@Test
	public void testOne() throws InterruptedException {
		WebElement element = driver.findElement(
				By.xpath("//*[@id='footer']/div/div[2]/div[3]/div[1]/div/div[6]/span"));

		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Scroll into view but leave some offset (avoid sticky header overlap)
		js.executeScript("arguments[0].scrollIntoView({block: 'center'}); window.scrollBy(0, -200);", element);

		// Optional short wait
		Thread.sleep(500);
		
		// Click with JS (bypasses interception issues)
		js.executeScript("arguments[0].click();", element);
		
		Thread.sleep(3000);
		WebElement homeEMI = driver
		.findElement(By.xpath("//*[@id=\"footer\"]/div/div[2]/div[3]/div[2]/div/div[6]/div[1]/a[5]"));
		// ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", homeEMI);
		homeEMI.click();
		Thread.sleep(1000);
		
		WebElement loanAMount = driver.findElement(By.xpath("//input[@id='LOAN_AMOUNT']"));
		loanAMount.sendKeys("2300000");


		WebElement ROI = driver.findElement(By.xpath("//input[@id=\"RATE_OF_INTEREST\"]"));
		ROI.sendKeys("8");

		
		WebElement tenure = driver.findElement(By.xpath("//input[@id=\"LOAN_TENURE\"]"));
		tenure.sendKeys("20");

	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
