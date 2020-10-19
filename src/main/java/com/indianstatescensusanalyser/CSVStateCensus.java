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
			if (!(filePath.split("\\.")[1].equals("csv"))) {
				throw new AnalyserException(AnalyserExceptionType.INCORRECT_TYPE, "Incorrect File Type");
			}
			BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(filePath));
			String[] headerColumns = bufferedReader.readLine().split(",");
			if (headerColumns.length < 5) {
				throw new AnalyserException(AnalyserExceptionType.INCORRECT_DELIMITER, "File contains Invalid Delimiter");
			}
			if (!headerColumns[1].equals("State Name") || !headerColumns[2].equals("TIN")
					|| !headerColumns[3].equals("Population") || !headerColumns[4].equals("State Code")) {
				throw new AnalyserException(AnalyserExceptionType.INCORRECT_HEADER, "Incorrect Header");
			}
			bufferedReader.close();
			CsvToBean<IndiaStateCensusData> csvToBean = new CsvToBeanBuilder<IndiaStateCensusData>(reader)
					                                          .withType(IndiaStateCensusData.class).withIgnoreLeadingWhiteSpace(true).build();
			Iterator<IndiaStateCensusData> iterator = csvToBean.iterator();
			Iterable<IndiaStateCensusData> csvIterable = () -> iterator;
			return (int) StreamSupport.stream(csvIterable.spliterator(), false).count();
		} catch (IllegalStateException e) {
			throw new AnalyserException(AnalyserExceptionType.UNABLE_TO_PARSE, "Unable to parse");
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}
}
