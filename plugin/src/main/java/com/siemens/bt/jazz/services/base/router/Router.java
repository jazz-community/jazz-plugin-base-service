package com.siemens.bt.jazz.services.base.router;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Handle REST URL routing to a service.
 * <p>
 * <p>This interface supplies methods that enable a client to look up a REST service implementation given a URI.</p>
 */
public interface Router {
    /**
     * Adds a service with a method, a full URL path and a builder class.
     *
     * @param method  A REST method ({@code GET/POST/}etc.)
     * @param factory Factory to use when building rest actions
     */
    void addService(HttpMethod method, ServiceFactory factory);

    void get(String path, Class<? extends AbstractRestService> service);

    void put(String path, Class<? extends AbstractRestService> service);

    void post(String path, Class<? extends AbstractRestService> service);

    void delete(String path, Class<? extends AbstractRestService> service);

    void get(ServiceFactory factory);

    void put(ServiceFactory factory);

    void post(ServiceFactory factory);

    void delete(ServiceFactory factory);

    /**
     * Builds a rest action and returns it, complete for execution.
     *
     * @param request     The original HTTP Request
     * @param response    The response object for writing back responses to the client
     * @param restRequest A summary of the HTTP request, mainly used for navigation
     * @return A rest action that can be executed
     */
    RestActionBuilder prepareAction(
            TeamRawService parentService,
            Log log,
            HttpServletRequest request,
            HttpServletResponse response,
            RestRequest restRequest);
}
