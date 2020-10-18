package com.indianstatescensusanalyser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.indianstatescensusanalyser.AnalyserException.AnalyserExceptionType;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

public class CSVStateCensus {

	private static final String STRING_READ_SAMPLE = "IndiaStateCensusData - IndiaStateCensusData.csv";
	private static final String STRING_READ_SAMPLE_NEW = "IndiaStateCode - IndiaStateCode.csv";
	public enum IOStateCensusAnalyser {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	public int readFromCSVFile(IOStateCensusAnalyser ioService) throws IOException, AnalyserException{
		if(ioService.equals(IOStateCensusAnalyser.FILE_IO)) {
			int count = this.readToCSV();
			return count;
		}
		return 0;
	}

	public int readToCSV() throws IOException, AnalyserException{
		List<IndianStateCode> data = new ArrayList<IndianStateCode>();
		int count=0;
		try(Reader reader = Files.newBufferedReader(Paths.get(STRING_READ_SAMPLE_NEW))){
			CsvToBeanBuilder<IndianStateCode> csvToBeanBuilder = new CsvToBeanBuilder<IndianStateCode>(reader);
			csvToBeanBuilder.withType(IndianStateCode.class);
			csvToBeanBuilder.withIgnoreLeadingWhiteSpace(true);
			CsvToBean<IndianStateCode> csvToBean = csvToBeanBuilder.build();
			Iterator<IndianStateCode> objectIterator = csvToBean.iterator();
			CSVReader csvReader = new CSVReader(reader);
			while (objectIterator.hasNext()) {	
				if(objectIterator.next()!=null) 
					count++;
			}
			return count;
		}catch(IllegalStateException e){
			throw new AnalyserException(AnalyserExceptionType.INCORRECT_TYPE, e.getMessage());
		}
		catch(NoSuchFileException e) {
			throw new AnalyserException(AnalyserExceptionType.FILE_NOT_FOUND_TYPE,"No file found");
		}
		catch(RuntimeException e) {
			throw new AnalyserException(AnalyserExceptionType.DELIMITER_OR_HEADER_TYPE,e.getMessage());
		}
	}
}
