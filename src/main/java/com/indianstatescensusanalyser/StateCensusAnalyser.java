package com.indianstatescensusanalyser;
import java.io.IOException;

import com.indianstatescensusanalyser.CSVStateCensus.IOStateCensusAnalyser;
public class StateCensusAnalyser {
	public static void main(String []args) throws IOException {
		CSVStateCensus stateCensus = new CSVStateCensus();
		stateCensus.readFromCSVFile(IOStateCensusAnalyser.FILE_IO);
	}
}
