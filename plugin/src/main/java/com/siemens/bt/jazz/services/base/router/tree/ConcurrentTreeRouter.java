package com.siemens.bt.jazz.services.base.router.tree;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.router.Router;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * REST request router implementation, internally using a tree data structure.
 * <p>
 * <p>For a given rest path and method, this class retrieves the correct action builder and builds the corresponding
 * action. This especially facilitates passing the correct parameters to all services, as it acts as a single entry
 * point to all constructed services.</p>
 */
public class ConcurrentTreeRouter implements Router {
    private final ActionTree tree;

    public ConcurrentTreeRouter(ServiceFactory defaultFactory) {
        tree = new ActionTree(defaultFactory);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>Uses an ActionTree to determine which service needs to be built.</p>
     */
    public RestActionBuilder prepareAction(
            TeamRawService parentService,
            Log log,
            HttpServletRequest request,
            HttpServletResponse response,
            RestRequest restRequest) {

        this.formatResponseDefault(response);

        return tree.getFactory(restRequest).getBuilder()
                .setParentService(parentService)
                .setLog(log)
                .setRequest(request)
                .setResponse(response)
                .setRestRequest(restRequest);
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>If a path is added twice, it will be overridden.</p>
     */
    public void addService(HttpMethod method, String path, ServiceFactory factory) {
        tree.addElement(method, path, factory);
    }

    /**
     * Set standard response headers.
     * <p>
     * <p>This shouldn't really be here, as it doesn't functionally belong to this class. There should be a much nicer
     * way to expose this functionality "globally"</p>
     *
     * @param response HttpResponse to be formatted
     */
    private void formatResponseDefault(HttpServletResponse response) {
        response.setCharacterEncoding(HttpConstants.DEFAULT_ENCODING);
        response.setContentType(HttpConstants.CT_APPLICATION_JSON);
    }

}
