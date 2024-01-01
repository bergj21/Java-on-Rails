package jrails;

import java.util.*;
import java.lang.reflect.*;

public class JRouter {

    private static Map<String, Class<?>> classRoute = new HashMap<>();
    private static Map<String, String> methodRoute = new HashMap<>();


    public void addRoute(String verb, String path, Class clazz, String method) {
        String route = verb + path;
        classRoute.put(route, clazz);
        methodRoute.put(route, method);
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        String route = verb + path;
        Class<?> c = classRoute.get(route);
        String method = methodRoute.get(route);
        if (c == null || method == null) {
            return null;
        }
        return c.getName() + "#" + method;
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        String route = verb + path;
        Class<?> c = classRoute.get(route);
        String methodName = methodRoute.get(route);
        if (c == null || methodName == null) {
            throw new UnsupportedOperationException();
        }
        Html html;
        try {
            Method m = null;
            for (Method method : c.getMethods()) {
                if (method.getName().equals(methodName)) {
                    m = method;
                }
            }
            html = (Html) m.invoke(null, params);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        return html;
    }
}
