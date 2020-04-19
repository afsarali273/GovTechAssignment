package govtech.stepDefinations;

import govtech.apiutils.ApiUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;

import static io.restassured.RestAssured.given;

public class BookkeeperAction {


    private JSONObject jsonBody;
    String natid_new;
    String name_new;


    @When("I send GET request to {string}")
    public void i_send_GET_request_to(String string) {
        ClerksAction.response = ClerksAction.request.when().get(ApiUtils.baseURL + string);
        System.out.println("Response :  " + ClerksAction.response.asString());
    }

    @Then("I shall get a list consist of {string},{string} and {string}")
    public void i_shall_get_a_list_consist_of_and(String natid, String name, String relief) throws Exception {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(ClerksAction.response.asString());

        for (int i = 0; i < jsonArray.size(); i++) {

            //Validate natid,name,tax relief from response
            Assert.assertEquals(true, ((JSONObject) jsonArray.get(i)).containsKey(natid));
            Assert.assertEquals(true, ((JSONObject) jsonArray.get(i)).containsKey(name));
            Assert.assertEquals(true, ((JSONObject) jsonArray.get(i)).containsKey(relief));
        }


    }


    @Given("I added working hero to the DB")
    public void i_added_working_hero_to_the_DB() {

        natid_new = ApiUtils.randomNumbers(10);
        name_new = ApiUtils.randomString(10);

        jsonBody = new JSONObject();
        jsonBody.put("natid", natid_new);
        jsonBody.put("name", name_new);
        jsonBody.put("gender", "M");
        jsonBody.put("birthday", "12031950");
        jsonBody.put("salary", "8765478");
        jsonBody.put("tax", "8765");

        given().headers("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(ApiUtils.baseURL + "calculator/insert")
                .then()
                .statusCode(202);
    }

    @When("I retrieve data from Database using GET api")
    public void i_retrieve_data_from_Database_using_GET_api() {

        ClerksAction.response = given().headers("Content-Type", "application/json")
                .when()
                .get(ApiUtils.baseURL + "calculator/taxRelief");
    }

    @Then("I shall see tax relief computation obeys formula {string}")
    public void i_shall_see_tax_relief_computation_obeys_formula(String string) throws Exception {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(ClerksAction.response.asString());

        for (int i = 0; i < jsonArray.size(); i++) {


            if (((JSONObject) jsonArray.get(i)).get("name").toString().contains(name_new)) {

                System.out.println("Inside newly created data");

                //validate the formula ((Salary-tax paid) * variable)+gender bonus
                // jsonBody.put("gender","M");
                // jsonBody.put("birthday","12031950");
                // jsonBody.put("salary","8765478");
                // jsonBody.put("tax","8765");

                //  ((8765478 - 8765) * 0.367 )+ 0   = 3213713.671      //Formula

                Assert.assertEquals(3213713.68, Double.parseDouble(((JSONObject) jsonArray.get(i)).get("relief").toString()), 0.00);
            }
        }
    }

    @Then("I shall see natid field must be masked from the 5th character onwards with dollar sign ‘$’")
    public void i_shall_see_natid_field_must_be_masked_from_the_5th_character_onwards_with_dollar_sign_$() throws Exception {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(ClerksAction.response.asString());

        for (int i = 0; i < jsonArray.size(); i++) {

            String natid = ((JSONObject) jsonArray.get(i)).get("natid").toString();

            String regex = natid.split("([^0-9])+")[0];

            Assert.assertEquals(true, regex.length() == 4);
        }
    }

    @Given("I added working hero to the DB with less tax relief")
    public void i_added_working_hero_to_the_DB_with_less_tax_relief() {

        natid_new = ApiUtils.randomNumbers(10);
        name_new = ApiUtils.randomString(10);

        jsonBody = new JSONObject();
        jsonBody.put("natid", natid_new);
        jsonBody.put("name", name_new);
        jsonBody.put("gender", "M");
        jsonBody.put("birthday", "12031950");
        jsonBody.put("salary", "12345");
        jsonBody.put("tax", "12300");

        given().headers("Content-Type", "application/json")
                .body(jsonBody)
                .when()
                .post(ApiUtils.baseURL + "calculator/insert")
                .then()
                .statusCode(202);

    }

    @Then("I shall see tax relief computation obeys formula {string} and validate it to be {double}")
    public void i_shall_see_tax_relief_computation_obeys_formula_and_validate(String string ,Double tax) throws Exception {

        JSONParser jsonParser = new JSONParser();
        JSONArray jsonArray = (JSONArray) jsonParser.parse(ClerksAction.response.asString());

        for (int i = 0; i < jsonArray.size(); i++) {


            if (((JSONObject) jsonArray.get(i)).get("name").toString().contains(name_new)) {

                System.out.println("Inside newly created data");

                //validate the formula ((Salary-tax paid) * variable)+gender bonus

                //  ((12345 - 12300) * 0.367 )+ 0   =  16.515     //Formula

                Assert.assertEquals(tax, Double.parseDouble(((JSONObject) jsonArray.get(i)).get("relief").toString()), 0.00);
            }


        }

    }
}
