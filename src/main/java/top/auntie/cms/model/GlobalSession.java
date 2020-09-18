package top.auntie.cms.model;

import java.util.HashMap;
import java.util.Map;

public class GlobalSession {

    public static final String SESSION_USER_KEY = "user";
    private static final ThreadLocal requestThreadLocal = new ThreadLocal();

    protected GlobalSession() {
    }

    public static SessionInfo getCurrentUser() {
        return get(SESSION_USER_KEY, SessionInfo.class);
    }

    public static Object get(String attribute) {
        Map map = (Map)requestThreadLocal.get();
        return map.get(attribute);
    }

    public static <T> T get(String attribute, Class<T> clazz) {
        return (T) get(attribute);
    }

    public static void set(String attribute, Object value) {
        Map map = (Map)requestThreadLocal.get();
        if (map == null) {
            map = new HashMap();
            requestThreadLocal.set(map);
        }

        (map).put(attribute, value);
    }

    public static void remove() {
        requestThreadLocal.remove();
    }

}
