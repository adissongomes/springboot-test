/*
* Copyright 2018 Builders
*************************************************************
*Nome     : JsonUtil.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParseException;

import br.com.builders.treinamento.exception.ApiJsonException;

public class JsonUtil {

	private static final Gson GSON = new GsonBuilder().setDateFormat(ConverterUtil.FORMATO_DATA_HORA_ISO_8601).create();

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Object toObject(final String json, final Class clazz) throws ApiJsonException {
		Object objRequest = null;
		try {
			objRequest = GSON.fromJson(json, clazz);
		} catch (final JsonParseException e) {
			throw new ApiJsonException(e);
		}
		return objRequest;
	}

	public static String fromObject(final Object json) {
		return GSON.toJson(json);
	}

}
