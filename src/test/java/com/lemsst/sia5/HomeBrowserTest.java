package com.lemsst.sia5;

import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class HomeBrowserTest {

  @LocalServerPort
  private int port;
  private static WebDriver browser;  
  
  @BeforeClass
  public static void setup() {
    browser = new HtmlUnitDriver();
    
    browser.manage().timeouts()
          .implicitlyWait(10, TimeUnit.SECONDS);
  }
  
  @AfterClass
  public static void teardown() {
    browser.quit();
  }
  
  @Test
  public void testHomePage() {
    String homePage = "http://localhost:" + port;
    browser.get(homePage);
    
    String titleText = browser.getTitle();
    Assert.assertEquals("Taco Home Page", titleText);
    
    String h1Text = browser.findElement(By.tagName("h1")).getText();
    Assert.assertEquals("Welcome to the home page!", h1Text);
    
    WebElement designTacoBtn = browser.findElement(By.id("designTacoBtn"));
    
    Assert.assertTrue(designTacoBtn.isDisplayed());
  }
  
  
}