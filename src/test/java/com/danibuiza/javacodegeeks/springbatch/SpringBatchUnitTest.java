package com.danibuiza.javacodegeeks.springbatch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.jdbc.core.JdbcTemplate;
/**
 * very simple example of how to use the class jobLauncherTestUtils
 * 
 * @author dgutierrez-diez
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=SpringBatchTutorialConfiguration.class, loader=AnnotationConfigContextLoader.class)
public class SpringBatchUnitTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Test
	public void testLaunchJob() throws Exception {

		// test a complete job
		JobExecution jobExecution = jobLauncherTestUtils.launchJob();

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());

	}

	@Test
	public void testLaunchStep() {

		// test a individual step
		JobExecution jobExecution = jobLauncherTestUtils.launchStep("step1");

		assertEquals(BatchStatus.COMPLETED, jobExecution.getStatus());
	}
}
