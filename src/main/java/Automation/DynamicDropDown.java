package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicDropDown
{
    public static void main(String[] args) throws InterruptedException {
        //set the driver path
        System.setProperty("webdriver.chrome.driver","src/main/resources/Drivers/chromedriver");
        //create driver obj
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.way2automation.com/way2auto_jquery/automation-practice-site.html");
        //Maximizing window
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //getting present window id
        String oldWindow=driver.getWindowHandle();
        System.out.println("old window : "+oldWindow);
        //click the dynamic Element dropdown
        driver.findElement(By.xpath("//*[@href='dropdown.php']")).click();
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        System.out.println("no of windows : "+li.size());
        //go to next window
        for(String win:li)
        {
            //chek its new or old window
            if(!win.equals(oldWindow))
            {
                //switch driver to next window
                driver.switchTo().window(win);
                //select country tab
                driver.findElement(By.xpath("//*[@href='#example-1-tab-1']")).click();
                //driver switch to frame
                WebElement frame1=driver.findElement(By.xpath("//*[@id='example-1-tab-1']/child::div/child::iframe"));
                driver.switchTo().frame(frame1);
                //select the country
                driver.findElement(By.tagName("select")).sendKeys("India");
                Thread.sleep(5000);
                //click the Enter the country tab
                driver.switchTo().window(win);
                driver.findElement(By.xpath("//*[@href='#example-1-tab-2']")).click();
                //driver switch to frame
                WebElement frame2=driver.findElement(By.xpath("//*[@id='example-1-tab-2']/child::div/child::iframe"));
                driver.switchTo().frame(frame2);
                //select the country
                WebElement combo=driver.findElement(By.id("combobox"));
                combo.click();
                combo.sendKeys("France");
                combo.click();

            }
        }
        //driver close
        //driver.close();
    }
}
