package com.lindar.getaddress.io.client.api;

import com.google.gson.reflect.TypeToken;
import com.lindar.getaddress.io.client.util.GetAddressConfigs;
import com.lindar.getaddress.io.client.vo.GetAddressResponse;
import com.lindar.getaddress.io.client.vo.PrivateAddressVO;
import com.lindar.wellrested.vo.Result;
import lindar.acolyte.util.UrlAcolyte;

import java.util.List;

public class PrivateAddressResource extends AbstractResource {

    private static final String PRIVATE_ADDRESS_ENDPOINT = "/private-address";

    public PrivateAddressResource(GetAddressConfigs getAddressConfigs) {
        super(getAddressConfigs);
    }

    public Result<List<PrivateAddressVO>> list(String postcode) {
        return getListRequest(createFullPath(postcode), new TypeToken<List<PrivateAddressVO>>() {});
    }

    public Result<GetAddressResponse> add(String postcode, PrivateAddressVO privateAddressVO) {
        return postRequest(createFullPath(postcode), privateAddressVO);
    }

    public Result<GetAddressResponse> delete(String id, String postcode) {
        return deleteRequest(createFullPath(postcode), id);
    }

    private String createFullPath(String postcode) {
        return UrlAcolyte.safeConcat(PRIVATE_ADDRESS_ENDPOINT, postcode.replaceAll(" ", ""));
    }
}
