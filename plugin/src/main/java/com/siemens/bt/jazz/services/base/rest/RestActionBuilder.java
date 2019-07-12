package com.siemens.bt.jazz.services.base.rest;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.parameters.PathParameters;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

/**
 * Abstract class for implementing the builder pattern for RestActions.
 *
 * <p>Classes that extend this class have access to all the possible resources they might need.
 * However, only the required resources can be passed into internal classes to keep them sanitized.
 *
 * <p>All implementations return an instance of an inner private class with the intended
 * functionality. Classes extending RestActionBuilder are therefore only wrappers for creating
 * instances of actions. This is done for keeping the scope of all the builder variables as tight as
 * possible.
 */
public class RestActionBuilder {

  protected final Class<? extends AbstractRestService> serviceClass;
  protected final String path;
  protected String uri;
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected Log log;
  protected TeamRawService parentService;

  public RestActionBuilder(String path, Class<? extends AbstractRestService> serviceClass) {
    this.path = path;
    this.serviceClass = serviceClass;
  }

  /**
   * Sets the URI passed in from the calling servlet
   * @param uri String representation of the URI resolving this service
   * @return RestActionBuilder in construction
   */
  public RestActionBuilder setUri(String uri) {
    this.uri = uri;
    return this;
  }

  /**
   * Sets a logger implementation.
   *
   * @param log Logger implementation
   * @return RestActionBuilder in construction
   */
  public RestActionBuilder setLog(Log log) {
    this.log = log;
    return this;
  }

  /**
   * Sets a HttpServletRequest.
   *
   * @param request HttpServletRequest from an HTTP call
   * @return RestActionBuilder in construction
   */
  public RestActionBuilder setRequest(HttpServletRequest request) {
    this.request = request;
    return this;
  }

  /**
   * Set a HttpServletResponse.
   *
   * @param response HttpServletResponse to write service responses from a service.
   * @return RestActionBuilder in construction
   */
  public RestActionBuilder setResponse(HttpServletResponse response) {
    this.response = response;
    return this;
  }

  /**
   * Set a TeamRawService.
   *
   * <p>This is necessary for services that reflect upon the form of the service itself.
   *
   * @param parentService The service calling the request.
   * @return RestActionBuilder in construction
   */
  public RestActionBuilder setParentService(TeamRawService parentService) {
    this.parentService = parentService;
    return this;
  }

  /**
   * Create an instance of a RestAction.
   *
   * @return A new RestAction instance
   */
  public RestAction create()
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {
    Constructor<? extends AbstractRestService> constructor =
        serviceClass.getConstructor(
            String.class,
            Log.class,
            HttpServletRequest.class,
            HttpServletResponse.class,
            TeamRawService.class,
            PathParameters.class);

    return constructor.newInstance(
        uri,
        log,
        request,
        response,
        parentService,
        new PathParameters(path, uri));
  }
}
