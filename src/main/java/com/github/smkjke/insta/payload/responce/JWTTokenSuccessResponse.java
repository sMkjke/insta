package com.github.smkjke.insta.payload.responce;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {

    private boolean success;
    private String token;

}
