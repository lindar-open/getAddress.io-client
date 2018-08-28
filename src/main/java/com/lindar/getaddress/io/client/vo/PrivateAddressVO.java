package com.lindar.getaddress.io.client.vo;

import lombok.Data;

@Data
public class PrivateAddressVO {

    private String id;
    private String line1;
    private String line2;
    private String line3;
    private String line4;
    private String locality;
    private String townOrCity;
    private String county;

}
