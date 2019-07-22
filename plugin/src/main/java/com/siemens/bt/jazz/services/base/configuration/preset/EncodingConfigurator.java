package com.siemens.bt.jazz.services.base.configuration.preset;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.configuration.ServiceConfigurator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

public class EncodingConfigurator implements ServiceConfigurator {

  private final String encoding;

  public EncodingConfigurator(String encoding) {
    this.encoding = encoding;
  }

  @Override
  public void configure(
      Log log,
      HttpServletRequest request,
      HttpServletResponse response,
      TeamRawService parentService) {
    response.setCharacterEncoding(encoding);
  }
}
