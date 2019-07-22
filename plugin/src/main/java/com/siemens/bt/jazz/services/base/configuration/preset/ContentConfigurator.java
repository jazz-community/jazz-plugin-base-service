package com.siemens.bt.jazz.services.base.configuration.preset;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.configuration.ServiceConfigurator;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

public class ContentConfigurator implements ServiceConfigurator {

  private final String contentType;

  public ContentConfigurator(String contentType) {
    this.contentType = contentType;
  }

  @Override
  public void configure(
      Log log,
      HttpServletRequest request,
      HttpServletResponse response,
      TeamRawService parentService) {
    response.setContentType(contentType);
  }
}
