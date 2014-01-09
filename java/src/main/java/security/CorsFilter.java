
/*
 * Copyright 2013-2014 Ascentis Corporation, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is proprietary software; you cannot redistribute it nor modify it.
 *
 * Please contact Ascentis Corporation, Inc., 11040 Main St., Suite 101
 * Bellevue, WA 98004 USA or visit http://www.ascentis.com/contact-us.asp
 * if you need additional information or have any questions.
 */
package security;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Provides the capability to specify cross-origin access, via:
 *
 * <code>HttpServletResponse.addHeader("Access-Control-Allow-Origin", "*");</code>
 */
public final class CorsFilter implements Filter {

//    private static final org.apache.log4j.Logger auditLog4J
//            = org.apache.log4j.Logger.getLogger("CorsFilter");

    /**
     * Set CORS (cross-origin resource sharing) header
     *
     * @param request the canonical <code>ServletRequest</code>
     * @param response the canonical <code>ServletResponse</code>
     * @param filterChain the canonical <code>FilterChain</code>
     * @throws java.io.IOException when trouble arises
     * @throws javax.servlet.ServletException when trouble arises
     *
     * @see <a
     * href='http://en.wikipedia.org/wiki/Cross-origin_resource_sharing'>http://en.wikipedia.org/wiki/Cross-origin_resource_sharing</a>
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain filterChain) throws IOException, ServletException {

//        auditLog4J.error("Cross-origin Filter");

        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // TODO This header will most likely not be needed for production because web pages and back-end calls will
        // most likely come from the same origin.
        httpServletResponse.addHeader("Access-Control-Allow-Origin", "*");

        filterChain.doFilter(request, httpServletResponse);
    }

    /**
     * Canonical lifecycle method for an instance of <code>javax.servlet.Filter</code>.
     *
     * @param config the canonical <code>FilterConfig</code> instance
     */
    public void init(FilterConfig config) throws ServletException {
    }

    /**
     * Canonical lifecycle method for an instance of <code>javax.servlet.Filter</code>.
     */
    public void destroy() {
    }
}
