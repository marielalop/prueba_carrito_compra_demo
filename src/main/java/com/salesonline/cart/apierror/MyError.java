package com.salesonline.cart.apierror;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@ToString
public class MyError extends Throwable{

    private HttpStatus status;
    private int code;
    private String message;
    private String backendMessage;

    public MyError(HttpStatus status, int code, String message, String backendMessage) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.backendMessage = backendMessage;
    }

}
