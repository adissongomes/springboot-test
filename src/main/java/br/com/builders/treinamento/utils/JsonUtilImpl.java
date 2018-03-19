/*
* Copyright 2018 Builders
*************************************************************
*Nome     : JsonUtilImpl.java
*Autor    : Builders
*Data     : Thu Mar 08 2018 00:02:30 GMT-0300 (-03)
*Empresa  : Platform Builders
*************************************************************
*/
package br.com.builders.treinamento.utils;

import br.com.builders.treinamento.exception.ApiJsonException;

public class JsonUtilImpl {

    public Object toObject(final String json, final Class clazz) throws ApiJsonException {
        return JsonUtil.toObject(json, clazz);
    }

    public String fromObject(final Object json) {
        return JsonUtil.fromObject(json);
    }
}
