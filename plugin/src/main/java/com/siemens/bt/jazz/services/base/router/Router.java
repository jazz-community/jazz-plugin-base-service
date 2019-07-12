package com.siemens.bt.jazz.services.base.router;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.configuration.Configuration;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

/**
 * Handle REST URL routing to a service.
 *
 * <p>This interface supplies methods that enable a client to look up a REST service implementation
 * given a URI.
 */
public interface Router {
  /**
   * Adds a service with a method, a full URL path and a builder class.
   *
   * @param method A REST method ({@code GET/POST/}etc.)
   * @param factory Factory to use when building rest actions
   */
  void addService(HttpMethod method, ServiceFactory factory);
  void addService(HttpMethod method, ServiceFactory factory, Configuration configuration);

  void get(String path, Class<? extends AbstractRestService> service);
  void get(String path, Class<? extends AbstractRestService> service, Configuration configuration);

  void put(String path, Class<? extends AbstractRestService> service);
  void put(String path, Class<? extends AbstractRestService> service, Configuration configuration);

  void post(String path, Class<? extends AbstractRestService> service);
  void post(String path, Class<? extends AbstractRestService> service, Configuration configuration);

  void delete(String path, Class<? extends AbstractRestService> service);
  void delete(String path, Class<? extends AbstractRestService> service, Configuration configuration);

  void get(ServiceFactory factory);

  void put(ServiceFactory factory);

  void post(ServiceFactory factory);

  void delete(ServiceFactory factory);

  /**
   * Builds a rest action and returns it, complete for execution
   *
   * @param parentService The parent service of this service. Initially called in the chain
   * @param log Log object
   * @param request The original HTTP Request
   * @param response The response object for writing back responses to the client
   * @return A rest action that can be executed
   */
  RestActionBuilder prepareAction(
      String uri,
      TeamRawService parentService,
      Log log,
      HttpServletRequest request,
      HttpServletResponse response);
}
