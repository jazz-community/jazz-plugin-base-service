package com.siemens.bt.jazz.services.base.rest.service;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.RestAction;
import com.siemens.bt.jazz.services.base.rest.parameters.PathParameters;
import com.siemens.bt.jazz.services.base.rest.parameters.RestRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

/** Abstract class that represents a REST service. */
public abstract class AbstractRestService implements RestAction {
  protected Log log;
  protected HttpServletRequest request;
  protected HttpServletResponse response;
  protected RestRequest restRequest;
  protected TeamRawService parentService;
  protected PathParameters pathParameters;

  public AbstractRestService(
      Log log,
      HttpServletRequest request,
      HttpServletResponse response,
      RestRequest restRequest,
      TeamRawService parentService,
      PathParameters pathParameters) {
    this.log = log;
    this.request = request;
    this.response = response;
    this.restRequest = restRequest;
    this.parentService = parentService;
    this.pathParameters = pathParameters;
  }
}
