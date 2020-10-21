package com.indianstatescensusanalyser.csvbuilder;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<E> {
	public Iterator<E> getCSVFileIterator(Reader reader, Class<E> csvClass, String filePath)
			throws AnalyserException;
	public List<E> getCSVFileList(Reader reader, Class<E> csvClass, String filePath) throws AnalyserException;
}
