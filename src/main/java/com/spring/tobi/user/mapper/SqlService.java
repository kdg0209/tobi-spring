package com.spring.tobi.user.mapper;

public interface SqlService {

    String getSql(String key) throws SqlRetrievalFailureException;
}
