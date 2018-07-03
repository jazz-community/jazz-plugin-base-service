package com.siemens.bt.jazz.services.base.test;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.siemens.bt.jazz.services.base.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.RestAction;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import com.siemens.bt.jazz.services.base.test.helper.MockRequestFactory;
import com.siemens.bt.jazz.services.base.test.helper.TestLogger;
import com.siemens.bt.jazz.services.base.test.helper.TestService;
import com.siemens.bt.jazz.services.base.test.mock.MockRequest;
import com.siemens.bt.jazz.services.base.test.mock.MockResponse;
import com.siemens.bt.jazz.services.base.test.mock.MockTeamService;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * This class should test for correct instantiation by the RestActionBuilder. The point of these tests should be to
 * verify that the reflection capabilities used function the way they should when using different subclasses of
 * an abstract rest service to produce rest actions.
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
        RestRequest restRequest = new RestRequest(HttpConstants.HttpMethod.GET, "doesn't matter");

        testBuilder.setLog(log)
                .setParentService(mockParent)
                .setRequest(mockRequest)
                .setResponse(mockResponse)
                .setRestRequest(restRequest);

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

        Field restField = superClass.getDeclaredField("restRequest");
        restField.setAccessible(true);
        assertSame(restRequest, restField.get(service));
    }

    @Before
    public void setUp() throws Exception {
        this.testBuilder = new RestActionBuilder("/", TestService.class);
    }
}
