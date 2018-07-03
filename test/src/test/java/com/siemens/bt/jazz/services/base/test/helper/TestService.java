package com.siemens.bt.jazz.services.base.test.helper;

import com.ibm.team.repository.service.TeamRawService;
import com.siemens.bt.jazz.services.base.rest.AbstractRestService;
import com.siemens.bt.jazz.services.base.rest.PathParameters;
import com.siemens.bt.jazz.services.base.rest.RestRequest;
import org.apache.commons.logging.Log;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestService extends AbstractRestService {
    public TestService(
            Log log,
            HttpServletRequest request,
            HttpServletResponse response,
            RestRequest restRequest,
            TeamRawService parentService,
            PathParameters pathParameters) {
        super(log, request, response, restRequest, parentService, pathParameters);
    }

    @Override
    public void execute() throws Exception {
        // do nothing for now
    }
}
