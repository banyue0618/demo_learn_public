package com.example.demo.common.support;

import javax.servlet.http.HttpServletRequest;

/**
 * @author banyue
 * date 2020-04-09
 */
public class RequestKit {

    public static String getRealIP(final HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        //X-Forwarded-For是个逗号分隔的列表 X->  A->B->C  ->Joss 则Joss.X-Forwarded-For=="X,A,B";Joss.X-Real-IP=="B";
        if (!StrKit.isStrTrimNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
            int p = ip.indexOf(',');
            if (p > 0) {
                ip = ip.substring(0, p).trim();
            }
            return ip;
        }
        ip = request.getHeader("X-Real-IP");
        if (!StrKit.isStrTrimNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("Proxy-Client-IP");
        if (!StrKit.isStrTrimNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (!StrKit.isStrTrimNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (!StrKit.isStrTrimNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (!StrKit.isStrTrimNull(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        return request.getRemoteAddr();
    }

}
