package com.siemens.bt.jazz.services.base.rest.service;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.configuration.Configuration;
import com.siemens.bt.jazz.services.base.rest.parameters.PathParameters;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.Log;

/*
 * Action builder for a default action.
 * <p>
 * <p>This builder can be used for nodes and methods that don't have a specific action assigned to them. This is usually
 * the case when a node is used as a dummy / navigation node.</p>
 *
 * @see DefaultRestService
 */

/**
 * Default rest service. This service is used for nodes that don't have a REST action associated.
 */
public final class DefaultRestService extends AbstractRestService {

  public DefaultRestService(String uri, Log log, HttpServletRequest request,
      HttpServletResponse response,
      Configuration configuration,
      TeamRawService parentService,
      PathParameters pathParameters) {
    super(uri, log, request, response, configuration, parentService, pathParameters);
  }

  /**
   * {@inheritDoc}.
   *
   * <p>Writes a message informing the user that this is an unimplemented service.
   *
   * <p>Returns 501 Not Implemented
   */
  public void execute() throws IOException, URISyntaxException {
    response.setStatus(HttpServletResponse.SC_NOT_IMPLEMENTED);
    response.setContentType("text");
    Writer writer = response.getWriter();
    String answer =
        String.format(
            "The requested service \"%s\" doesn't exist for method \"%s\".",
            uri, request.getMethod());

    writer.write(answer);
  }
}
