package com.siemens.bt.jazz.services.base.test;

import static org.junit.Assert.*;

import com.siemens.bt.jazz.services.base.configuration.Configuration;
import com.siemens.bt.jazz.services.base.rest.RestAction;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.service.AbstractRestService;
import com.siemens.bt.jazz.services.base.test.helper.MockRequestFactory;
import com.siemens.bt.jazz.services.base.test.helper.TestLogger;
import com.siemens.bt.jazz.services.base.test.helper.TestService;
import com.siemens.bt.jazz.services.base.test.mock.MockRequest;
import com.siemens.bt.jazz.services.base.test.mock.MockResponse;
import com.siemens.bt.jazz.services.base.test.mock.MockTeamService;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;

/**
 * This class should test for correct instantiation by the RestActionBuilder. The point of these
 * tests should be to verify that the reflection capabilities used function the way they should when
 * using different subclasses of an abstract rest service to produce rest actions.
 */
public class RestActionBuilderTest {
  private RestActionBuilder testBuilder;

  @Test
  public void Constructor_Creates() throws Exception {
    // need references to all these objects for checking injection later
    TestLogger log = new TestLogger();
    MockTeamService mockParent = new MockTeamService();
    MockRequest mockRequest = MockRequestFactory.httpGetRequest();
    MockResponse mockResponse = new MockResponse();
    String uri = "doesn't matter";

    testBuilder
        .setUri(uri)
        .setLog(log)
        .setParentService(mockParent)
        .setRequest(mockRequest)
        .setResponse(mockResponse)
        .setConfiguration(new Configuration());

    RestAction service = testBuilder.create();

    assertEquals(TestService.class, service.getClass());
    assertTrue(RestAction.class.isAssignableFrom(service.getClass()));
    assertTrue(AbstractRestService.class.isAssignableFrom(service.getClass()));

    // check that all fields are not null
    Class<?> superClass = service.getClass().getSuperclass();
    Field[] fields = superClass.getDeclaredFields();

    for (Field field : fields) {
      field.setAccessible(true);
      Object object = field.get(service);
      assertNotNull(object);
    }

    // check for correct injection of AbstractRestService fields
    Field logField = superClass.getDeclaredField("log");
    logField.setAccessible(true);
    assertSame(log, logField.get(service));

    Field parentField = superClass.getDeclaredField("parentService");
    parentField.setAccessible(true);
    assertSame(mockParent, parentField.get(service));

    Field requestField = superClass.getDeclaredField("request");
    requestField.setAccessible(true);
    assertSame(mockRequest, requestField.get(service));

    Field responseField = superClass.getDeclaredField("response");
    responseField.setAccessible(true);
    assertSame(mockResponse, responseField.get(service));

    Field uriField = superClass.getDeclaredField("uri");
    uriField.setAccessible(true);
    assertSame(uri, uriField.get(service));
  }

  @Before
  public void setUp() throws Exception {
    this.testBuilder = new RestActionBuilder("/", TestService.class, new Configuration());
  }
}
