package com.indianstatescensusanalyser.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.indianstatescensusanalyser.csvbuilder.AnalyserException;
import com.indianstatescensusanalyser.csvbuilder.CSVBuilderFactory;
import com.indianstatescensusanalyser.csvbuilder.ICSVBuilder;
import com.indianstatescensusanalyser.csvbuilder.AnalyserException.AnalyserExceptionType;

public class CSVStateCensus {

	public int loadStateCensusData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<IndiaStateCensusData> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndiaStateCensusData> iterator = csvBuilder.getCSVFileIterator(reader, IndiaStateCensusData.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}

	public int loadStateCodeData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<IndianStateCode> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			Iterator<IndianStateCode> iterator = csvBuilder.getCSVFileIterator(reader, IndianStateCode.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}


	private <E> int getCount(Iterator<E> iterator) {
		Iterable<E> csvIterable = () -> iterator;
		return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
	}
}
