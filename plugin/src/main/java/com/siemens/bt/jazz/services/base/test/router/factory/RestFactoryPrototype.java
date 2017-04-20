package com.siemens.bt.jazz.services.base.test.router.factory;

import com.siemens.bt.jazz.services.base.test.rest.AbstractRestService;

public class RestFactoryPrototype {

    public RestFactory getInstance(Class<? extends AbstractRestService> serviceClass) {
        return new RestFactory(serviceClass);
    }
}
