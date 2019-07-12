package com.siemens.bt.jazz.services.base.configuration;

import com.ibm.team.repository.service.TeamRawService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

public interface ServiceConfigurator {
  void configure(
      Log log,
      HttpServletRequest request,
      HttpServletResponse response,
      TeamRawService parentService);
}
