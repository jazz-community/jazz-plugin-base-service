package com.siemens.bt.jazz.services.base.router.map;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import com.siemens.bt.jazz.services.base.router.Router;
import com.siemens.bt.jazz.services.base.router.factory.RestFactory;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

public class MapRouter implements Router {
  private final ServiceMap services = new ServiceMap();

  @Override
  public void addService(HttpConstants.HttpMethod method, ServiceFactory factory) {
    services.add(method, factory.getPath() + ".*", factory);
  }

  @Override
  public void get(String path, Class<? extends AbstractRestService> service) {
    get(new RestFactory(path, service));
  }

  @Override
  public void put(String path, Class<? extends AbstractRestService> service) {
    put(new RestFactory(path, service));
  }

  @Override
  public void post(String path, Class<? extends AbstractRestService> service) {
    post(new RestFactory(path, service));
  }

  @Override
  public void delete(String path, Class<? extends AbstractRestService> service) {
    delete(new RestFactory(path, service));
  }

  @Override
  public void get(ServiceFactory factory) {
    addService(HttpConstants.HttpMethod.GET, factory);
  }

  @Override
  public void put(ServiceFactory factory) {
    addService(HttpConstants.HttpMethod.PUT, factory);
  }

  @Override
  public void post(ServiceFactory factory) {
    addService(HttpConstants.HttpMethod.POST, factory);
  }

  @Override
  public void delete(ServiceFactory factory) {
    addService(HttpConstants.HttpMethod.DELETE, factory);
  }

  @Override
  public RestActionBuilder prepareAction(
      String uri,
      TeamRawService parentService,
      Log log,
      HttpServletRequest request,
      HttpServletResponse response) {
    return services
        .getFactory(request, uri)
        .getBuilder()
        .setUri(uri)
        .setParentService(parentService)
        .setLog(log)
        .setRequest(request)
        .setResponse(response)
        .setConfiguration(configuration);
  }
}
