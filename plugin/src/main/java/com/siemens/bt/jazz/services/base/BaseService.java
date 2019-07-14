package com.siemens.bt.jazz.services.base;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.RestAction;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.router.Router;
import com.siemens.bt.jazz.services.base.router.map.MapRouter;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Entry point for the ExampleService, called by the Jazz class loader.
 *
 * <p>This class must be implemented for enabling plugins to run inside Jazz. The implemented
 * interface corresponds to the component in {@code plugin.xml}, and this service is therefore the
 * provided service by the interface.
 */
public abstract class BaseService extends TeamRawService {

  protected final Router router = new MapRouter();

  /**
   * <p>This constructor is only called by the Jazz class loader.
   */
  public BaseService() {
    super();
  }

  /** Called when a HTTP GET request is sent. */
  @Override
  public void perform_GET(String uri, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    performAction(uri, request, response);
  }

  /** Called when a HTTP POST request is sent. */
  @Override
  public void perform_POST(String uri, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    performAction(uri, request, response);
  }

  /** Called when a HTTP PUT request is sent. */
  @Override
  public void perform_PUT(String uri, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    performAction(uri, request, response);
  }

  /** Called when a HTTP PUT request is sent. */
  @Override
  public void perform_DELETE(String uri, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    performAction(uri, request, response);
  }

  /**
   * Calls the requested method and handles errors.
   *
   * <p>With the current architecture, it's easier to have dummy public methods and handle them all
   * with this single function. Error handling in jazz is a bit special, and to guarantee that the
   * service is functionally sound, all errors except {@code 501} and {@code IOExceptions} should be
   * handled internally. If not handled and logged, a generic {@code 500} exception is returned with
   * little help for debugging.
   *
   * @param uri Relative URI to which the request was sent
   * @param request ServletRequest object that contains all request information
   * @param response A wrapped IO Handler for responses
   * @throws IOException Only exception thrown back to Jazz, corresponding with the contract from
   *     the inherited method.
   */
  protected void performAction(String uri, HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    try {
      RestActionBuilder builder = prepareRequest(uri, request, response);
      RestAction action = builder.create();
      action.prepare();
      action.execute();
      action.cleanUp();
    } catch (IOException e) {
      throw e;
    } catch (Exception e) {
      // catch everything and log. Makes sure that there is no checked exception from our service
      // back to jazz, except for the expected IOException when the response isn't writable. We need
      // to make sure that our plug-in conforms to the contract that no exceptions bubble out into
      // the system.
      super.getLog().error(e);
      this.http500return(request, response, e);
    }
  }

  protected final RestActionBuilder prepareRequest(
      String uri, HttpServletRequest request, HttpServletResponse response) {
    HttpMethod method = HttpMethod.fromString(request.getMethod());
    return router.prepareAction(uri, this, this.getLog(), request, response);
  }
}
