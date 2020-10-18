package com.indianstatescensusanalyser;

public class IndiaStateCensusData {

	private String state;
	private String population;
	private String areaInSqKm;
	private String densityPerSqKm;
	public IndiaStateCensusData(String state, String population, String areaInSqKm, String densityPerSqKm) {
		this.state=state;
		this.population=population;
		this.areaInSqKm=areaInSqKm;
		this.densityPerSqKm=densityPerSqKm;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getAreaInSqKm() {
		return areaInSqKm;
	}
	public void setAreaInSqKm(String areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}
	public String getDensityPerSqKm() {
		return densityPerSqKm;
	}
	public void setDensityPerSqKm(String densityPerSqKm) {
		this.densityPerSqKm = densityPerSqKm;
	}
	
	@Override
	public String toString() {
		return "State =" + this.getState() + ",population =" + this.getPopulation() + ",area in sq km =" + this.getAreaInSqKm()+ ",density in sq km =" + this.getDensityPerSqKm();
	}
}
