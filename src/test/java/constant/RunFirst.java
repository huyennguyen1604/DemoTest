package constant;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class RunFirst {
	public static WebDriver webDriver;
	public static InternetExplorerDriver driverIE;
	public static WebDriverWait wait;
	public static WebElement element;

	@BeforeTest
	@Parameters({ "browser" })
	public void setupBrowser(String browser) {
		System.out.println("Are you testing on " + browser);

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\WORK\\Selenium Project\\chromedriver.exe");
			System.out.println("chrome");
			webDriver = new ChromeDriver();
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		}
	}

	@AfterTest
	public void closeBrowser() throws InterruptedException {
		Thread.sleep(2000);
		webDriver.close();
		webDriver.quit();
	}
}
