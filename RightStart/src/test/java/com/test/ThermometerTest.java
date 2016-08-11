package com.test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ThermometerTest {
	WebDriver driver;
	String URL="http://www.rightstart.com/";

	@BeforeClass
	public void setUp(){
		driver=new FirefoxDriver();
		driver.get(URL);
	}

	@Test
	public void clickOnThermometer(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.MICROSECONDS);
		WebElement healthSafety=driver.findElement(By.id("navigation-top-cat-label-336"));
		Actions action = new Actions(driver);
		action.moveToElement(healthSafety).build().perform();
		WebDriverWait wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Healthcare")));
		WebElement healthcare=driver.findElement(By.xpath(".//*[@id='navigation-top-cat-label-1308']/span"));
		action.moveToElement(healthcare).build().perform();
		WebDriverWait waitTime=new WebDriverWait(driver, 20);
		waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Thermometers")));
		driver.findElement(By.linkText("Thermometers")).click();
	}

	@Test(priority=1)
	public void count(){
		List list=driver.findElements(By.xpath("html/body/div[6]/div/div[2]/div[1]/div[3]/div[2]/ul/li"));
		int actual=list.size();
		Assert.assertEquals(actual, 7);
	}

	@Test(priority=2)
	public void firstImagePrice(){
		String price1=driver.findElement(By.xpath(".//*[@id='product_list-price-span-71234']")).getText();
		Assert.assertEquals(price1, "32.95");
	}

	@Test(priority=2)
	public void firstImageRating(){
		String rating=driver.findElement(By.xpath(".//*[@id='product_list-star_rating-span-71234']")).getText();
		Assert.assertEquals(rating, "4.8");
	}

	@Test(priority=2)
	public void outofStockTest(){
		boolean isButtonPresent = false;
		String message=driver.findElement(By.xpath(".//*[@id='product_list-stockout_block-p-76714']/span")).getText();
		String expected="OUT OF STOCK";
		if(message.equals(expected)){
			WebElement button=driver.findElement(By.xpath(".//*[@id='backinstock-button-76714']/div/button"));
			String buttonTitle=button.getAttribute("title");
			String buttonType=button.getAttribute("type");
			if(buttonType.equals("button")){
				isButtonPresent=true;
			}
			Assert.assertEquals(buttonTitle, "Email Me When Back In Stock");
		}
		Assert.assertEquals(message, expected);
		Assert.assertTrue(isButtonPresent);
	}

}
