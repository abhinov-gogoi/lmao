package com.netcat.meow.Interceptor;

import com.netcat.meow.Utility.Literal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AppInterceptor implements HandlerInterceptor {

    Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        /**
         * Hold the Api Url
         */
        StringBuffer api_url = request.getRequestURL();
        /**
         * In case of API call
         */
        if (api_url.length() == 0) {
            return Literal.FALSE;
        } else if (api_url.indexOf("html") > 0 || api_url.indexOf("js") > 0 || api_url.indexOf("css") > 0) {
            LOGGER.info("Document URL : " + api_url + " :: Request Address : " + request.getRemoteAddr() + " :: User-Agent : " + request.getHeader("User-Agent"), this.getClass());
        } else if (api_url.indexOf("error") > 0) {
            LOGGER.error("Error URL : " + api_url + " :: Request Address : " + request.getRemoteAddr() + " :: User-Agent : " + request.getHeader("User-Agent"), this.getClass());
        } else {
            LOGGER.info("Api URL : " + api_url + " :: Request Address : " + request.getRemoteAddr() + " :: " + request.getMethod() + " :: User-Agent : " + request.getHeader("User-Agent"), this.getClass());
        }
        /**
         * Check for the Null
         */
        if (request.getHeader("User-Agent") == null || request.getHeader("User-Agent").trim().equals("")) {
            return Literal.FALSE;
        }
        return Literal.TRUE;
    }
}
