package com.siemens.bt.jazz.services.base.test.helper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletInputStream;

public class TestInputStream extends ServletInputStream {
  private final ByteArrayInputStream byteStream;

  public TestInputStream(String content) {
    byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
    this.byteStream = new ByteArrayInputStream(bytes);
  }

  @Override
  public int read() throws IOException {
    return this.byteStream.read();
  }
}
