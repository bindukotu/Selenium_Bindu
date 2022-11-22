package Automation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class AlertClass
{
    public static void main(String[] args) throws InterruptedException {
        //set the system page
        System.setProperty("webdriver.chrome.driver","src/main/resources/Drivers/chromedriver");
        //create driver object
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.way2automation.com/way2auto_jquery/automation-practice-site.html");
        //maximize window
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //get old window id
        String oldWindow=driver.getWindowHandle();
        System.out.println(" old window id  : "+oldWindow);
        //click the alert link/img
        driver.findElement(By.xpath("//*[@href='alert.php']")).click();
        //To get the list of windows ids
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        System.out.println("No of Windows : "+li.size());
        //move to next window
        for(String s:li)
        {
            if(!s.equals(oldWindow))
            {
                driver.switchTo().window(s);
                //select the tab from new window
               WebElement tabSelect= driver.findElement(By.xpath("//*[@href='#example-1-tab-1']"));
               //click the tab1
               tabSelect.click();
               //go to frame
               WebElement frame1=driver.findElement(By.className("demo-frame"));
               driver.switchTo().frame(frame1);
               //click the button to display alert box
               driver.findElement(By.tagName("button")).click();
               Thread.sleep(5000);
               //go to alert box click ok button
               Alert alert=driver.switchTo().alert();
               alert.accept();
            }
        }
        //driver close
       // driver.close();
    }
}
