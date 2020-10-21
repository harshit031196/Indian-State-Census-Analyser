package com.indianstatescensusanalyser.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

import com.indianstatescensusanalyser.csvbuilder.AnalyserException;
import com.indianstatescensusanalyser.csvbuilder.CSVBuilderFactory;
import com.indianstatescensusanalyser.csvbuilder.ICSVBuilder;
import com.indianstatescensusanalyser.csvbuilder.AnalyserException.AnalyserExceptionType;

public class CSVStateCensus {

	public int loadStateCensusData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<IndiaStateCensusData> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<IndiaStateCensusData> list = csvBuilder.getCSVFileList(reader, IndiaStateCensusData.class, filePath);
			return list.size();
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}

	public int loadStateCodeData(String filePath) throws AnalyserException {
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<IndianStateCode> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			List<IndianStateCode> stateCensusList = csvBuilder.getCSVFileList(reader, IndianStateCode.class, filePath);
			return stateCensusList.size();
		} catch (IOException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
		}
	}

}
