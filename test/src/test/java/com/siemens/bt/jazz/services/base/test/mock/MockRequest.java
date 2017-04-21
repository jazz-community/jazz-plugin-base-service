package com.siemens.bt.jazz.services.base.test.mock;

import com.siemens.bt.jazz.services.base.test.helper.TestInputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletInputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

public class MockRequest implements HttpServletRequest {
    private final String method;
    private final String body;

    public MockRequest(String method, String body) {
        this.method = method;
        this.body = body;
    }

    public MockRequest(String method) {
        this(method, null);
    }

    @Override
    public String getAuthType() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Cookie[] getCookies() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public long getDateHeader(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getHeader(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Enumeration getHeaders(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Enumeration getHeaderNames() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public int getIntHeader(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public String getPathInfo() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getPathTranslated() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getContextPath() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getQueryString() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getRemoteUser() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isUserInRole(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Principal getUserPrincipal() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getRequestedSessionId() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getRequestURI() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public StringBuffer getRequestURL() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getServletPath() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public HttpSession getSession(boolean b) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public HttpSession getSession() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isRequestedSessionIdValid() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isRequestedSessionIdFromCookie() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isRequestedSessionIdFromURL() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isRequestedSessionIdFromUrl() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Object getAttribute(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Enumeration getAttributeNames() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getCharacterEncoding() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setCharacterEncoding(String s) throws UnsupportedEncodingException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public int getContentLength() {
        return body.length();
    }

    @Override
    public String getContentType() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        return new TestInputStream(this.body);
    }

    @Override
    public String getParameter(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Enumeration getParameterNames() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String[] getParameterValues(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Map getParameterMap() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getProtocol() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getScheme() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getServerName() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public int getServerPort() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public BufferedReader getReader() throws IOException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getRemoteAddr() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getRemoteHost() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setAttribute(String s, Object o) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void removeAttribute(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Locale getLocale() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Enumeration getLocales() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isSecure() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public RequestDispatcher getRequestDispatcher(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getRealPath(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public int getRemotePort() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getLocalName() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getLocalAddr() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public int getLocalPort() {
        throw new RuntimeException("Not implemented in Mock.");
    }
}
