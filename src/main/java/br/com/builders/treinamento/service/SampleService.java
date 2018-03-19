/*
* Copyright 2018 Builders
*************************************************************
*Nome     : BuildersService.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.service;

import java.net.MalformedURLException;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/*
import br.com.builders.treinamento.BaseToken;
import br.com.builders.treinamento.GatewayWeb;
import br.com.builders.treinamento.Resposta;
import br.com.builders.treinamento.TransacaoTemplate;
*/

@Component
@Lazy
public class SampleService {

  @Value("${host:'http://localhost:8080/WebService?wsdl'}")
  protected String host;

  @Value("${login:'teste'}")
  protected String login;

  @Value("${senha:'teste'}")
  protected String senha;
  /*
  private GatewayWeb service;

  @PostConstruct
  protected void criaSoapPort() throws MalformedURLException {
    service = new GatewayWeb(new URL(host));
  }

  protected Resposta processaTransacao(TransacaoTemplate transacao) {
    return service.getBasicHttpBindingIGatewayWeb().processaTransacao(transacao);
  }

  protected Resposta processaToken(BaseToken baseToken) {
    return service.getBasicHttpBindingIGatewayWeb().processaToken(baseToken);
  }
  */
}
