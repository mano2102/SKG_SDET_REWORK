package runner;

import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.testng.annotations.Test;

import utils.EventHandler;

public class TestCheck {

    static WebDriver driver;

    @Test
    void caseone() {
        try {

            driver = new RemoteWebDriver(new URL("http://localhost:4444"), new ChromeOptions());

            // Maximize the browser window
            driver.manage().window().maximize();

            // Add the implicit wait
            driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));

            // Add the page load timeout
            driver.manage().timeouts().pageLoadTimeout(java.time.Duration.ofSeconds(10));

            // Initialize the event listener
            WebDriverListener listener = new EventHandler();
            driver = new EventFiringDecorator<>(listener).decorate(driver);

            // Start your script from here
            driver.get("https://groww.in/");

            WebElement login = driver.findElement(By.xpath("//*[@id=\"root\"]/div[1]/div[1]/div[2]/div[2]/button/span"));

                    login.click();

            WebElement welcome =driver.findElement(By.xpath("//*[@id=\"lils382InitialLoginScreen\"]//h1"));


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }

}
