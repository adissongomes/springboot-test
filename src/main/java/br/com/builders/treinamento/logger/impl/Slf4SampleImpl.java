/*
* Copyright 2018 Builders
*************************************************************
*Nome     : Slf4SampleImpl.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.logger.impl;

import br.com.builders.treinamento.logger.Slf4Sample;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Slf4SampleImpl extends Slf4Sample {

  @Override
  protected void SampleMethod(String id, String nono) {
    log.info("Describe message: id={}, nono={}.", id, nono);
  }


}
