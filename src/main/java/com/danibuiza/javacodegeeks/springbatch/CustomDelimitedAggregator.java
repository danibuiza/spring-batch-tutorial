package com.danibuiza.javacodegeeks.springbatch;

import org.springframework.batch.item.file.transform.DelimitedLineAggregator;

public class CustomDelimitedAggregator extends
		DelimitedLineAggregator<CustomPojo> {

	@Override
	public String doAggregate(Object[] fields) {


		return super.doAggregate(fields);
	}

}
