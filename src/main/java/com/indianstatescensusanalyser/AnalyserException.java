package com.indianstatescensusanalyser;

public class AnalyserException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2495158559928012312L;
	public enum AnalyserExceptionType{
		FILE_NOT_FOUND_TYPE, INCORRECT_TYPE, DELIMITER_OR_HEADER_TYPE, OTHER_TYPE
	}
	private String message;
	public AnalyserExceptionType type;
	public AnalyserException() {
		
	}
	public AnalyserException(AnalyserExceptionType type,String message){
		super(message);
		this.type=type;
	}
}
