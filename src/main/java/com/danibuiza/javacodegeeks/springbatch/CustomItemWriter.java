package com.danibuiza.javacodegeeks.springbatch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class CustomItemWriter implements ItemWriter<CustomPojo> {

	@Override
	public void write(List<? extends CustomPojo> pojo) throws Exception {
		System.out.println("writing Pojo " + pojo);
	}

}
