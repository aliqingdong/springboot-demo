package com.liqingdong.core.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author liqingdong
 * @since 2019/01/30 18:02
 */
public class HttpUtils {

    // 判断是否为ajax请求
    public static boolean isAjaxRequest(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Request-With"));
    }
}
