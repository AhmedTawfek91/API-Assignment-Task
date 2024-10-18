package APIs;

import Enums.StatusCode;
import Responses.BookingIds;
import Responses.BookingIdsResponse;
import com.google.gson.reflect.TypeToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import static Utils.Constants.*;
import static Utils.EndPoints.*;
import static Utils.Randoms.gson;
import static Utils.Randoms.softAssert;


public class GetBookingIdsTest {

    Response response;
    BookingIdsResponse bookingIdsResponse;


    @When("user send a valid request to get all booking Ids")
    public void Get_BookingIDS_POSITIVE()
    {
        response = RestAssured.given().relaxedHTTPSValidation().
                accept("*/*").header("Accept-Encoding", "gzip, deflate, br").
                header("Content-Type", "application/json").
                when().
                get(GET_BOOKING_IDS);
        String jsonResponse = response.asPrettyString();
//        bookingIdsResponse = gson.fromJson(response.asPrettyString(), BookingIdsResponse.class);
        List<BookingIds> bookingIdsResponse = gson.fromJson(jsonResponse, new TypeToken<List<BookingIds>>(){}.getType());
        System.out.println("Response is: " + response.asPrettyString());
        System.out.println("Response status is: " + response.getStatusCode());
        BOOKINGIDS = new ArrayList<>();
        for(BookingIds bookingId: bookingIdsResponse)
        {
            BOOKINGIDS.add(bookingId.getBookingid());
            BOOKINGID = new Random().nextInt(BOOKINGIDS.size());
        }
    }

    @Then("user receive booking Ids successfully")
    public void ASSERT_Get_ALL_Booking_Ids()
    {
        if(Objects.isNull(bookingIdsResponse))
        {
            softAssert.assertEquals(response.getStatusCode(), StatusCode.BAD_REQUEST.getCode());
        }
        else{

            softAssert.assertEquals(response.getStatusCode(), StatusCode.OK.getCode());
            softAssert.assertNotNull(bookingIdsResponse.getData());
            String jsonResponse = response.asPrettyString();
            List<BookingIds> bookingIdsResponse = gson.fromJson(jsonResponse, new TypeToken<List<BookingIds>>(){}.getType());
            for(BookingIds bookingId: bookingIdsResponse) {
                softAssert.assertNotNull(String.valueOf(bookingId.getBookingid()));
            }
    }
    }
}
