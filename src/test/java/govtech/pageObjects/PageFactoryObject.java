package govtech.pageObjects;

import org.openqa.selenium.WebDriver;

public class PageFactoryObject {


    OppenheimerHomePage myacc;


    /*** Getters ***/

    public OppenheimerHomePage getHomePage() {
        return myacc;
    }


    public PageFactoryObject(WebDriver driver){

        myacc = new OppenheimerHomePage(driver);
    }





}
