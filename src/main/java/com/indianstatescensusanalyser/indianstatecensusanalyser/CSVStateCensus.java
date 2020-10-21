package com.indianstatescensusanalyser.indianstatecensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;
import com.indianstatescensusanalyser.csvbuilder.AnalyserException;
import com.indianstatescensusanalyser.csvbuilder.AnalyserException.AnalyserExceptionType;
import com.indianstatescensusanalyser.csvbuilder.CSVBuilderFactory;
import com.indianstatescensusanalyser.csvbuilder.ICSVBuilder;

public class CSVStateCensus {

//	public int loadStateCensusData(String filePath) throws AnalyserException {
//		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
//			ICSVBuilder<IndiaStateCensusData> csvBuilder = CSVBuilderFactory.createCSVBuilder();
//			List<IndiaStateCensusData> list = csvBuilder.getCSVFileList(reader, IndiaStateCensusData.class, filePath);
//			return list.size();
//		} catch (IOException e) {
//			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
//		}
//	}
//
//	public int loadStateCodeData(String filePath) throws AnalyserException {
//		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
//			ICSVBuilder<IndianStateCode> csvBuilder = CSVBuilderFactory.createCSVBuilder();
//			List<IndianStateCode> stateCensusList = csvBuilder.getCSVFileList(reader, IndianStateCode.class, filePath);
//			return stateCensusList.size();
//		} catch (IOException e) {
//			throw new AnalyserException(AnalyserExceptionType.FILE_PROBLEM, "File problem encountered");
//		}
//	}
	
	public int loadStateCensusData(String filePath) throws AnalyserException {
		List<IndiaStateCensusData> stateCensusList = getList(filePath, IndiaStateCensusData.class);
		return stateCensusList.size();
	}

	public int loadStateCodeData(String filePath) throws AnalyserException {
		List<IndianStateCode> stateCensusList = getList(filePath, IndianStateCode.class);
		return stateCensusList.size();
	}
	 
	private <E> List<E> getList(String filePath, Class csvClass) throws AnalyserException{
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<E> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return (List<E>) csvBuilder.getCSVFileList(reader, csvClass, filePath);
		}catch (IOException e) {
			throw new AnalyserException(AnalyserException.AnalyserExceptionType.FILE_PROBLEM, "File Problem encountered");
		}
	}

	public String getSortedDataStateWise(String filePath) throws AnalyserException {
		List<IndiaStateCensusData> stateCensusList = getList(filePath, CSVStateCensus.class);
		Comparator<IndiaStateCensusData> comparator = Comparator.comparing(IndiaStateCensusData -> IndiaStateCensusData.getState());
		stateCensusList.sort(comparator);
		Gson gson = new Gson();
		String sortedCensusJsonString = gson.toJson(stateCensusList);
		return sortedCensusJsonString;
	}

}
