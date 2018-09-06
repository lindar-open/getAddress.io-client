package com.lindar.getaddress.io.client.api;

import com.google.gson.reflect.TypeToken;
import com.lindar.getaddress.io.client.util.GetAddressConfigs;
import com.lindar.getaddress.io.client.vo.DomainVO;
import com.lindar.getaddress.io.client.vo.GetAddressResponse;
import com.lindar.wellrested.vo.Result;

import java.util.List;

public class DomainResource extends AbstractResource {

    private static final String WHITE_LIST_ENDPOINT = "/security/domain-whitelist";

    public DomainResource(GetAddressConfigs getAddressConfigs) {
        super(getAddressConfigs);
    }

    public Result<List<DomainVO>> list() {
        return getListRequest(WHITE_LIST_ENDPOINT, new TypeToken<List<DomainVO>>() {});
    }

    public Result<GetAddressResponse> add(DomainVO domainVO) {
        return postRequest(WHITE_LIST_ENDPOINT, domainVO);
    }

    public Result<GetAddressResponse> delete(String id) {
        return deleteRequest(WHITE_LIST_ENDPOINT, id);
    }

}
