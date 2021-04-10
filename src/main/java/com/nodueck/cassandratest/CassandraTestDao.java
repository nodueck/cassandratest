package com.nodueck.cassandratest;

import java.util.concurrent.CompletionStage;

import com.datastax.oss.driver.api.core.PagingIterable;
import com.datastax.oss.driver.api.mapper.annotations.Dao;
import com.datastax.oss.driver.api.mapper.annotations.Insert;
import com.datastax.oss.driver.api.mapper.annotations.Select;

@Dao
public interface CassandraTestDao {

    @Select
    PagingIterable<CassandraTestEntity> all();
    
    @Insert
    CompletionStage<Void> save(CassandraTestEntity entity);

}
