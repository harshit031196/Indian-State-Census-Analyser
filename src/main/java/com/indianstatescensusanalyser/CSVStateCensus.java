package com.indianstatescensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.indianstatescensusanalyser.AnalyserException.AnalyserExceptionType;

public class CSVStateCensus {

	public int loadStateCensusData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<IndiaStateCensusData> iterator = new OpenCSVBuilder().getCSVFileIterator(reader, IndiaStateCensusData.class, filePath);
			return getCount(iterator);
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}

	public int loadStateCodeData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<IndianStateCode> iterator = new OpenCSVBuilder().getCSVFileIterator(reader, IndianStateCode.class, filePath);
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
