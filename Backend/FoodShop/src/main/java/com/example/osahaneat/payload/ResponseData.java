package com.example.osahaneat.payload;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseData {
    private int status = 200;
    private Object descrip;
    private Object data;
    private boolean isSuccess = true;



    public ResponseData() {
    }
}

