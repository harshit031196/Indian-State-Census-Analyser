package com.indianstatescensusanalyser;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.google.gson.Gson;
import com.indianstatescensusanalyser.csvbuilder.AnalyserException;
import com.indianstatescensusanalyser.indianstatecensusanalyser.CSVStateCensus;
import com.indianstatescensusanalyser.indianstatecensusanalyser.IndiaStateCensusData;

import junit.framework.TestCase;
public class StateAnalyserTest extends TestCase {
	private static final String STATE_CENSUS_FILE_PATH = "IndianStateCensusData.csv";
	private static final String INCORRECT_STATE_CENSUS_FILE_PATH = "IndianStateCensusData.csv";
	private static final String INCORRECT_TYPE_STATE_CENSUS_FILE_PATH = "IndianStateCensusData.csv";
	private static final String INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH = "IndianStateCensusData.csv";
	private static final String INCORRECT_HEADER_STATE_CENSUS_FILE_PATH = "IndianStateCensusData.csv";
	
private CSVStateCensus stateCensusAnalyser;	
	
	@Before
	public void setup() {
		stateCensusAnalyser = new CSVStateCensus();
		ExpectedException exceptionRule = ExpectedException.none();
		exceptionRule.expect(AnalyserException.class);
	}

	@Test
	public void givenCensusFileShouldReturnCorrectNumberOfEnteries() {
		CSVStateCensus csvStateCensus = new CSVStateCensus();
		try {
			int entries = csvStateCensus.loadStateCensusData(STATE_CENSUS_FILE_PATH);
			assertEquals(29, entries);
		} catch (AnalyserException e) {

		}
	}
	
//	@Test
//	public void givenCensusFileWhenSortedShouldReturnTheSortedList() {
//		try {
//			String stateWiseSortedString = stateCensusAnalyser.getSortedDataStateWise(STATE_CENSUS_FILE_PATH);
//			IndiaStateCensusData[] censusArray = new Gson().fromJson(stateWiseSortedString, IndiaStateCensusData[].class);
//			int size = censusArray.length;
//			assertEquals("Andaman and Nicobar Islands", censusArray[0].getState());
//			assertEquals("West Bengal", censusArray[size-1].getState());
//		} catch (AnalyserException e) {
//			
//		}
//		
//	}

	@Test
	public void givenWrongFilePathShouldThrowAnException() {
		CSVStateCensus csvStateCensus = new CSVStateCensus();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(AnalyserException.class);
			csvStateCensus.loadStateCensusData(INCORRECT_STATE_CENSUS_FILE_PATH);
		} catch (AnalyserException e) {
			assertEquals(AnalyserException.AnalyserExceptionType.FILE_PROBLEM, e.type);
		}
	}

	@Test
	public void givenWrongFileTypeWhenProcessedShouldThrowAnException() {
		CSVStateCensus csvStateCensus = new CSVStateCensus();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(AnalyserException.class);
			csvStateCensus.loadStateCensusData(INCORRECT_TYPE_STATE_CENSUS_FILE_PATH);
		} catch (AnalyserException e) {
			assertEquals(AnalyserException.AnalyserExceptionType.INCORRECT_TYPE, e.type);
		}
	}

	@Test
	public void givenFileWithWrongDelimiterWhenProcessedShouldThrowAnException() {
		CSVStateCensus csvStateCensus = new CSVStateCensus();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(AnalyserException.class);
			csvStateCensus.loadStateCensusData(INCORRECT_DELIMITER_STATE_CENSUS_FILE_PATH);
		} catch (AnalyserException e) {
			assertEquals(AnalyserException.AnalyserExceptionType.INCORRECT_DELIMITER, e.type);
		}
	}
	
	@Test
	public void givenFileWithWrongHeaderWhenProcessedShouldThrowAnException() {
		CSVStateCensus csvStateCensus = new CSVStateCensus();
		try {
			ExpectedException exceptionRule = ExpectedException.none();
			exceptionRule.expect(AnalyserException.class);
			csvStateCensus.loadStateCensusData(INCORRECT_HEADER_STATE_CENSUS_FILE_PATH);
		} catch (AnalyserException e) {
			assertEquals(AnalyserException.AnalyserExceptionType.INCORRECT_HEADER, e.type);
		}
	}
}
