package com.indianstatescensusanalyser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

import com.indianstatescensusanalyser.AnalyserException.AnalyserExceptionType;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class OpenCSVBuilder {
	
	public <E> Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath)
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
			if (!headerColumns[1].equals("State Name") || !headerColumns[2].equals("TIN")
					|| !headerColumns[3].equals("Population") || !headerColumns[4].equals("State Code"))
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
