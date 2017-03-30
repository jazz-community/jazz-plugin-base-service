package com.siemens.bt.jazz.services.base.router.factory;

import com.siemens.bt.jazz.services.base.rest.AbstractRestService;

public class RestFactoryPrototype {

    public RestFactory getInstance(Class<? extends AbstractRestService> serviceClass) {
        return new RestFactory(serviceClass);
    }
}
