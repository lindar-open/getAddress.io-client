package com.lindar.getaddress.io.client;

import com.lindar.getaddress.io.client.api.DomainResource;
import com.lindar.getaddress.io.client.api.IPAddressResource;
import com.lindar.getaddress.io.client.api.PrivateAddressResource;
import com.lindar.getaddress.io.client.api.UsageResource;
import com.lindar.getaddress.io.client.util.GetAddressConfigs;

public class GetAddressAdminClient {

    private DomainResource    domainResource;
    private UsageResource     usageResource;
    private IPAddressResource ipAddressResource;
    private PrivateAddressResource privateAddressResource;

    public GetAddressAdminClient(GetAddressConfigs getAddressConfigs){
        domainResource = new DomainResource(getAddressConfigs);
        usageResource = new UsageResource(getAddressConfigs);
        ipAddressResource = new IPAddressResource(getAddressConfigs);
        privateAddressResource = new PrivateAddressResource(getAddressConfigs);
    }

    public DomainResource domain(){
        return domainResource;
    }

    public UsageResource usage(){
        return usageResource;
    }

    public IPAddressResource ipAddress(){
        return ipAddressResource;
    }

    public PrivateAddressResource privateAddress(){
        return privateAddressResource;
    }

}
