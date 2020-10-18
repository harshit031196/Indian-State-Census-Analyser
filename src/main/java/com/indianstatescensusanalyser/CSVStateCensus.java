package com.indianstatescensusanalyser;

import java.io.IOException;
import java.io.Reader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;


import com.opencsv.CSVReader;

public class CSVStateCensus {

	private static final String STRING_READ_SAMPLE = "IndiaStateCensusData - IndiaStateCensusData.csv";
	public enum IOStateCensusAnalyser {
		CONSOLE_IO, FILE_IO, DB_IO, REST_IO
	}
	public int readFromCSVFile(IOStateCensusAnalyser ioService) throws IOException {
		if(ioService.equals(IOStateCensusAnalyser.FILE_IO)) {
			int count = this.readToCSV();
			return count;
		}
		return 0;
	}

	public int readToCSV() throws IOException{
		List<IndiaStateCensusData> data = new ArrayList<IndiaStateCensusData>();
		int count=0;
		try(Reader reader = Files.newBufferedReader(Paths.get(STRING_READ_SAMPLE));
				CSVReader csvReader = new CSVReader(reader);
				){
			String [] nextRecord;
			boolean flag=false;
			while((nextRecord=csvReader.readNext())!=null) {
				if(!flag) {flag=true;continue;}
				data.add(new IndiaStateCensusData(nextRecord[0],nextRecord[1],nextRecord[2],nextRecord[3]));
				count++;
			}
		}
		System.out.println(data);
		return count;
	}
}
