package com.siemens.bt.jazz.services.base.test.mock;

import com.siemens.bt.jazz.services.base.test.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.test.rest.RestActionBuilder;

public class MockBuilder extends RestActionBuilder{
    public MockBuilder(Class<? extends AbstractRestService> serviceClass) {
        super(serviceClass);
    }
}
