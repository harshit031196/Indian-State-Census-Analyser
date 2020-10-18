package com.indianstatescensusanalyser;

import com.opencsv.bean.CsvBindByName;

public class IndianStateCode {
	@CsvBindByName(column = "SrNo")
	private String srno;
	@CsvBindByName(column = "State Name")
	private String stateName;
	@CsvBindByName(column = "TIN")
	private String tin;
	@CsvBindByName(column = "StateCode")
	private String stateCode;
	public String getSrno() {
		return srno;
	}
	public void setSrno(String srno) {
		this.srno = srno;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getStateCode() {
		return stateCode;
	}
	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;
	}
	public String getTin() {
		return tin;
	}
	public void setTin(String tin) {
		this.tin = tin;
	}
}
