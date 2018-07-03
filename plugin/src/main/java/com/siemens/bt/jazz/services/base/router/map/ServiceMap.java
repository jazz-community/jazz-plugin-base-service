package com.siemens.bt.jazz.services.base.router.map;

import com.ibm.team.jfs.app.http.util.HttpConstants;
import com.siemens.bt.jazz.services.base.rest.service.DefaultRestService;
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
            String regex = path.replaceAll(
                    "\\{[^\\/]+\\}",
                    "([^\\/]+)");
            Pattern pattern = Pattern.compile(regex);
            if (pattern.matcher(uri).matches()) {
                return services
                        .get(path)
                        .get(HttpConstants.HttpMethod.fromString(request.getMethod()));
            }
        }

        return new RestFactory(uri, DefaultRestService.class);
    }

    public void add(
            HttpConstants.HttpMethod method,
            String path,
            ServiceFactory factory) {

        String pattern = path;

        if (!services.containsKey(pattern)) {
            services.put(pattern, new HashMap<HttpConstants.HttpMethod, ServiceFactory>());
        }

        services.get(pattern).put(method, factory);
    }
}
