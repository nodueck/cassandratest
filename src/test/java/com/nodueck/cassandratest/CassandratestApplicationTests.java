package com.nodueck.cassandratest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.config.DriverConfigLoader;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CassandratestApplicationTests {


	private static final int TEST_SIZE = 10_000;

	@Test
	void testSaveAndLoadData() throws Exception {
		CqlSession session = CqlSession.builder().withConfigLoader(DriverConfigLoader.fromClasspath("cassandra.conf")).build();
		session.execute("truncate cassandra_test_entity");
		CassandraTestMapper mapper = new CassandraTestMapperBuilder(session).build();
		CassandraTestDao dao = mapper.cassandraTestDao();


		for(int i = 0; i < TEST_SIZE; i++){
			CassandraTestEntity entity = buildCassandraTestEntity(i);
			dao.save(entity).exceptionally(throwable -> {
				System.out.println(throwable); 
				return null; 
			});
		}

		TimeUnit.SECONDS.sleep(5L);

		assertEquals(TEST_SIZE, dao.all().all().size());

	}

	private CassandraTestEntity buildCassandraTestEntity(long id) {
		CassandraTestEntity entity = new CassandraTestEntity();
		entity.setId(id);
		entity.setTimestamp(Instant.now());
		entity.setSomeText(UUID.randomUUID().toString());
		return entity;
	}

}
