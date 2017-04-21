package com.siemens.bt.jazz.services.base.test.helper;

import com.siemens.bt.jazz.services.base.test.mock.MockRequest;

/**
 * Will be used to create instances of MockRequest that can be used by tests. The important point here is to set
 * the required properties so that services can access request data.
 */
public class MockRequestFactory {
    public static MockRequest httpGetRequest() {
        return new MockRequest("GET");
    }

    public static MockRequest httpPostRequest() {
        return new MockRequest("POST");
    }

    public static MockRequest httpPostRequest(String body) {
        return new MockRequest("POST", body);
    }
}
