package com.siemens.bt.jazz.services.base.test.mock;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

public class MockResponse implements HttpServletResponse {
    private final CharArrayWriter charArrayWriter = new CharArrayWriter();
    private final PrintWriter writer = new PrintWriter(charArrayWriter);
    private int status;
    private String encoding;
    private String contentType;

    @Override
    public void addCookie(Cookie cookie) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean containsHeader(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String encodeURL(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String encodeRedirectURL(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String encodeUrl(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String encodeRedirectUrl(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void sendError(int i, String s) throws IOException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void sendError(int i) throws IOException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void sendRedirect(String s) throws IOException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setDateHeader(String s, long l) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void addDateHeader(String s, long l) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setHeader(String s, String s1) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void addHeader(String s, String s1) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setIntHeader(String s, int i) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void addIntHeader(String s, int i) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setStatus(int i, String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public String getCharacterEncoding() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setCharacterEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override
    public String getContentType() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    @Override
    public ServletOutputStream getOutputStream() throws IOException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public PrintWriter getWriter() throws IOException {
        return writer;
    }

    @Override
    public void setContentLength(int i) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public int getBufferSize() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setBufferSize(int i) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void flushBuffer() throws IOException {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void resetBuffer() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public boolean isCommitted() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void reset() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Locale getLocale() {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public void setLocale(Locale locale) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    public CharArrayWriter getMockWriter() {
        return this.charArrayWriter;
    }

    public int getStatus() {
        return this.status;
    }

    @Override
    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String getHeader(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Collection<String> getHeaders(String s) {
        throw new RuntimeException("Not implemented in Mock.");
    }

    @Override
    public Collection<String> getHeaderNames() {
        throw new RuntimeException("Not implemented in Mock.");
    }
}
