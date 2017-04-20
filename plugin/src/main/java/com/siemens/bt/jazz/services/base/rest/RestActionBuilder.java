package com.siemens.bt.jazz.services.base.rest;

import com.ibm.team.repository.service.TeamRawService;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Abstract class for implementing the builder pattern for RestActions.
 * <p>
 * <p>Classes that extend this class have access to all the possible resources they might need. However, only the
 * required resources can be passed into internal classes to keep them sanitized.</p>
 * <p>
 * <p>All implementations return an instance of an inner private class with the intended functionality. Classes extending
 * RestActionBuilder are therefore only wrappers for creating instances of actions. This is done for keeping the scope
 * of all the builder variables as tight as possible.</p>
 */
public class RestActionBuilder {

    protected final Class<? extends AbstractRestService> serviceClass;

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected Log log;
    protected RestRequest restRequest;
    protected TeamRawService parentService;

    public RestActionBuilder(Class<? extends AbstractRestService> serviceClass) {
        this.serviceClass = serviceClass;
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
     * Set a RestRequest.
     *
     * @param restRequest Summary of REST call information
     * @return RestActionBuilder in construction
     */
    public RestActionBuilder setRestRequest(RestRequest restRequest) {
        this.restRequest = restRequest;
        return this;
    }

    /**
     * Set a TeamRawService.
     * <p>
     * <p>This is necessary for services that reflect upon the form of the service itself.</p>
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
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Constructor<? extends AbstractRestService> constructor =
                serviceClass.getConstructor(Log.class,
                        HttpServletRequest.class,
                        HttpServletResponse.class,
                        RestRequest.class,
                        TeamRawService.class);

        return constructor.newInstance(log, request, response, restRequest, parentService);

    }
}
