package com.lindar.getaddress.io.client.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.lindar.getaddress.io.client.util.GetAddressConfigs;
import com.lindar.getaddress.io.client.vo.GetAddressResponse;
import com.lindar.wellrested.WellRestedRequest;
import com.lindar.wellrested.vo.Result;
import com.lindar.wellrested.vo.ResultBuilder;
import com.lindar.wellrested.vo.WellRestedResponse;
import lindar.acolyte.util.UrlAcolyte;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractResource {

    private GetAddressConfigs getAddressConfigs;

    AbstractResource(GetAddressConfigs getAddressConfigs){
        this.getAddressConfigs = getAddressConfigs;
    }

    <T> Result<T> getRequest(String endpoint, TypeToken<T> tTypeToken){
        String path = UrlAcolyte.addParams(getFullPath(endpoint) , getParamMap());
        WellRestedResponse wellRestedResponse = WellRestedRequest.builder().url(path).build().get().submit();
        if(wellRestedResponse.isValid()){
            return ResultBuilder.successful(wellRestedResponse.fromJson().castTo(tTypeToken));
        }
        return ResultBuilder.failed(wellRestedResponse.getServerResponse());
    }


    <T> Result<List<T>> getListRequest(String endpoint, TypeToken<List<T>> tTypeToken){
        String path = UrlAcolyte.addParams(getFullPath(endpoint), getParamMap());
        WellRestedResponse wellRestedResponse = WellRestedRequest.builder().url(path).build().get().submit();
        if(wellRestedResponse.isValid()){
            return ResultBuilder.successful(wellRestedResponse.fromJson().castToList(tTypeToken));
        }
        return ResultBuilder.failed(wellRestedResponse.getServerResponse());
    }

    <T> Result<GetAddressResponse> postRequest(String endpoint, T jsonContent){
        String path = UrlAcolyte.addParams(getFullPath(endpoint), getParamMap());
        WellRestedResponse wellRestedResponse = WellRestedRequest.builder().url(path).build().post().jsonContent(jsonContent).submit();
        return createResult(wellRestedResponse);
    }

    <T> Result<GetAddressResponse> deleteRequest(String endpoint , String id){
        String path = UrlAcolyte.addParams(getFullPath(endpoint,id), getParamMap());
        WellRestedResponse wellRestedResponse = WellRestedRequest.builder().url(path).build().delete().submit();
        return createResult(wellRestedResponse);
    }

    private String getFullPath(String... paths){
        return UrlAcolyte.safeConcat(getAddressConfigs.getRootUrl(), paths);
    }

    private Map<String,String> getParamMap(){
        Map<String,String> pathParams = new HashMap<>();
        pathParams.put("api-key", getAddressConfigs.getApiKey());
        return pathParams;
    }

    private Result<GetAddressResponse> createResult(WellRestedResponse wellRestedResponse){
        if(wellRestedResponse.isValid()){
            return ResultBuilder.successful(wellRestedResponse.fromJson().castTo(GetAddressResponse.class));
        }
        return ResultBuilder.failed().buildAndOverrideData(wellRestedResponse.fromJson().castTo(GetAddressResponse.class));
    }

}
