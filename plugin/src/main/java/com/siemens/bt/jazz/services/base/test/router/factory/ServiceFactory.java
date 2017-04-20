package com.siemens.bt.jazz.services.base.test.router.factory;

import com.siemens.bt.jazz.services.base.test.rest.RestActionBuilder;

public interface ServiceFactory {
    RestActionBuilder getBuilder();
}
