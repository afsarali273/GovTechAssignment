package govtech.stepDefinations;
import govtech.apiutils.ApiUtils;
import govtech.frameworkcore.DriverFactory;
import govtech.pageObjects.PageFactoryObject;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import io.cucumber.datatable.DataTable;
import org.apache.commons.lang.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;


public class ClerksAction {

    public static  Response response;
    public static  RequestSpecification request;
    private JSONObject jsonBody;
    private JSONArray jsonArray;
    public static PageFactoryObject pagefactory;
    public static DriverFactory driverFactory;


    @Given("I set Headers for request")
    public void i_set_Headers_for_request(io.cucumber.datatable.DataTable dt) {
        List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        for(int i=0; i<list.size(); i++) {
            //request= given().param(list.get(i).get("KEY"), list.get(i).get("VALUE"));
            request = given().headers(list.get(i).get("KEY"),list.get(i).get("VALUE"));
        }
    }

    @Given("Request body for Single working class hero with below details")
    public void request_body_for_Single_working_class_hero_with_below_details(io.cucumber.datatable.DataTable dataTable) {

        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);
        for(int i=0; i<list.size(); i++) {

            jsonBody = new JSONObject();
            jsonBody.putAll(list.get(i));

            System.out.println("JSON object is : "+jsonBody.toJSONString() );
        }
        request.body(jsonBody);
    }

    @When("I send POST request to {string}")
    public void i_send_POST_request_to(String string) {
        response =  request.when()
                    .post(ApiUtils.baseURL+string) ;

        System.out.println("Response :  "+response.asString());
    }

    @Then("The status code is {int}")
    public void the_status_code_is(Integer int1) {

        response.then()
                .assertThat()
                .statusCode(int1);
    }

    @Then("Response body is Alright")
    public void response_body_is_Alright() {
        response.then().assertThat().log().body().toString().contentEquals("Alright");
    }


    @Given("Request body for Multiple working class hero with below details")
    public void request_body_for_Multiple_working_class_hero_with_below_details(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> list = dataTable.asMaps(String.class, String.class);

        jsonArray = new JSONArray();
        for(int i=0; i<list.size(); i++) {

            jsonBody = new JSONObject();

            jsonBody.put("natid",list.get(i).get("natid"));
            jsonBody.put("name",list.get(i).get("name"));
            jsonBody.put("gender",list.get(i).get("gender"));
            jsonBody.put("birthday",list.get(i).get("birthday"));
            jsonBody.put("salary",list.get(i).get("salary"));
            jsonBody.put("tax",list.get(i).get("tax"));

           jsonArray.add(jsonBody);


        }
        System.out.println("JSON object is : "+jsonArray.toJSONString() );
        request.body(jsonArray);
    }

    @Given("Request body for Multiple working class hero with csv file {string}")
    public void request_body_for_Multiple_working_class_hero_with_csv_file(String filename) {

        request.
                multiPart("file", new File(ApiUtils.testDataPath+filename));
    }

    @Given("I have a valid csv file named {string}")
    public void i_have_a_valid_csv_file_named(String string) {

    }

    @When("I navigate to baseurl in chrome browser")
    public void i_navigate_to_baseurl_in_chrome_browser() throws Exception{

        driverFactory = DriverFactory.getInstance();
        driverFactory.setDriver("Chrome");
        pagefactory=new PageFactoryObject(driverFactory.getDriver());
        driverFactory.getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driverFactory.getDriver().get(ApiUtils.baseURL);
        driverFactory.getDriver().manage().window().maximize();
    }

    @Then("I Verify UI for Oppenheimer project")
    public void i_Verify_UI_for_Oppenheimer_project() {
        pagefactory.getHomePage().verifyHomePage();

    }

    @Then("I shall be able to upload csv file successfully using UI")
    public void i_shall_be_able_to_upload_csv_file_using_UI() {
        pagefactory.getHomePage().verifyFileUploadFunctionality(ApiUtils.testDataPath+"working-hero.csv");
        ClerksAction.driverFactory.clearCurrentDriver();
    }

}
