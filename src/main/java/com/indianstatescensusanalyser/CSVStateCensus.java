package com.indianstatescensusanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import com.indianstatescensusanalyser.AnalyserException.AnalyserExceptionType;

public class CSVStateCensus {

	public int loadStateCensusData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<IndiaStateCensusData> iterator = getCSVFileIterator(reader, IndiaStateCensusData.class, filePath);
			Iterable<IndiaStateCensusData> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}

	public int loadStateCodeData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			Iterator<IndianStateCode> iterator = getCSVFileIterator(reader, IndianStateCode.class, filePath);
			Iterable<IndianStateCode> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}

	private <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath)
			throws AnalyserException {
		try {
			if (!((filePath.split("\\.")[1]).equals("csv"))) {
				throw new AnalyserException(AnalyserExceptionType.INCORRECT_TYPE, "Incorrect file type");
			}
			if (!isCorrectDelimiter(filePath)) {
				throw new AnalyserException(AnalyserExceptionType.INCORRECT_DELIMITER, "File contains Invalid Delimiter");
			}
			if (!isCorrectHeader(filePath)) {
				throw new AnalyserException(AnalyserExceptionType.INCORRECT_HEADER, "Incorrect Header");
			}
			CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader).withType(csvClass)
					                                                .withIgnoreLeadingWhiteSpace(true)
					                                                .build();
			return csvToBean.iterator();
		} catch (IllegalStateException e) {
			throw new AnalyserException(AnalyserExceptionType.UNABLE_TO_PARSE, "Unable to parse");
		}
	}

	public boolean isCorrectHeader(String filePath) throws AnalyserException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (!headerColumns[1].equals("State") || !headerColumns[2].equals("Poplulation")
					|| !headerColumns[3].equals("AreaInSqKm") || !headerColumns[4].equals("DensityPerSqKm"))
				return false;
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File Problem Occured");
		}
		return true;
	}

	public boolean isCorrectDelimiter(String filePath) throws AnalyserException {
		try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));) {
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (headerColumns.length < 5) {
				return false;
			}
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File Problem Occured");
		}
		return true;
	}
}
