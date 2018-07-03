package com.siemens.bt.jazz.services.base.router;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.router.factory.RestFactory;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MapRouter implements Router {
    private final ServiceMap services = new ServiceMap();

    @Override
    public void addService(
            HttpConstants.HttpMethod method,
            ServiceFactory factory) {
        services.add(method, factory.getPath(), factory);
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
            TeamRawService parentService,
            Log log,
            HttpServletRequest request,
            HttpServletResponse response,
            RestRequest restRequest) {
        return services.getFactory(request, restRequest.toString())
                .getBuilder()
                .setParentService(parentService)
                .setLog(log)
                .setRequest(request)
                .setResponse(response)
                .setRestRequest(restRequest);
    }
}
