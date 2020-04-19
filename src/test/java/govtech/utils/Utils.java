package govtech.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import static java.lang.Math.*;
import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.random;
import static org.apache.commons.lang3.StringUtils.leftPad;

public class Utils {

    public WebDriver driver;

    public Utils(WebDriver driver) {
        this.driver=driver;
    }

    public Utils(){

    }

    public void select_dropdownboxByValue(WebElement ele, String enterValue) {
        Select se=new Select(ele);
        se.selectByValue(enterValue);
    }

    public  void select_dropdownboxByVisibleText(WebElement ele,String visibleText) {
        Select se=new Select(ele);
        se.selectByVisibleText(visibleText);
    }

    public  String uniqueTextGenerate(int lenthofString) {
        StringBuffer sb = new StringBuffer();
        for (int i = lenthofString; i > 0; i -= 12) {
            int n = min(12, abs(i));
            sb.append(leftPad(Long.toString(round(random() * pow(36, n)), 36), n, '0'));
        }
        return sb.toString();
    }

    public  void takeScreenShots(WebDriver driver) {
        // Take screenshot and store as a file format
        File src= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            Date d=new Date();
            FileUtils.copyFile(src, new File(System.getProperty("user.dir")+"/ScreenShots/"+d.toString()+".png"));
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void MovetoElementAndClick(WebElement element){

        Actions act = new Actions(driver);
        act.moveToElement(element).click().build().perform();
    }

    public void JS_ScrollToView(WebElement element){
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public void hardWait(int sec){

        try {
            Thread.sleep(sec*1000);
        }catch (Exception e){

        }
    }
}
