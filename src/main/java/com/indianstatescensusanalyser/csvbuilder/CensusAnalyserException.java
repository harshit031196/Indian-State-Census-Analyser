package com.indianstatescensusanalyser.csvbuilder;

public class CensusAnalyserException extends Exception {
	
	ExceptionType type;

	public enum ExceptionType {
		NO_DATA_FOUND;
	}

	public CensusAnalyserException(String message, ExceptionType exceptionType) {
		super(message);
		this.type = exceptionType;
	}
}
