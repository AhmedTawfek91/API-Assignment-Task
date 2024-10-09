package Responses;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BookingData {
    private String firstname;
    private String lastname;
    private float totalprice;
    private boolean depositpaid;
    private BookingDates bookingdates;
    private String additionalneeds;
}
