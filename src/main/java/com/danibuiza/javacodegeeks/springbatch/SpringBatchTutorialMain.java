package com.danibuiza.javacodegeeks.springbatch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

@SpringBootApplication
public class SpringBatchTutorialMain implements CommandLineRunner {

	public static void main(String[] args) {

		SpringApplication.run(SpringBatchTutorialMain.class, args);
	}

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Override
	public void run(String... strings) throws Exception {

		System.out.println("running...");

		/*
		 * //different implementations for different scenarios are
		 * neededList<CustomPojo> pojos =
		 * jdbcTemplate.query("SELECT * FROM pojo", new RowMapper<CustomPojo>()
		 * {
		 * 
		 * @Override public CustomPojo mapRow(ResultSet rs, int row) throws
		 * SQLException { return new CustomPojo(rs.getString(1),
		 * rs.getString(2)); } });
		 * 
		 * 
		 * 
		 * for (CustomPojo pojo : pojos) { System.out.println(pojo); }
		 */

	}

}
