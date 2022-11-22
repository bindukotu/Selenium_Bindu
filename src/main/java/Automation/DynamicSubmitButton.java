package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DynamicSubmitButton
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
        //get present window id
        String oldWindow=driver.getWindowHandle();
        System.out.println("old window id :"+oldWindow);
        //Select dyanmic submit button id
        driver.findElement(By.xpath("//*[@href=\"submit_button_clicked.php\"]")).click();
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        System.out.println("no of windows : "+li.size());
        //check the window new or old
        for(String win:li)
        {
            if(!win.equals(oldWindow))
            {
                //driver switch to new window
                driver.switchTo().window(win);
                //select the start with tab
                driver.findElement(By.xpath("//*[@href='#example-1-tab-1']")).click();
                //go to frame
                WebElement frame1= driver.findElement(By.xpath("//*[@id='example-1-tab-1']/child::div/child::iframe"));
                //driver go to frame
                driver.switchTo().frame(frame1);
                //send val to text box
                driver.findElement(By.xpath("//*[@lang='en'][1]/child::body/child::form/child::input[1]")).sendKeys("nnn");
                driver.findElement(By.xpath("//*[@lang='en'][1]/child::body/child::form/child::input[2]")).click();
                  Thread.sleep(5000);

                //select the end with tab
                driver.switchTo().window(win);
                driver.findElement(By.xpath("//*[@href='#example-1-tab-2']")).click();
                frame1=driver.findElement(By.xpath("//*[@id='example-1-tab-2']/child::div/child::iframe"));
                driver.switchTo().frame(frame1);
              //  driver.findElement(By.xpath())
            }
        }
        //driver close
        //driver.close();
    }
}
