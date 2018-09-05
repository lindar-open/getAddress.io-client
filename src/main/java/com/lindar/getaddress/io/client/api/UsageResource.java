package com.lindar.getaddress.io.client.api;

import com.google.gson.reflect.TypeToken;
import com.lindar.getaddress.io.client.util.GetAddressConfigs;
import com.lindar.getaddress.io.client.vo.UsageVO;
import com.lindar.wellrested.vo.Result;
import lindar.acolyte.util.UrlAcolyte;
import org.joda.time.DateTime;

import java.util.Date;

public class UsageResource extends AbstractResource {

    private static final String USAGE_ENDPOINT = "/v2/usage";

    public UsageResource(GetAddressConfigs getAddressConfigs) {
        super(getAddressConfigs);
    }

    public Result<UsageVO> findTodays(){
        return getRequest(USAGE_ENDPOINT, new TypeToken<UsageVO>(){});
    }

    public Result<UsageVO> findByDate(Date date){
        DateTime dateTime = new DateTime(date);
        String day = String.valueOf(dateTime.getDayOfMonth());
        String month = String.valueOf(dateTime.getMonthOfYear());
        String year = String.valueOf(dateTime.getYear());
        String url = UrlAcolyte.safeConcat(USAGE_ENDPOINT, day,month,year);
        return getRequest(url, new TypeToken<UsageVO>(){});
    }


}
