package com.danibuiza.javacodegeeks.springbatch;

import org.springframework.batch.item.ItemProcessor;

public class CustomItemProcessor implements
		ItemProcessor<CustomPojo, CustomPojo> {

	@Override
	public CustomPojo process(final CustomPojo pojo) throws Exception {
		final String id = encode(pojo.getId());
		final String desc = encode(pojo.getDescription());

		final CustomPojo encodedPojo = new CustomPojo(id, desc);

		return encodedPojo;

	}

	private String encode(String word) {
		StringBuffer str = new StringBuffer(word);
		return str.reverse().toString();
	}

}