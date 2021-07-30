package com.example;

import io.micronaut.data.jdbc.annotation.JdbcRepository;
import io.micronaut.data.jdbc.runtime.JdbcOperations;
import io.micronaut.data.model.query.builder.sql.Dialect;
import io.micronaut.data.repository.GenericRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@JdbcRepository(dialect = Dialect.POSTGRES)
public abstract class ComponentRepository implements GenericRepository<Component, String> {

	private final JdbcOperations jdbcOperations;

	public ComponentRepository(JdbcOperations jdbcOperations) {
		this.jdbcOperations = jdbcOperations;
	}

	@Transactional
	Optional<Component> componentById(String uuid) {
		String sql = "select * from \"SuperMaster\".component where component.\"ID\" = uuid(?)";
		return jdbcOperations.prepareStatement(sql, statement -> {
			statement.setString(1, uuid);
			var resultSet = statement.executeQuery();
			return jdbcOperations.entityStream(resultSet, Component.class).findFirst();
		});
	}
}
