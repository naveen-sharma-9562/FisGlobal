package org.fis.FisSample;

import org.openqa.selenium.By;

import java.util.ArrayList;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;

public class ebayTest {

	public static void main(String[] args) {
      
        System.setProperty("webdriver.edge.driver", "E:\\edgedriver_win64\\msedgedriver.exe");
        
       
        WebDriver driver = new EdgeDriver();   
               
        try {
            // Step 3: Navigate to eBay
            driver.get("https://www.ebay.com");
            
            driver.manage().window().maximize();

            // Step 4: Search for 'book'
            WebElement searchBox = driver.findElement(By.id("gh-ac"));  // Locate search bar
            searchBox.sendKeys("book");  // Enter 'book'
         
            Thread.sleep(300);
            
            driver.findElement(By.xpath("//*[@type='submit']")).click();
            
            Thread.sleep(300);
            
            WebElement firstItem = driver.findElement(By.xpath("//*[@class='srp-river-results clearfix']/ul/li/div[1]/div[1]"));  // First item link
            firstItem.click();
            
            Thread.sleep(300);
            
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(tabs.get(1));

            WebElement addToCartButton = driver.findElement(By.id("atcBtn_btn_1"));  // 'Add to Cart' button
            
            addToCartButton.click();
            
            Thread.sleep(300);

            WebElement cartIcon = driver.findElement(By.id("gh-cart-n"));  // Cart icon
            
            String cartCount = cartIcon.getText();  // Get the text (number of items)

            if (cartCount.matches("\\d+") && Integer.parseInt(cartCount) > 0) {
                System.out.println("Test Passed: Cart has " + cartCount + " item(s).");
            } else {
                System.out.println("Test Failed: Cart is empty.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 11: Close the browser
            driver.quit();
        }
    }
        
    }

