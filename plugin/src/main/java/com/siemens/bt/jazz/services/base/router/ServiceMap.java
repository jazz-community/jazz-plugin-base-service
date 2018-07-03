package com.siemens.bt.jazz.services.base.router;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.siemens.bt.jazz.services.base.rest.DefaultRestService;
import com.siemens.bt.jazz.services.base.router.factory.RestFactory;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class ServiceMap {
    private Map<String, Map<HttpConstants.HttpMethod, ServiceFactory>> services = new HashMap<>();

    public ServiceFactory getFactory(HttpServletRequest request, String uri) {
        for (String path : services.keySet()) {
            String regex = path.replaceAll("\\{[^\\/]+\\}", "([^\\/]+)");
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(uri).matches()) {
                return services
                        .get(path)
                        .get(HttpConstants.HttpMethod.fromString(request.getMethod()));
            }
        }

        return new RestFactory("", DefaultRestService.class);
    }
}
