package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DroppableClass
{
    public static void main(String[] args) {
        //set the driver path
        System.setProperty("webdriver.chrome.driver","src/main/resources/Drivers/chromedriver");
        //create driver obj
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.way2automation.com/way2auto_jquery/automation-practice-site.html");
        //Maximizing window
        driver.manage().timeouts().implicitlyWait(200, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //get present window id
        String oldWindow=driver.getWindowHandle();
        //click the droppable link
        driver.findElement(By.xpath("//*[@href='droppable.php']")).click();
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        for(String win:li)
        {
            if(!win.equals(oldWindow)) {
                driver.switchTo().window(win);
                driver.findElement(By.xpath("//*[@href=\"#example-1-tab-1\"]")).click();
                WebElement frame1 = driver.findElement(By.xpath("//*[@src='droppable/default.html']"));
                driver.switchTo().frame(frame1);
                WebElement sourceElement = driver.findElement(By.xpath("//*[@id='draggable']"));
                WebElement targetElement = driver.findElement(By.xpath("//*[@id='droppable']"));
                Actions actions = new Actions(driver);
                actions.clickAndHold(sourceElement).release(targetElement).build().perform();
            }
        }
        //driver close
        //driver.close();
    }
}
