package com.siemens.bt.jazz.services.base.test.mock;

import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import com.siemens.bt.jazz.services.base.test.helper.TestService;

public class MockFactory implements ServiceFactory {
  private final String path;

  public MockFactory() {
    this.path = "/";
  }

  public MockFactory(String path) {
    this.path = path;
  }

  @Override
  public RestActionBuilder getBuilder() {
    return new RestActionBuilder(path, TestService.class);
  }

  @Override
  public String getPath() {
    return path;
  }
}
