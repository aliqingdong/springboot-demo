package com.liqingdong.web.security;

import com.liqingdong.core.util.HttpUtils;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.MediaType;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 框架认证Session失效策略处理，区别处理Ajax请求
 * <p>
 * 前台Ajax配合使用
 * <pre>
 * jQuery.ajaxSetup({
 *   complete:function(xhr){
 *     var sessionStatus = xhr.getResponseHeader("sessionStatus");
 *     if(sessionStatus==='timeout'){
 *       alert("会话超时，请重新登录！");
 *       var redirectUrl = xhr.getResponseHeader("redirectUrl");
 *       top.location.href = redirectUrl;
 *     }
 *   }
 * });
 * </pre>
 *
 * @author liqingdong
 * @since 2019/01/30 17:50
 */
public class CustomAjaxSessionStrategy implements InvalidSessionStrategy, RedirectStrategy {

    @Getter
    @Setter
    private String invalidSessionUrl;

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void sendRedirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        process(request, response, url);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        process(request, response, invalidSessionUrl);
    }

    private void process(HttpServletRequest request, HttpServletResponse response, String url) throws IOException {
        if (HttpUtils.isAjaxRequest(request)) {
            response.setCharacterEncoding("UTF-8");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setHeader("sessionStatus", "timeout");
            response.setHeader("redirectUrl", getRedirectUrl(url, request, response));
        } else redirectStrategy.sendRedirect(request, response, url);
    }

    private String getRedirectUrl(String url, HttpServletRequest request, HttpServletResponse response) {
        return response.encodeRedirectURL(request.getContextPath() + url);
    }
}
