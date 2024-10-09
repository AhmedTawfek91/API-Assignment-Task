package Requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddItemRequest {
    private String firstname;
    private String lastname;
    private float totalprice;
    private boolean depositpaid;
    private DataItems bookingdates;
    private String additionalneeds;

    }


