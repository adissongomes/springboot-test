/*
* Copyright 2018 Builders
*************************************************************
*Nome     : Slf4jRunnable.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.logger.impl;

import javax.transaction.Transaction;

public class Slf4jRunnable implements Runnable {

  private final Transaction tx;

  public Slf4jRunnable(Transaction tx) {
    this.tx = tx;
  }

  @Override
  public void run() {
    // Implementar
  }
}
