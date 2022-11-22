package Automation;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RegistrationClass
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
        //click registration link
        String oldWindow=driver.getWindowHandle();
        System.out.println("old window id : "+oldWindow);
       driver.findElement(By.xpath("//*[@href='registration.php']")).click();
        //get no of windows id
        List<String> li=new ArrayList<String>(driver.getWindowHandles());
        System.out.println("no of windows : "+li.size());
        //go to new window
        for(String win:li)
        {
            if(!win.equals(oldWindow))
            {
                //driver switch to new window
                driver.switchTo().window(win);
                //fill the First name txt box
               driver.findElement(By.xpath("//*[@class='fieldset']/child::p[1]/child::input")).sendKeys("bindu");
                //fill the Last name txt box
                driver.findElement(By.xpath("//*[@class='fieldset']/child::p[2]/child::input")).sendKeys("kotu");
               //select the radio button
                driver.findElement(By.xpath("//*[@class='radio_wrap']/child::label[1]/child::input")).click();
                //select the checkbox (hobbies)
                driver.findElement(By.xpath("//*[@class='fieldset padding-bottom']/child::div/child::label[1]/child::input")).click();
                driver.findElement(By.xpath("//*[@class='fieldset padding-bottom']/child::div/child::label[2]/child::input")).click();
                driver.findElement(By.xpath("//*[@class='fieldset padding-bottom']/child::div/child::label[3]/child::input")).click();
                //select the dropdown list (Country)
                driver.findElement(By.xpath("//*/fieldset[4]/child::select/child::option[text()='India']")).click();
                //select the dropdown list (dob)
                //month feild
                driver.findElement(By.xpath("//*[@class='time_feild'][1]/child::select/child::option[text()='1']")).click();
                //day feild
                driver.findElement(By.xpath("//*[@class='time_feild'][2]/child::select/child::option[text()='1']")).click();
                //year feild
                driver.findElement(By.xpath("//*[@class='time_feild'][3]/child::select/child::option[text()='2014']")).click();
                //set value in phone no
                driver.findElement(By.name("phone")).sendKeys("1234567891");
                //set value in username
                driver.findElement(By.name("username")).sendKeys("bb");
                //set value in Email
                driver.findElement(By.name("email")).sendKeys("bb@gmail.com");
                //upload the img on page
                WebElement fileUpload=driver.findElement(By.xpath("//*/fieldset[9]/child::input[@type='file']"));
                fileUpload.sendKeys("/Users/indrahasreddy/Desktop/bindu/191_saibaba007.jpeg");
                //set value in About ur self
                String s="Hi My name is bindu. " +
                        "\nI completed M.C.A in 2008";
                driver.findElement(By.xpath("//*/fieldset[10]/child::textarea")).sendKeys(s);
                //set value in password
                driver.findElement(By.name("password")).sendKeys("b123");
                //set value in conform password
                driver.findElement(By.name("c_password")).sendKeys("b123");
                //click the submit button
                driver.findElement(By.xpath("//*[@type='submit']")).click();
            }
        }
        //driver close
      //  driver.close();
    }
}
