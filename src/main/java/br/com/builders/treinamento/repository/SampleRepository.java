/*
* Copyright 2018 Builders
*************************************************************
*Nome     : SampleRepository.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.repository;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.builders.treinamento.domain.Sample;

@RepositoryRestResource(collectionResourceRel = "parametroSample", path = "parametroSample")
@Lazy
public interface SampleRepository
		extends MongoRepository<Sample, String> {
	public Sample findById(String id);

	public List<Sample> findBySample(String nono);
}
