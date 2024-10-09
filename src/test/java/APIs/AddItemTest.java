package APIs;

import Enums.StatusCode;
import Responses.AddItemResponse;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Objects;

import static Utils.Randoms.*;
import static Utils.Constants.*;
import static Utils.EndPoints.ADD_ITEM;


public class AddItemTest {

    Response response;
    AddItemResponse addItemResponse;

    @When("User send valid request to add item")
            public void ADD_ITEM_POSITIVE() {
               String request =  "{\n" +
                "  \"firstname\": \"Jim\",\n" +
                "  \"lastname\": \"Brown\",\n" +
                "  \"totalprice\": 111,\n" +
                "  \"depositpaid\": true,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2018-01-01\",\n" +
                "    \"checkout\": \"2019-01-01\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        response = RestAssured.given().
                   relaxedHTTPSValidation().
                   accept("*/*").header("Accept-Encoding", "gzip, deflate, br").
                   header("Content-Type", "application/json")
                  .body(request)
                  .when()
                  .post(ADD_ITEM);
        System.out.println("Request IS: " + request);
        addItemResponse = gson.fromJson(response.asPrettyString(), AddItemResponse.class);
        System.out.println("Response is: " + response.asPrettyString());
        System.out.println("Response status is: " + response.getStatusCode());
        if (Objects.nonNull(addItemResponse)) {
            BOOKING_ID = String.valueOf(addItemResponse.getBookingid());
        }
        System.out.println("Booking ID is: " +  BOOKING_ID);
    }

    @When("User send invalid request to add item with null total price")
    public void ADD_ITEM_NullTotalPrice_NEGATIVE() {
        String request = "{\n" +
                "  \"firstname\": \"Jim\",\n" +
                "  \"lastname\": \"Brown\",\n" +
                "  \"totalprice\": ,\n" +
                "  \"depositpaid\": true,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2018-01-01\",\n" +
                "    \"checkout\": \"2019-01-01\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        response = RestAssured.given().
                relaxedHTTPSValidation().
                accept("*/*").header("Accept-Encoding", "gzip, deflate, br").
                header("Content-Type", "application/json")
                .body(request)
                .when()
                .post(ADD_ITEM);
        System.out.println("Request IS: " + request);
        System.out.println("Response is: " + response.asPrettyString());
        System.out.println("Response status is: " + response.getStatusCode());
    }

    @When("User send invalid request to add item with null deposit paid")
    public void ADD_ITEM_NullDepositPaid_NEGATIVE() {
        String request = "{\n" +
                "  \"firstname\": \"Jim\",\n" +
                "  \"lastname\": \"Brown\",\n" +
                "  \"totalprice\": 111,\n" +
                "  \"depositpaid\": ,\n" +
                "  \"bookingdates\": {\n" +
                "    \"checkin\": \"2018-01-01\",\n" +
                "    \"checkout\": \"2019-01-01\"\n" +
                "  },\n" +
                "  \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        response = RestAssured.given().
                relaxedHTTPSValidation().
                accept("*/*").header("Accept-Encoding", "gzip, deflate, br").
                header("Content-Type", "application/json")
                .body(request)
                .when()
                .post(ADD_ITEM);
        System.out.println("Request IS: " + request);
        System.out.println("Response is: " + response.asPrettyString());
        System.out.println("Response status is: " + response.getStatusCode());
    }

    @Then("user should receive the added item data")
    public void ASSERT_ADD_ITEMS()
    {
        if(Objects.isNull(addItemResponse))
        {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.BAD_REQUEST.getCode());
        }
        else {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.OK.getCode());
            softAssert.assertNotNull(addItemResponse.getBooking().getAdditionalneeds(), "additionalneeds");
            softAssert.assertNotNull(addItemResponse.getBooking().getFirstname(), "firstname");
            softAssert.assertNotNull(addItemResponse.getBooking().getLastname(), "lastname");

        }
    }

    @Then("user should receive an error")
    public void ASSERT_ADD_ITEMS_Negative()
    {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.BAD_REQUEST.getCode());
        }
    }


