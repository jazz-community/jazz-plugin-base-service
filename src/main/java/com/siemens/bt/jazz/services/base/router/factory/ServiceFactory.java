package com.siemens.bt.jazz.services.base.router.factory;

import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;

public interface ServiceFactory {
    RestActionBuilder getBuilder();
}
