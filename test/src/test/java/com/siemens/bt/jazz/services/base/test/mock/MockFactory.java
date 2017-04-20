package com.siemens.bt.jazz.services.base.test.mock;

import com.siemens.bt.jazz.services.base.test.helper.TestService;
import com.siemens.bt.jazz.services.base.test.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.test.router.factory.ServiceFactory;

public class MockFactory implements ServiceFactory {
    @Override
    public RestActionBuilder getBuilder() {
        return new MockBuilder(TestService.class);
    }
}
