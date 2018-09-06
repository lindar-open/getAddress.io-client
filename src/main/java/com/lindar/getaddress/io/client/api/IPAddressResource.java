package com.lindar.getaddress.io.client.api;

import com.google.gson.reflect.TypeToken;
import com.lindar.getaddress.io.client.util.GetAddressConfigs;
import com.lindar.getaddress.io.client.vo.GetAddressResponse;
import com.lindar.getaddress.io.client.vo.IPAddressVO;
import com.lindar.wellrested.vo.Result;

import java.util.List;


public class IPAddressResource extends AbstractResource {

    private static final String IP_ADDRESS_ENDPOINT = "/security/ip-address-whitelist";

    public IPAddressResource(GetAddressConfigs getAddressConfigs) {
        super(getAddressConfigs);
    }

    public Result<List<IPAddressVO>> list() {
        return getListRequest(IP_ADDRESS_ENDPOINT, new TypeToken<List<IPAddressVO>>() {});
    }

    public Result<GetAddressResponse> add(IPAddressVO ipAddressVO) {
        return postRequest(IP_ADDRESS_ENDPOINT, ipAddressVO);
    }

    public Result<GetAddressResponse> delete(String id) {
        return deleteRequest(IP_ADDRESS_ENDPOINT, id);
    }

}
