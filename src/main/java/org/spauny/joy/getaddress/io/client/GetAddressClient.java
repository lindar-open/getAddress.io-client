package org.spauny.joy.getaddress.io.client;

import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.spauny.joy.getaddress.io.util.GetAddressAPI;
import org.spauny.joy.getaddress.io.vo.PostcodeVO;
import org.spauny.joy.getaddress.io.vo.Response;
import org.spauny.joy.wellrested.request.AbstractRequestProcessor;
import org.spauny.joy.wellrested.request.HttpRequestProcessor;
import org.spauny.joy.wellrested.vo.ResponseVO;

/**
 *
 * @author iulian
 */
public class GetAddressClient {

    public static final String START = "?";
    public static final String AND = "&";
    public static final String API_KEY_QUERY = "api-key=%s";

    private final GetAddressAPI API;
    private final String apiKey;

    /**
     * Creates a getAddress.io client with the default API root path:
     * https://api.getAddress.io/v2/uk/ . Please provide your valid API key. If
     * you don't have one you can register for a free account on:
     * https://getaddress.io/
     *
     * @param apiKey your valid getAddress.io API key.
     */
    public GetAddressClient(String apiKey) {
        this.API = new GetAddressAPI();
        this.apiKey = apiKey;
    }

    /**
     * Creates a getAddress.io client with a custom getAddress.io API root path
     * - useful for when you host your own instance of getAddress.io or the
     * default API changes and the library hasn't been updated yet. Please
     * provide your valid API key. If you don't have one you can register for a
     * free account on: https://getaddress.io/
     *
     * @param getAddressAPIRootpath the custom getAddress.io API root path
     * @param apiKey your valid getAddress.io API key.
     * @throws IllegalArgumentException when the argument provided is blank -
     * please use the empty constructor if you want to use the default API path
     */
    public GetAddressClient(String getAddressAPIRootpath, String apiKey) {
        if (StringUtils.isBlank(getAddressAPIRootpath)) {
            throw new IllegalArgumentException("You provided a blank getAddress API root path. If you want to use the default getAddress.io API root path then use the default constructor");
        }
        this.API = new GetAddressAPI(getAddressAPIRootpath);
        this.apiKey = apiKey;
    }

    /**
     * Lookup a postcode. Returns all available data if found. Returns 404 if
     * postcode does not exist.
     *
     * @param postcode
     * @return
     */
    public Response<PostcodeVO> lookupPostcode(String postcode) {
        return processGetRequestAndReturnResponse(String.format(API.LOOKUP_POSTCODE + START + API_KEY_QUERY, postcode, this.apiKey));
    }

    /**
     * Lookup a postcode and house number. Returns all available data if found.
     * Returns 404 if postcode does not exist.
     *
     * @param postcode
     * @param houseNumber
     * @return
     */
    public Response<PostcodeVO> lookupPostcodeAndHouseNumber(String postcode, int houseNumber) {
        return processGetRequestAndReturnResponse(String.format(API.LOOKUP_POSTCODE_AND_HOUSE_NUMBER + START + API_KEY_QUERY, postcode, houseNumber, apiKey));
    }
    
    public Response<List<PostcodeVO>> bulkLookupPostcodes(List<String> postcodes) {
        return processGetRequestAndReturnResponse(String.format(API.API_ROOT_PATH 
                + StringUtils.join(postcodes, ".") + START + API_KEY_QUERY, apiKey));
    }

    private <T> Response<T> processGetRequestAndReturnResponse(String url) {
        AbstractRequestProcessor requestProcessor = new HttpRequestProcessor(url);

        ResponseVO serverResponse = requestProcessor.processGetRequest();

        return getResponseFromServerResponse(serverResponse);
    }

    private <T> Response<T> getResponseFromServerResponse(ResponseVO serverResponse) {
        Response<T> response = new Response<>();
        if (StringUtils.isNotBlank(serverResponse.getServerResponse())) {
            Gson gson = new Gson();
            T postcodeVO = gson.fromJson(serverResponse.getServerResponse(), new TypeToken<PostcodeVO>() {
            }.getType());
            response.setData(postcodeVO);
        }
        response.setStatus(serverResponse.getStatusCode());
        return response;
    }

    public static void main(String[] args) {
        GetAddressClient postcodesClient = new GetAddressClient("FIZi3Po-hUWhVbwUU7RRqg1715");
        System.out.println(postcodesClient.lookupPostcode("SE61TZ"));
        System.out.println(postcodesClient.lookupPostcode("SE61T"));
        System.out.println(postcodesClient.lookupPostcodeAndHouseNumber("SE61TZ", 47));
        System.out.println("***********************");
        System.out.println(postcodesClient.bulkLookupPostcodes(Lists.newArrayList("SE61TZ", "E153BH")));
    }
}
