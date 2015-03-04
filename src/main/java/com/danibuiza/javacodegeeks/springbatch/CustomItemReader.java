package com.danibuiza.javacodegeeks.springbatch;

import java.util.Iterator;
import java.util.List;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class CustomItemReader implements ItemReader<CustomPojo> {

	private List<CustomPojo> pojos;

	private Iterator<CustomPojo> iterator;

	@Override
	public CustomPojo read() throws Exception, UnexpectedInputException,
			ParseException, NonTransientResourceException {

		if (getIterator().hasNext()) {
			return getIterator().next();
		}
		return null;

	}

	public List<CustomPojo> getPojos() {
		return pojos;
	}

	public void setPojos(List<CustomPojo> pojos) {
		this.pojos = pojos;
	}

	public Iterator<CustomPojo> getIterator() {
		return iterator;
	}

	public void setIterator(Iterator<CustomPojo> iterator) {
		this.iterator = iterator;
	}

}
