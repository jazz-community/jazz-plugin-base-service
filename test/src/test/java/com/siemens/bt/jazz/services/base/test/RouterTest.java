package com.siemens.bt.jazz.services.base.test;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.siemens.bt.jazz.services.base.test.mock.MockFactory;
import com.siemens.bt.jazz.services.base.test.mock.MockRequest;
import com.siemens.bt.jazz.services.base.test.mock.MockResponse;
import com.siemens.bt.jazz.services.base.test.mock.MockTeamService;
import com.siemens.bt.jazz.services.base.rest.DefaultRestService;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.router.Router;
import com.siemens.bt.jazz.services.base.router.factory.RestFactory;
import com.siemens.bt.jazz.services.base.router.tree.ConcurrentTreeRouter;
import org.junit.Before;
import org.junit.Test;

public class RouterTest {

    private Router router;

    @Test
    public void ServicePath_Exists() throws Exception {
        MockFactory mockFactory = new MockFactory();

        router.addService(HttpConstants.HttpMethod.GET, "test_path", mockFactory);
        MockTeamService mockTeamService = new MockTeamService();
        MockRequest mockRequest = new MockRequest();
        MockResponse mockResponse = new MockResponse();
        RestRequest restRequest = new RestRequest(HttpConstants.HttpMethod.GET, "test_path");

        RestActionBuilder builder = router.prepareAction(
                mockTeamService,
                null,
                mockRequest,
                mockResponse,
                restRequest);
    }

    @Before
    public void setUp() throws Exception {
        RestFactory restFactory = new RestFactory(DefaultRestService.class);
        this.router = new ConcurrentTreeRouter(restFactory);
    }
}