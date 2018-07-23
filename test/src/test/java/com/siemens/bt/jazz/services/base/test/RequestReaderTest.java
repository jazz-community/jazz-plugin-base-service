package com.siemens.bt.jazz.services.base.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.siemens.bt.jazz.services.base.test.helper.MockRequestFactory;
import com.siemens.bt.jazz.services.base.test.mock.MockRequest;
import com.siemens.bt.jazz.services.base.utils.RequestReader;
import org.junit.Test;

public class RequestReaderTest {
  @Test
  public void ReadAsString_RegularString() throws Exception {
    MockRequest mockRequest = MockRequestFactory.httpPostRequest("this is some content");
    String result = RequestReader.readAsString(mockRequest);
    assertEquals("this is some content", result);
  }

  @Test
  public void ReadAsJson_JsonString() throws Exception {
    String content = "{\"member\":\"value\"}";
    MockRequest mockRequest = MockRequestFactory.httpPostRequest(content);
    JsonObject jsonObject = RequestReader.readAsJson(mockRequest);

    JsonElement member = jsonObject.get("member");
    assertNotNull(member);

    String value = member.getAsString();
    assertEquals("value", value);
  }

  @Test
  public void ReadAsArray_JsonArray() throws Exception {
    String content = "[\"this\", \"is\", \"some\", \"content\"]";
    MockRequest mockRequest = MockRequestFactory.httpPostRequest(content);
    JsonArray array = RequestReader.readAsArray(mockRequest);

    assertEquals("this", array.get(0).getAsString());
    assertEquals("is", array.get(1).getAsString());
    assertEquals("some", array.get(2).getAsString());
    assertEquals("content", array.get(3).getAsString());
  }
}
