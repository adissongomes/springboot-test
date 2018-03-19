/*
* Copyright 2018 Builders
*************************************************************
*Nome     : SampleEnum.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.enumeration;

import static java.lang.String.format;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

public enum SampleEnum {
  NONO1("nono1"), NONO2("nono2");

  private String code;

  SampleEnum(String code) {
        this.code = code;
    }

  public String getCode() {
    return this.code;
  }

  public static SampleEnum get(String code) {
    return Arrays.stream(SampleEnum.values()).filter(e -> StringUtils.equals(e.code, code)).findFirst()
        .orElseThrow(() -> new IllegalStateException(format("Unsupported code %s.", code)));
  }
}
