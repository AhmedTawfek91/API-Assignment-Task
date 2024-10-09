package APIs;

import Enums.StatusCode;
import Responses.BookingData;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Objects;

import static Utils.Constants.BOOKING_ID;
import static Utils.EndPoints.GET_BOOKING_ITEMS;
import static Utils.Randoms.gson;
import static Utils.Randoms.softAssert;

public class GetItemListTest {

    Response response;
    BookingData bookingData;

    @When("Send valid request to get items list")
    public void GET_Items_list_POSITIVE() {
        response = RestAssured.given().relaxedHTTPSValidation().
                accept("*/*").header("Accept-Encoding", "gzip, deflate, br").
                header("Content-Type", "application/json").
                when().
                pathParam("id", BOOKING_ID).
                get(GET_BOOKING_ITEMS);
        bookingData = gson.fromJson(response.asPrettyString(), BookingData.class);
        System.out.println("Response is: " + response.asPrettyString());
        System.out.println("Response status is: " + response.getStatusCode());

    }

    @Then("user should receive item data")
    public void ASSERT_GET_ITEMS()
    {
        if(Objects.isNull(bookingData))
        {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.BAD_REQUEST.getCode());
        }
        else {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.OK.getCode());
            softAssert.assertNotNull(bookingData.getAdditionalneeds(), "additionalneeds");
            softAssert.assertNotNull(bookingData.getFirstname(), "firstname");
            softAssert.assertNotNull(bookingData.getLastname(), "lastname");

        }
    }
}
