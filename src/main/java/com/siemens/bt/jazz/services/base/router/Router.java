package com.siemens.bt.jazz.services.base.router;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;

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
     * @param path    A full path in standard url form {@code /like/this/please}
     * @param factory Factory to use when building rest actions
     */
    void addService(HttpMethod method, String path, ServiceFactory factory);

    /**
     * Builds a rest action and returns it, complete for execution.
     *
     * @param request     The original HTTP Request
     * @param response    The response object for writing back responses to the client
     * @param restRequest A summary of the HTTP request, mainly used for navigation
     * @return A rest action that can be executed
     * @throws InstantiationException If there is an error building the class
     * @throws IllegalAccessException If a class with wrong visibility is trying to be created
     */
    RestActionBuilder prepareAction(
            TeamRawService parentService,
            HttpServletRequest request,
            HttpServletResponse response,
            RestRequest restRequest);
}
