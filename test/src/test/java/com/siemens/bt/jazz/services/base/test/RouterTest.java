package com.siemens.bt.jazz.services.base.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.siemens.bt.jazz.services.base.rest.RestAction;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.service.DefaultRestService;
import com.siemens.bt.jazz.services.base.router.Router;
import com.siemens.bt.jazz.services.base.router.map.MapRouter;
import com.siemens.bt.jazz.services.base.test.helper.MockRequestFactory;
import com.siemens.bt.jazz.services.base.test.helper.TestLogger;
import com.siemens.bt.jazz.services.base.test.helper.TestService;
import com.siemens.bt.jazz.services.base.test.mock.MockFactory;
import com.siemens.bt.jazz.services.base.test.mock.MockResponse;
import com.siemens.bt.jazz.services.base.test.mock.MockTeamService;
import org.junit.Before;
import org.junit.Test;

/**
 * This implicitly tests the functionality of ActionTree. ActionNode is only a container class and
 * need not be tested more deeply. ActionTree however should be tested by this class because all
 * services functionality depends on it working properly.
 */
public class RouterTest {

  private Router router;

  @Test
  public void ServicePath_Root() throws Exception {
    router.addService(HttpConstants.HttpMethod.GET, new MockFactory());

    MockResponse response = new MockResponse();

    RestActionBuilder builder =
        router.prepareAction(
            "/",
            new MockTeamService(),
            new TestLogger(),
            MockRequestFactory.httpGetRequest(),
            response);

    RestAction action = builder.create();
    assertEquals(TestService.class, action.getClass());
  }

  @Test
  public void ServicePath_Exists_WithParameters() throws Exception {
    // this test will need to check that paths with parameters are passed correctly.
  }

  @Test
  public void ServicePath_NotExists() throws Exception {
    MockResponse response = new MockResponse();

    RestActionBuilder builder =
        router.prepareAction(
            "no_service_here",
            new MockTeamService(),
            new TestLogger(),
            MockRequestFactory.httpGetRequest(),
            response);

    RestAction action = builder.create();

    // Because no service has been added at the requested endpoint, the default service will just be
    // returned.
    assertSame(DefaultRestService.class, action.getClass());
    // Action executed here will have no side-effects, so we can just let it run.
    action.execute();
  }

  @Test
  public void ServicePath_Exists() throws Exception {
    MockFactory mockFactory = new MockFactory("test/service/path");

    router.addService(HttpConstants.HttpMethod.GET, mockFactory);

    RestActionBuilder builder =
        router.prepareAction(
            "test/service/path",
            new MockTeamService(),
            new TestLogger(),
            MockRequestFactory.httpGetRequest(),
            new MockResponse());

    RestAction action = builder.create();

    assertSame(TestService.class, action.getClass());
    // action should execute without exceptions because it doesn't interact with anything anyway.
    // The mock service
    // does nothing.
    action.execute();
  }

  @Before
  public void setUp() throws Exception {
    this.router = new MapRouter();
  }
}
