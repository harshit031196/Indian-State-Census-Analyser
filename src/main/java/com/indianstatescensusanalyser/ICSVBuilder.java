package com.indianstatescensusanalyser;

import java.io.Reader;
import java.util.Iterator;

public interface ICSVBuilder<E> {
	public Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath)
			throws AnalyserException;
}
