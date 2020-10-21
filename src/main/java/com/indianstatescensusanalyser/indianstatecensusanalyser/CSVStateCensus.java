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
import com.indianstatescensusanalyser.csvbuilder.CensusAnalyserException;
import com.indianstatescensusanalyser.csvbuilder.CensusAnalyserException.ExceptionType;
import com.indianstatescensusanalyser.csvbuilder.ICSVBuilder;

public class CSVStateCensus {

	List<IndianStateCode> censusDataList = null;
	List<IndianStateCode> stateDataList =  null;
	
	public int loadStateCensusData(String filePath) throws AnalyserException {
		censusDataList = getCSVList(filePath, IndiaStateCensusData.class);
		return censusDataList.size();
	}

	public int loadStateCodeData(String filePath) throws AnalyserException {
		stateDataList = getCSVList(filePath, IndianStateCode.class);
		return stateDataList.size();
	}
	
	private <E> List<E> getCSVList(String filePath, Class csvClass) throws AnalyserException{
		try (Reader reader = Files.newBufferedReader(Paths.get(filePath));) {
			ICSVBuilder<E> csvBuilder = CSVBuilderFactory.createCSVBuilder();
			return (List<E>) csvBuilder.getCSVFileList(reader, csvClass, filePath);
		}catch (IOException e) {
			throw new AnalyserException(AnalyserException.AnalyserExceptionType.FILE_PROBLEM, "File Problem encountered");
		}
	}
	
	public String getStateWiseSortedData() throws CensusAnalyserException {
		if(censusDataList == null || censusDataList.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found", CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IndianStateCode> comparator = Comparator.comparing(indianStateCode -> indianStateCode.getStateName());
		censusDataList.sort(comparator);
		return getListAsJsonString(censusDataList);
	}
	
	public String getStateCodeWiseSortedData() throws CensusAnalyserException {
		if(censusDataList == null || censusDataList.size() == 0) {
			throw new CensusAnalyserException("Census Data Not Found", CensusAnalyserException.ExceptionType.NO_DATA_FOUND);
		}
		Comparator<IndianStateCode> comparator = Comparator.comparing(indianStateCode -> indianStateCode.getStateCode());
		censusDataList.sort(comparator);
		return getListAsJsonString(censusDataList);
	}
	
	private String getListAsJsonString(List list) {
		Gson gson = new Gson();
		String sortedCensusJsonString = gson.toJson(list);
		return sortedCensusJsonString;
	}

}
