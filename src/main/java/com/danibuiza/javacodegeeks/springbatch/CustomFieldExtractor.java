package com.danibuiza.javacodegeeks.springbatch;

import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;

public class CustomFieldExtractor extends
		BeanWrapperFieldExtractor<CustomPojo> {

	@Override
	public Object[] extract(CustomPojo pojo) {

		System.out.println("calling pojos" + pojo);
		
		Object[] results = super.extract(pojo);
		return results;
	}

}
