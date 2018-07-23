package com.siemens.bt.jazz.services.base.utils;

public enum PathRegex {
  PARAMETER_MATCH("\\{[^\\/]+\\}"),
  PARAMETER_GROUP("([^\\/]+)");

  private final String pattern;

  PathRegex(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public String toString() {
    return pattern;
  }
}
