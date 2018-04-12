/*
* Copyright 2018 Builders
*************************************************************
*Nome     : CustomerRepository.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.repository;

import br.com.builders.treinamento.domain.Customer;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "parametroSample", path = "parametroSample")
@Lazy
public interface CustomerRepository
		extends MongoRepository<Customer, String> {
}
