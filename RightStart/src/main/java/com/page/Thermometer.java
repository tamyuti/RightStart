package com.page;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Thermometer {
	public static void main(String[] args) {
		WebDriver driver=new FirefoxDriver();
		driver.get("http://www.rightstart.com/");
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

}
