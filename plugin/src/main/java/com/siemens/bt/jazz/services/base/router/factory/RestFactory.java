package com.siemens.bt.jazz.services.base.router.factory;

import com.siemens.bt.jazz.services.base.configuration.Configuration;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;

public class RestFactory implements ServiceFactory {
  protected final Class<? extends AbstractRestService> serviceClass;
  protected final String path;
  protected final Configuration configuration;

  public RestFactory(
      String path, Class<? extends AbstractRestService> serviceClass, Configuration configuration) {
    this.serviceClass = serviceClass;
    this.path = path;
    this.configuration = configuration;
  }

  public RestFactory(String path, Class<? extends AbstractRestService> serviceClass) {
    this(path, serviceClass, new Configuration());
  }

  @Override
  public RestActionBuilder getBuilder() {
    return new RestActionBuilder(path, serviceClass, configuration);
  }

  @Override
  public String getPath() {
    return path;
  }
}
