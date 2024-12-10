package demo.wrappers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.time.Duration;

public class Wrappers {
    /*
     * Write your selenium wrappers here
     */

    
     ChromeDriver driver;
     WebDriverWait wait;

     public Wrappers(ChromeDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
     } 


     public void NavigateToUrl(String url){
        try{
            if(!(driver.getCurrentUrl().contains("flipkart"))){
                driver.get(url);
                wait.until(ExpectedConditions.urlContains("flipkart"));
                Thread.sleep(2000);
            }

        }catch(Exception e){
            System.out.println(e);
        }
        
        

     }

     public boolean searchAndEnter(String productToSearch){
        try{
            WebElement searchBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='Pke_EE' and contains(@title,'Search for Products')]")));
            Thread.sleep(2000);
            searchBar.click();
            searchBar.clear();
            Thread.sleep(2000);
            searchBar.sendKeys(productToSearch);

            WebElement searchIcon = driver.findElement(By.xpath("//button[contains(@title,'Search for Products')]"));
            searchIcon.click();
            Thread.sleep(5000);
            return true;

        }catch(Exception e){
            return false;
        }
        
     }

     public void setPopularity(){
        try{
            WebElement popularity = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'sHCOk')]/div[text()='Popularity']")));
            Thread.sleep(1000);
            popularity.click();
            Thread.sleep(2000);
        }catch(Exception e){
            System.out.println(e);
        } 

     }

     public int countItemsLessThan(int ratings){
        try{

            List<WebElement> Items = driver.findElements(By.xpath("//span[contains(@id,'productRating')]/div[number(text())<= " + ratings + "]"));
            Thread.sleep(2000);
            int ItemsCount = Items.size();
            System.out.println("Items count with rating Lesser than or equal to:   "+ItemsCount);
            return ItemsCount; 

        }catch(Exception e){
            System.out.println(e);
            return 0;
        }
     }

     public void getTitlesAndDiscounts(int discount) {
        try {
    
            String productXPath = "//div[@class='UkUFwK']/span[contains(text(), '% off') and number(substring-before(text(), '%')) > "+discount+"]/ancestor::div[@class='yKfJKb row']";
    
            // Locate all matching products
            List<WebElement> products = driver.findElements(By.xpath(productXPath));
            System.out.println("number of iPhones with discount > 17% :- "+products.size());
    
            JavascriptExecutor js = (JavascriptExecutor) driver;

            for (WebElement product : products) {
                // Extract the title
                WebElement titleElement = product.findElement(By.xpath(".//div[@class='KzDlHZ']"));
                js.executeScript("arguments[0].scrollIntoView(true);", titleElement);
                String title = titleElement.getText();
    
                // Extract the discount percentage
                WebElement discountElement = product.findElement(By.xpath(".//div[@class='UkUFwK']/span[contains(text(), '% off')]"));
                js.executeScript("arguments[0].scrollIntoView(true);", discountElement);
                String discountText = discountElement.getText();
                
                System.out.println("Product Title: " + title);
                System.out.println("Discount: " + discountText);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }

    public void setCustomerRatings(int rating){

        try{
            
            String str = String.valueOf(rating);
            WebElement custR = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class,'qKy') and starts-with(text(),'"+str+"')]/preceding-sibling::div[@class='XqNaEv']")));
            Thread.sleep(1000);
            custR.click();
            Thread.sleep(5000);

        }catch(Exception e){
            System.out.println(e);
        }
        
    }
    public void getTitleAndImageURL(int numOfItems_toDisplay){
        try{
            ArrayList<Integer> reviewsInteger = new ArrayList<>();
        
            //locate reviews element to get text
            List<WebElement> numOfReviews = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='Wphh3N']"))); //driver.findElements(By.xpath(""));
            Thread.sleep(2000);
            
            //convert string values to Integer values for sorting
            for(WebElement wb : numOfReviews){
                String revText = wb.getText();
                // Remove non-numeric characters
                String cleanedData = revText.replace(",", "").replace("(", "").replace(")", "");
                // Convert to integer
                int revNum = Integer.parseInt(cleanedData);
                reviewsInteger.add(revNum);
            }
            //sort array in descending order
            Collections.sort(reviewsInteger, Collections.reverseOrder());

            int n = 0;
            if(reviewsInteger.size()<numOfItems_toDisplay){
                n = reviewsInteger.size();
            }else{
                n = numOfItems_toDisplay;
            }

            for(int i=0; i<n; i++){
                int numberR = reviewsInteger.get(i);
                String revString = "(" + NumberFormat.getNumberInstance(Locale.US).format(numberR) + ")";

                WebElement title = driver.findElement(By.xpath("//span[@class='Wphh3N' and text()='"+revString+"']/ancestor::div[contains(@class,'slAVV')]/a[@class='wjcEIp']"));
                String Title = title.getAttribute("title");

                WebElement imageLink = driver.findElement(By.xpath("//span[@class='Wphh3N' and text()='"+revString+"']/ancestor::div[contains(@class,'slAVV')]//img[contains(@class,'DByuf')]"));
                String imgUrl = imageLink.getAttribute("src");

                System.out.println(i+". Coffee Mug with highest reviews : " + revString);
                System.out.println("Coffee Mug title : " + Title);
                System.out.println("Coffee Mug image URL : " + imgUrl);

            }

        }
        catch(Exception e){
            System.out.println(e);
        }

        

        
    }


    
    


     




}
