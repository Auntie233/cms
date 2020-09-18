package top.auntie.cms.util;

import top.auntie.cms.model.GlobalSession;
import top.auntie.cms.model.SessionInfo;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {

    public static SessionInfo getCurrentUser(HttpServletRequest request) {
        SessionInfo userInfo = (SessionInfo) request.getSession().getAttribute(GlobalSession.SESSION_USER_KEY);
        return userInfo;
    }

    public static void setAttribute(HttpServletRequest request, String key, Object object) {
        request.getSession().setAttribute(key, object);
    }

}
