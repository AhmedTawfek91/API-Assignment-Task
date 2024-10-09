package Responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class AddItemResponse {
    private int bookingid;
    private BookingData booking;
}
