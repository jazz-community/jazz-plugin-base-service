package com.siemens.bt.jazz.services.base.test;

import com.siemens.bt.jazz.services.base.rest.RestAction;
import com.siemens.bt.jazz.services.base.rest.RestActionBuilder;
import com.siemens.bt.jazz.services.base.test.helper.TestLogger;
import com.siemens.bt.jazz.services.base.test.helper.TestService;
import com.siemens.bt.jazz.services.base.test.mock.MockRequest;
import com.siemens.bt.jazz.services.base.test.mock.MockResponse;
import com.siemens.bt.jazz.services.base.test.mock.MockTeamService;
import org.junit.Before;
import org.junit.Test;

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
        RestAction service = testBuilder.create();

        assertEquals(TestService.class, service.getClass());
        assertTrue(RestAction.class.isAssignableFrom(service.getClass()));
    }

    @Before
    public void setUp() throws Exception {
        this.testBuilder = new RestActionBuilder(TestService.class);

        testBuilder.setLog(null);
        testBuilder.setParentService(null);

        // these could also be altered per test, when more tests are necessary.
        testBuilder.setRequest(null);
        testBuilder.setResponse(null);
        testBuilder.setRestRequest(null);
    }
}
