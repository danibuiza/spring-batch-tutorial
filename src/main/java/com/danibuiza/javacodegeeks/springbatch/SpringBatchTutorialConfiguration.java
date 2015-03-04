package com.danibuiza.javacodegeeks.springbatch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 * This class contains all the configuration of the Spring Batch application
 * used for this tutorial. It contains readers, writers, processors, jobs, steps
 * and all the needed beans.
 * 
 * @author dgutierrez-diez
 *
 */
@Configuration
@EnableBatchProcessing
public class SpringBatchTutorialConfiguration {

	/* ********************************************
	 * READERS This section contains all the readers
	 * ********************************************
	 */

	/**
	 * 
	 * @return flat file item reader (using an csv extractor)
	 */
	/*
	 * @Bean public ItemReader<CustomPojo> reader() {
	 * FlatFileItemReader<CustomPojo> reader = new
	 * FlatFileItemReader<CustomPojo>(); reader.setResource(new
	 * ClassPathResource("input.csv")); reader.setLineMapper(new
	 * DefaultLineMapper<CustomPojo>() { { setLineTokenizer(new
	 * DelimitedLineTokenizer() { { setNames(new String[] { "id", "description"
	 * }); } }); setFieldSetMapper(new BeanWrapperFieldSetMapper<CustomPojo>() {
	 * { setTargetType(CustomPojo.class); } }); } }); return reader; }
	 */

	/**
	 * 
	 * 
	 * @return custom item reader (dummy), using an iterator within an internal
	 *         list
	 */
	@Bean
	public ItemReader<CustomPojo> reader() {
		CustomItemReader reader = new CustomItemReader();
		List<CustomPojo> pojos = new ArrayList<CustomPojo>();
		pojos.add(new CustomPojo("1", "desc1"));
		pojos.add(new CustomPojo("2", "desc2"));
		pojos.add(new CustomPojo("3", "desc3"));
		reader.setPojos(pojos);
		reader.setIterator(reader.getPojos().iterator());
		return reader;
	}

	/* ********************************************
	 * PROCESSORS This section contains all processors
	 * ********************************************
	 */

	/**
	 * 
	 * @return custom item processor -> anything
	 */
	@Bean
	public ItemProcessor<CustomPojo, CustomPojo> processor() {
		return new CustomItemProcessor();
	}

	/* ********************************************
	 * WRITERS This section contains all the writers
	 * ********************************************
	 */

	/**
	 * 
	 * @param mysqlDataSource
	 * @return mysql writer using a {@link JdbcBatchItemWriter}
	 */
	
	/*@Bean
	 * public ItemWriter<CustomPojo> writer(DataSource mysqlDataSource) {
	 * JdbcBatchItemWriter<CustomPojo> writer = new
	 * JdbcBatchItemWriter<CustomPojo>();
	 * 
	 * writer.setSql("INSERT INTO pojo (id, description) VALUES (:id, :description)"
	 * ); writer.setDataSource(mysqlDataSource);
	 * writer.setItemSqlParameterSourceProvider(new
	 * BeanPropertyItemSqlParameterSourceProvider<CustomPojo>()); return writer;
	 * }
	 */
	/**
	 * 
	 * @param mongoDataSource
	 * @return a writer using the MongoItemWriter
	 */
	
