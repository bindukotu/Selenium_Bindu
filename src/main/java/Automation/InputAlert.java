package Automation;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class InputAlert
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
        //Click to alert img
        String oldWindow=driver.getWindowHandle();
        System.out.println("old window id : "+oldWindow);
        driver.findElement(By.xpath("//*[@href='alert.php']")).click();
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        System.out.println("no of windows :"+li.size());
        //go to new window
        for(String win:li)
        {
            if(!win.equals(oldWindow))
            {
                driver.switchTo().window(win);
                driver.findElement(By.xpath("//*[@href='#example-1-tab-2']")).click();
                //switch to frame
                WebElement frame1=driver.findElement(By.xpath("//*[@src='alert/input-alert.html']"));
                driver.switchTo().frame(frame1);
                driver.findElement(By.tagName("button")).click();

                //go to alert box
                Alert alert=driver.switchTo().alert();
                alert.sendKeys("bindu");
                Thread.sleep(5000);
                alert.accept();
//                driver.switchTo().frame(frame1);
              String txt=driver.findElement(By.id("demo")).getText();
                System.out.println(txt);

            }
        }
        //driver close
        //driver.close();
    }
}
