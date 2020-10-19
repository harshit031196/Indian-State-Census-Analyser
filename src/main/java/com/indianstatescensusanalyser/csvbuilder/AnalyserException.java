package com.indianstatescensusanalyser.csvbuilder;

public class AnalyserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2495158559928012312L;
	public enum AnalyserExceptionType{
		FILE_PROBLEM, UNABLE_TO_PARSE, INCORRECT_TYPE, INCORRECT_DELIMITER, INCORRECT_HEADER;
	}
	private String message;
	public AnalyserExceptionType type;
	public AnalyserException(AnalyserExceptionType type,String message){
		super(message);
		this.type=type;
	}
}
