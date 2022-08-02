/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyect.web.gateway.security;
import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.cors.reactive.CorsUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
/*
@Configuration
public class CORS {

	  private static final String ALLOWED_HEADERS = "x-requested-with, authorization, Content-Type, Authorization, credential, X-XSRF-TOKEN, token, username, client";
	    private static final String ALLOWED_METHODS = "*";
	    private static final String ALLOWED_ORIGIN  = "*";
	    private static final String ALLOWED_EXPOSE  = "*";

	    @Bean
	    public WebFilter corsFilter() {
	        return (ServerWebExchange ctx, WebFilterChain chain) -> {
	            ServerHttpRequest request = ctx.getRequest();
	            if (CorsUtils.isCorsRequest(request)) {
	                ServerHttpResponse response = ctx.getResponse();
	                HttpHeaders headers = response.getHeaders();
	                headers.add("Access-Control-Allow-Origin", ALLOWED_ORIGIN);
	                headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
	                headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
	                headers.add("Access-Control-Expose-Headers", ALLOWED_EXPOSE);
	                headers.add("Access-Control-Allow-Credentials", "true");
	                if (request.getMethod() == HttpMethod.OPTIONS) {
	                    response.setStatusCode(HttpStatus.OK);
	                    return Mono.empty();
	                }
	            }
	            return chain.filter(ctx);
	        };
	    }
}*/