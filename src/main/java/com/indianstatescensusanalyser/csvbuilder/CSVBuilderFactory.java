package com.indianstatescensusanalyser.csvbuilder;

public class CSVBuilderFactory {

	public static <E> ICSVBuilder<E> createCSVBuilder() {
		return new OpenCSVBuilder<E>();
	}			
}
