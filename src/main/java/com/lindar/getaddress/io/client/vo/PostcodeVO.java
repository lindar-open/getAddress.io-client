package com.lindar.getaddress.io.client.vo;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class PostcodeVO implements Serializable {

    private static final long serialVersionUID = 5120087426200556607L;

    @SerializedName("Longitude")
    private String longitude;
    @SerializedName("Latitude")
    private String latitude;
    
    @SerializedName("Addresses")
    private List<String> addresses;
    
    private List<AddressVO> compiledAddresses = new ArrayList<>();
}
