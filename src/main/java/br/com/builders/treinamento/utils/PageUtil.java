/*
* Copyright 2018 Builders
*************************************************************
*Nome     : PageUtil.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.utils;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PageUtil {

	public static Pageable getPageableWithDefaultLimit10(Optional<Integer> page, Optional<Integer> size,
			Optional<String> sort) {

		Integer numPage = 0;
		Integer numSize = 10;
		Pageable pageRequest;

		if (page.isPresent())
			numPage = page.get();

		if (size.isPresent())
			numSize = size.get();

		if (sort.isPresent()) {

			String sorts[] = sort.get().split(",");

			if (sorts[1].toUpperCase().equals("DESC")) {
				pageRequest = new PageRequest(numPage, numSize, new Sort(Sort.Direction.DESC, sorts[0]));
			} else {
				pageRequest = new PageRequest(numPage, numSize, new Sort(Sort.Direction.ASC, sorts[0]));
			}

		} else {
			pageRequest = new PageRequest(numPage, numSize);
		}

		return pageRequest;
	}

	public static Pageable getPageableWithDefaultLimit20(Optional<Integer> page, Optional<Integer> size,
			Optional<String> sort) {

		Integer numPage = 0;
		Integer numSize = 20;
		Pageable pageRequest;

		if (page.isPresent())
			numPage = page.get();

		if (size.isPresent())
			numSize = size.get();

		if (sort.isPresent()) {

			String sorts[] = sort.get().split(",");

			if (sorts[1].toUpperCase().equals("DESC")) {
				pageRequest = new PageRequest(numPage, numSize, new Sort(Sort.Direction.DESC, sorts[0]));
			} else {
				pageRequest = new PageRequest(numPage, numSize, new Sort(Sort.Direction.ASC, sorts[0]));
			}
		} else {
			pageRequest = new PageRequest(numPage, numSize);
		}

		return pageRequest;
	}

	public static Integer getNumeroUltimoElemento(Page page) {
		return getNumeroPrimeiroElemento(page) + (page.getNumberOfElements() - 1);
	}

	public static Integer getNumeroPrimeiroElemento(Page page) {
		return page.isFirst() ? 1 : ((((page.getNumber() + 1) * page.getSize()) + 1) - page.getSize());
	}

}
