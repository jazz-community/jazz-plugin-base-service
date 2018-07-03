package com.siemens.bt.jazz.services.base.router.factory;

import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;

public class RestFactory implements ServiceFactory {
    protected final Class<? extends AbstractRestService> serviceClass;
    protected final String path;

    public RestFactory(String path, Class<? extends AbstractRestService> serviceClass) {
        this.serviceClass = serviceClass;
        this.path = path;
    }

    @Override
    public RestActionBuilder getBuilder() {
        return new RestActionBuilder(path, serviceClass);
    }

    @Override
    public String getPath() {
        return path;
    }
}
