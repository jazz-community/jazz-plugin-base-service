package com.siemens.bt.jazz.services.base.router;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MapRouter implements Router {

    @Override
    public void addService(HttpConstants.HttpMethod method, String path, ServiceFactory factory) {

    }

    @Override
    public void get(String path, Class<? extends AbstractRestService> service) {

    }

    @Override
    public void put(String path, Class<? extends AbstractRestService> service) {

    }

    @Override
    public void post(String path, Class<? extends AbstractRestService> service) {

    }

    @Override
    public void delete(String path, Class<? extends AbstractRestService> service) {

    }

    @Override
    public void get(ServiceFactory factory) {

    }

    @Override
    public void put(ServiceFactory factory) {

    }

    @Override
    public void post(ServiceFactory factory) {

    }

    @Override
    public void delete(ServiceFactory factory) {

    }

    @Override
    public RestActionBuilder prepareAction(
            TeamRawService parentService,
            Log log,
            HttpServletRequest request,
            HttpServletResponse response,
            RestRequest restRequest) {

    }
}
