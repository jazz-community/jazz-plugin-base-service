package com.siemens.bt.jazz.services.base.utils;

public enum PathRegex {
  PARAMETER_GROUP("\\{([^\\/}]+)\\}"),
  PARAMETER_MATCH("\\{[^\\/]+\\}"),
  PARAMETER_REPLACEMENT("([^\\/]+)");

  private final String pattern;

  PathRegex(String pattern) {
    this.pattern = pattern;
  }

  @Override
  public String toString() {
    return pattern;
  }
}
