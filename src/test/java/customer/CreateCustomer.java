package customer;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import constant.Constant;
import constant.RunFirst;

public class CreateCustomer extends RunFirst {

	public void waitForTitle(WebDriver wr, String title) {
		WebDriverWait wait = new WebDriverWait(wr, 30000);
		wait.until(ExpectedConditions.titleContains(title));
	}

//	generate email
	protected String getSaltString() {
		String SALTCHARS = "asdfghjklzxcvbnmqwertyui1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 10) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@Test
	public void TC001_Login() throws InterruptedException {
		System.out.println("hello");
		webDriver.get(Constant.LOGIN_URL);
		System.out.println("yes");
		Thread.sleep(3000);
		webDriver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr310403");
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("emymYqa");
		Thread.sleep(1000);
		webDriver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	}

	@Test
	public void TC002_CreateNewCustomerSuccessfully() throws InterruptedException {
		System.out.println("hello");
//		New Customer
		webDriver.get(Constant.NEW_CUSTOMER_URL);
//		waitForTitle(webDriver, "Guru99 Bank New Customer Entry Page");
		System.out.println("Open Add Customer Page sucessfully");
		webDriver.findElement(By.xpath("//input[@name='name']")).sendKeys("Huyen Nguyen");
		webDriver.findElement(By.xpath("//input[@id='dob']")).sendKeys("02/02/1990");
		webDriver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("Test Address");
		webDriver.findElement(By.xpath("//input[@name='city']")).sendKeys("Ho Chi Minh");
		webDriver.findElement(By.xpath("//input[@name='state']")).sendKeys("Ho Chi Minh");
		webDriver.findElement(By.xpath("//input[@name='pinno']")).sendKeys("123456");
		webDriver.findElement(By.xpath("//input[@name='telephoneno']")).sendKeys("1234567890");
		webDriver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(getSaltString() + "@gmail.com");
		webDriver.findElement(By.xpath("//input[@name='password']")).sendKeys("123456");
		webDriver.findElement(By.xpath("//input[@name='sub']")).click();
		Thread.sleep(2000);
//		New Account based on Customer ID created
		String customerID = webDriver.findElement(By.xpath("//table[@id='customer']//tbody//tr[4]//td[2]")).getText();
		webDriver.get(Constant.NEW_ACCOUNT_URL);
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerID);
		System.out.println("Okay");
		webDriver.findElement(By.xpath("//input[@name='inideposit']")).sendKeys("123456");
		System.out.println("Okay");
		webDriver.findElement(By.xpath("//input[@name='button2']")).click();
		Thread.sleep(2000);
//		Deposit
		String accountID = webDriver.findElement(By.xpath("//table[@id='account']//tbody//tr[4]//td[2]")).getText();
		webDriver.get(Constant.DEPOSIT_URL);
		Thread.sleep(2000);
		webDriver.findElement(By.xpath("//input[@name='accountno']")).sendKeys(accountID);
		webDriver.findElement(By.xpath("//input[@name='ammount']")).sendKeys("123456");
		webDriver.findElement(By.xpath("//input[@name='desc']")).sendKeys("Test");
		webDriver.findElement(By.xpath("//input[@name='AccSubmit']")).click();
	}
}
