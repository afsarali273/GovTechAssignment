package govtech.pageObjects;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OppenheimerHomePage {

    private static final Logger LOG = LogManager.getLogger(OppenheimerHomePage.class);
    WebDriver driver;


    public OppenheimerHomePage(WebDriver driver){

        PageFactory.initElements(driver, this);
        this.driver=driver;
    }
    /**
     * @author Afsar Ali
     * @code Login page objects
     */


    @FindBy(how= How.CSS , using="body>div>h1")
    private WebElement txt_title;

    @FindBy(how= How.CSS , using=".custom-file-input")
    private WebElement input_fileUpload;

    @FindBy(how= How.CSS , using="button.btn.btn-primary")
    private WebElement btn_Refresh_Tax_Relief_Table;

    @FindBy(how= How.CSS , using=".table>tbody>tr")
    private List<WebElement> list_table_rows;

    @FindBy(how= How.CSS , using=".btn.btn-danger.btn-block")
    private WebElement btn_dispense_now;

    @FindBy(how= How.CSS , using=".display-4.font-weight-bold")
    private WebElement txt_cash_dispensed;

    public OppenheimerHomePage verifyHomePage(){

        Assert.assertEquals("Technical Challenge for CDS",driver.getTitle());
        Assert.assertEquals("The Oppenheimer Project",txt_title.getText());
        Assert.assertEquals(true,btn_Refresh_Tax_Relief_Table.isDisplayed());

        return this;
    }

    public OppenheimerHomePage verifyDispenseButton(){

        Assert.assertEquals("rgba(220, 53, 69, 1)",btn_dispense_now.getCssValue("background-color"));
        Assert.assertEquals("Dispense Now",btn_dispense_now.getText());

        return this;
    }

    public OppenheimerHomePage clickDispenseButton(){
        btn_dispense_now.click();
        System.out.println("Dispense button is Clicked");

        return this;
    }

    public OppenheimerHomePage verifyNavigationToDispensePage(){

        Assert.assertTrue("Navigation did not happened",driver.getCurrentUrl().contains("http://167.99.65.170/dispense"));

        Assert.assertEquals("Cash dispensed",txt_cash_dispensed.getText());

        return this;
    }


    public void verifyFileUploadFunctionality(String file){

        //Upload file
        input_fileUpload.sendKeys(file);

        btn_Refresh_Tax_Relief_Table.click();


        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {

            if(entry.getMessage().contains("http://167.99.65.170/calculator/uploadLargeFileForInsertionToDatabase")){

                System.out.println(entry.getMessage());

                Assert.assertEquals("File upload was unsuccessful : FAILED","SUCCESS!!",entry.getMessage());

            }
        }



    }
}
