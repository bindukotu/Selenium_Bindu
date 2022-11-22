package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Draggable
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
        driver.findElement(By.xpath("//*[@href='draggable.php']")).click();//href="draggable.php"
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        for(String win:li) {
            if (!win.equals(oldWindow)) {
                driver.switchTo().window(win);
                WebElement frame1 = driver.findElement(By.xpath("//*[@src='draggable/default.html']"));
                driver.switchTo().frame(frame1);
                WebElement sourceElement = driver.findElement(By.xpath("//*[@id='draggable']"));
                Actions actions=new Actions(driver);
               // actions.dragAndDropBy(sourceElement,209,519).build().perform();
                //actions.clickAndHold(sourceElement).release().build().perform();
            }
        }
        //driver close
        //driver.close();
    }
}
