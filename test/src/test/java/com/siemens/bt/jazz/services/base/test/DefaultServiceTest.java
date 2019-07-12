package com.siemens.bt.jazz.services.base.test;

import static org.junit.Assert.assertEquals;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.siemens.bt.jazz.services.base.rest.parameters.PathParameters;
import com.siemens.bt.jazz.services.base.rest.service.DefaultRestService;
import com.siemens.bt.jazz.services.base.test.helper.MockRequestFactory;
import com.siemens.bt.jazz.services.base.test.helper.TestLogger;
import com.siemens.bt.jazz.services.base.test.mock.MockResponse;
import com.siemens.bt.jazz.services.base.test.mock.MockTeamService;
import org.junit.Test;

/**
 * Most importantly, this Test should demonstrate how a finished service can be tested by correctly
 * using the Request/Response and TeamService mocks. With proper injection, services can be tested
 * without actually running networked requests against them.
 *
 * <p>MockRequest can be used to inject requested data into services for tests. MockResponse can be
 * used to inspect if services produced responses that they are expected to produce.
 */
public class DefaultServiceTest {
  private static final String SERVICE_TEMPLATE =
      "The requested service \"%s\" doesn't exist for method \"%s\".";

  @Test
  public void Runs_CorrectResponse() throws Exception {
    MockResponse mockResponse = new MockResponse();

    DefaultRestService service =
        new DefaultRestService(
            "get_default_service",
            new TestLogger(),
            MockRequestFactory.httpGetRequest(),
            mockResponse,
            new MockTeamService(),
            new PathParameters("", ""));

    service.execute();

    String responseMessage = mockResponse.getMockWriter().toString();
    assertEquals(String.format(SERVICE_TEMPLATE, "get_default_service", "GET"), responseMessage);
    assertEquals(501, mockResponse.getStatus());
  }
}
