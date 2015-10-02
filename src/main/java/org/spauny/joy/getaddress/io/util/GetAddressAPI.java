package org.spauny.joy.getaddress.io.util;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author iulian
 */
public final class GetAddressAPI {

    private static final String GETADDRESS_IO_API_ROOT_PATH = "https://api.getAddress.io/v2/uk/";

    public final String API_ROOT_PATH;

    public String LOOKUP_POSTCODE;
    public String LOOKUP_POSTCODE_AND_HOUSE_NUMBER;

    /**
     *
     * Creates a getAddress.io client with the default API root path:
     * {@value #GETADDRESS_IO_API_ROOT_PATH}
     */
    public GetAddressAPI() {
        this.API_ROOT_PATH = GETADDRESS_IO_API_ROOT_PATH;
        initAPI();
    }

    /**
     * Creates a getAddress.io client with a custom getAddress.io API root path -
     * useful for when you host your own instance of getAddress.io or the default
     * API changes and the library hasn't been updated yet
     *
     * @param getAddressAPIRootpath the custom getAddress.io API root path
     * @throws IllegalArgumentException when the argument provided is blank -
     * please use the empty constructor if you want to use the default API path
     */
    public GetAddressAPI(String getAddressAPIRootpath) {
        if (StringUtils.isBlank(getAddressAPIRootpath)) {
            throw new IllegalArgumentException("You provided a blank getAddress.io API root path. If you want to use the default getAddress.io API root path then use the default constructor");
        }
        this.API_ROOT_PATH = getAddressAPIRootpath;
        initAPI();
    }
    
    private void initAPI() {
        LOOKUP_POSTCODE = GETADDRESS_IO_API_ROOT_PATH + "%s";
        LOOKUP_POSTCODE_AND_HOUSE_NUMBER = LOOKUP_POSTCODE + "/%s";
    }
}
