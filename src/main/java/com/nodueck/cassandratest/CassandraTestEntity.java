package com.nodueck.cassandratest;

import java.time.Instant;

import com.datastax.oss.driver.api.mapper.annotations.ClusteringColumn;
import com.datastax.oss.driver.api.mapper.annotations.Entity;
import com.datastax.oss.driver.api.mapper.annotations.PartitionKey;

import lombok.Data;

@Data
@Entity
public class CassandraTestEntity {
    
    @PartitionKey(0)
    private Long id;
    
    @ClusteringColumn(0)
    private Instant timestamp;

    private String someText;


    public Long getId(){
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Instant timestamp){
        this.timestamp = timestamp;
    }

    public String getSomeText(){
        return this.someText;
    }

    public void setSomeText(String someText){
        this.someText = someText;
    }

}
