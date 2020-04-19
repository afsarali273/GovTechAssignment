package govtech.stepDefinations;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class GovernorAction {


    @Given("Verify UI button with its color and text")
    public void verify_UI_button_with_its_color_and_text() {

        ClerksAction.pagefactory.getHomePage()
                                .verifyHomePage()
                                .verifyDispenseButton();
    }

    @When("I click on the Dispense button")
    public void i_click_on_the_Dispense_button() {
        ClerksAction.pagefactory.getHomePage().clickDispenseButton();
    }

    @Then("I Shall navigate to a page which says {string}")
    public void i_Shall_navigate_to_a_page_which_says(String string) {
        ClerksAction.pagefactory.getHomePage().verifyNavigationToDispensePage();
        ClerksAction.driverFactory.clearCurrentDriver();
    }
}
