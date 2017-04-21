package com.siemens.bt.jazz.services.base.test.helper;

import org.apache.commons.logging.Log;

import java.util.ArrayList;

public class TestLogger implements Log{
    private ArrayList<String> messages = new ArrayList<>();

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isErrorEnabled() {
        return true;
    }

    @Override
    public boolean isFatalEnabled() {
        return true;
    }

    @Override
    public boolean isInfoEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public boolean isWarnEnabled() {
        return true;
    }

    @Override
    public void trace(Object o) {
        messages.add("trace: " + o.toString());
    }

    @Override
    public void trace(Object o, Throwable throwable) {
        messages.add("trace:" + o.toString() + throwable.getMessage());
    }

    @Override
    public void debug(Object o) {
        messages.add("debug: " + o.toString());
    }

    @Override
    public void debug(Object o, Throwable throwable) {
        messages.add("debug: " + o.toString() + throwable);
    }

    @Override
    public void info(Object o) {
        messages.add("info: " + o.toString());
    }

    @Override
    public void info(Object o, Throwable throwable) {
        messages.add("info" + o.toString() + throwable);
    }

    @Override
    public void warn(Object o) {
        messages.add("warn: " + o.toString());
    }

    @Override
    public void warn(Object o, Throwable throwable) {
        messages.add("warn" + o.toString() + throwable);
    }

    @Override
    public void error(Object o) {
        messages.add("error" + o.toString());
    }

    @Override
    public void error(Object o, Throwable throwable) {
        messages.add("error" + o.toString() + throwable);
    }

    @Override
    public void fatal(Object o) {
        messages.add("fatal" + o.toString());
    }

    @Override
    public void fatal(Object o, Throwable throwable) {
        messages.add("fatal" + o.toString() + throwable);
    }
}
