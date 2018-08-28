package com.lindar.getaddress.io.client.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class GetAddressResponse {

    private String id;

    @SerializedName(value = "message",alternate = "Message")
    private String message;

}