	/*
	 * @Bean
	 * public ItemWriter<CustomPojo> writer(DataSource mongoDataSource) {
	 * MongoItemWriter<CustomPojo> writer = new MongoItemWriter<CustomPojo>();
	 * writer.setCollection("testCollection");
	 * 
	 * return writer; }
	 */
	/**
	 * 
	 * @param dataSource
	 * @return hsqldb writer using JdbcBatchItemWriter (the difference is the
	 *         datasource)
	 */
	/*@Bean
	 * public ItemWriter<CustomPojo> writer(DataSource dataSource) {
	 * System.out.println("writer called"); JdbcBatchItemWriter<CustomPojo>
	 * writer = new JdbcBatchItemWriter<CustomPojo>();
	 * writer.setItemSqlParameterSourceProvider(new
	 * BeanPropertyItemSqlParameterSourceProvider<CustomPojo>());
	 * writer.setSql("INSERT INTO pojo (id, description) VALUES (:id, :description)"
	 * ); writer.setDataSource(dataSource); return writer; }
	 */
	/**
	 * 
	 * @param dataSource
	 * @return dummy item writer custom
	 */
	@Bean
	public ItemWriter<CustomPojo> writer(DataSource dataSource) {
		CustomItemWriter writer = new CustomItemWriter();

		return writer;
	}

	/**
	 * FlatFileItemWriter writer
	 * 
	 * @return
	 */
	/*
	 * @Bean public ItemWriter<CustomPojo> writer() {
	 * FlatFileItemWriter<CustomPojo> writer = new CustomFlatFileItemWriter();
	 * writer.setResource(new ClassPathResource("output.csv"));
	 * 
	 * BeanWrapperFieldExtractor<CustomPojo> fieldExtractor = new
	 * CustomFieldExtractor(); fieldExtractor.setNames(new String[] { "id",
	 * "description" });
	 * 
	 * DelimitedLineAggregator<CustomPojo> delLineAgg = new
	 * CustomDelimitedAggregator(); delLineAgg.setDelimiter(",");
	 * delLineAgg.setFieldExtractor(fieldExtractor);
	 * 
	 * writer.setLineAggregator(delLineAgg); return writer; }
	 */
	/* ********************************************
	 * JOBS ***************************************
	 * ********************************************
	 */
	/**
	 * 
	 * @param jobs
	 * @param s1
	 *            steps
	 * @return the Job
	 */
	@Bean
	public Job importUserJob(JobBuilderFactory jobs, Step s1) {
		return jobs.get("importUserJob").incrementer(new RunIdIncrementer())
				.flow(s1).end().build();
	}

	/* ********************************************
	 * STEPS **************************************
	 * ********************************************
	 */

	/**
	 * the step 1 contains a reader a processor and a writer using a chunk of 10
	 * 
	 * @param stepBuilderFactory
	 * @param reader
	 * @param writer
	 * @param processor
	 * @return
	 */
	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<CustomPojo> reader, ItemWriter<CustomPojo> writer,
			ItemProcessor<CustomPojo, CustomPojo> processor) {
		/* it handles bunches of 10 units */
		return stepBuilderFactory.get("step1")
				.<CustomPojo, CustomPojo> chunk(10).reader(reader)
				.processor(processor).writer(writer).build();
	}

	/* ********************************************
	 * UTILITY BEANS ******************************
	 * ********************************************
	 */

	/**
	 * jdbc template (hsqldb)
	 * 
	 * @param dataSource
	 * @return JdbcTemplate
	 */
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	/**
	 * mongo db factory
	 * 
	 * @return
	 * @throws Exception
	 */
	/*
	 * public MongoDbFactory mongoDbFactory() throws Exception { return new
	 * SimpleMongoDbFactory(new MongoClient(), "testDB"); }
	 */

	/**
	 * mongo db template
	 * 
	 * @param dataSource
	 * @return MongoTemplate
	 * @throws Exception
	 */
	/*
	 * @Bean public MongoTemplate mongoTemplate(DataSource dataSource) throws
	 * Exception { return new MongoTemplate(mongoDbFactory()); }
	 */

	/**
	 * mysql datasource
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	/*@Bean
	public DataSource mysqlDataSource() throws SQLException {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/spring_batch_annotations");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}*/

	/**
	 * mysql datasource
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	/*@Bean
	public DataSource mongoDataSource() throws SQLException {
		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost/spring_batch_annotations");
		dataSource.setUsername("root");
		dataSource.setPassword("root");
		return dataSource;
	}*/

}