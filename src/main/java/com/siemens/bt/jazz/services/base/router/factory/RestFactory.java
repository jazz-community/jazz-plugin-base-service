package com.siemens.bt.jazz.services.base.router.factory;

import com.siemens.bt.jazz.services.base.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;

public class RestFactory implements ServiceFactory {
    protected final Class<? extends AbstractRestService> serviceClass;

    public RestFactory(Class<? extends AbstractRestService> serviceClass) {
        this.serviceClass = serviceClass;
    }

    @Override
    public RestActionBuilder getBuilder() {
        return new RestActionBuilder(serviceClass);
    }
}
