package com.indianstatescensusanalyser;

import java.io.IOException;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;
import com.indianstatescensusanalyser.CSVStateCensus.IOStateCensusAnalyser;
public class StateAnalyserTest extends TestCase {
	@Test
	public void test() throws IOException {
		CSVStateCensus stateCensus = new CSVStateCensus();
		int count=stateCensus.readFromCSVFile(IOStateCensusAnalyser.FILE_IO);
		Assert.assertEquals(29, count);
	}
}
