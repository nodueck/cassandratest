package com.nodueck.cassandratest;

import com.datastax.oss.driver.api.mapper.annotations.DaoFactory;
import com.datastax.oss.driver.api.mapper.annotations.Mapper;

@Mapper
public interface CassandraTestMapper {

    @DaoFactory
    CassandraTestDao cassandraTestDao();
    
}
