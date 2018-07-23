package com.siemens.bt.jazz.services.base.router.map;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.siemens.bt.jazz.services.base.rest.service.DefaultRestService;
import com.siemens.bt.jazz.services.base.router.factory.RestFactory;
import com.siemens.bt.jazz.services.base.router.factory.ServiceFactory;
import com.siemens.bt.jazz.services.base.utils.PathRegex;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

public class ServiceMap {
  private Map<String, Map<HttpMethod, ServiceFactory>> services = new HashMap<>();

  public ServiceFactory getFactory(HttpServletRequest request, String uri) {
    for (Map.Entry<String, Map<HttpMethod, ServiceFactory>> entry : services.entrySet()) {
      String regex =
          entry
              .getKey()
              .replaceAll(
                  PathRegex.PARAMETER_MATCH.toString(), PathRegex.PARAMETER_REPLACEMENT.toString());
      Pattern pattern = Pattern.compile(regex);
      HttpMethod method = HttpMethod.fromString(request.getMethod());
      if (pattern.matcher(uri).matches() && entry.getValue().containsKey(method)) {
        return entry.getValue().get(method);
      }
    }

    return new RestFactory(uri, DefaultRestService.class);
  }

  public void add(HttpMethod method, String path, ServiceFactory factory) {
    if (!services.containsKey(path)) {
      services.put(path, new EnumMap<HttpMethod, ServiceFactory>(HttpMethod.class));
    }

    services.get(path).put(method, factory);
  }
}
