# Reproduce NoNodeAvailableException

## Run Cassandra in a Container
`docker run -d --name cassandratest -e CASSANDRA_START_RPC=true -e CASSANDDRA_LISTEN_ADDRESS=cassandratest -p9042:9042 -p7070:7070 cassandra`

## Create Keyspace and Table
### Open Bash in the cassandra container
`docker exec -it cassandratest /bin/bash`
### Run cqlsh
`cqlsh`
### Create keyspace and table
`create keyspace cassandra_test with replication = {'class': 'SimpleStrategy', 'replication_factor': 1};`

`use cassandra_test;`

`create table cassandra_test_entity ( id bigint, timestamp timestamp, some_text text, PRIMARY KEY((id),timestamp)) WITH CLUSTERING ORDER BY (timestamp DESC) AND dclocal_read_repair_chance=0.000000 AND gc_grace_seconds=864000 AND read_repair_chance=0.100000 AND  compaction={'sstable_size_in_mb': '160', 'class': 'LeveledCompactionStrategy'} AND compression={'sstable_compression': 'SnappyCompressor'};`

## Run Test
`gradlew build`