package Enums;

import lombok.Getter;

@Getter
public enum StatusCode {

    OK(200),
    BAD_REQUEST(400),
    UNAUTHORIZED(401);

    private final int code;

    StatusCode(int code){
        this.code=code;
    }



}
