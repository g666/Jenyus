package it.jenyus.model;

import java.io.InputStream;

public abstract class DataSetGenerator implements IGenerator{

	protected InputStream input;
	protected Population population;

	/**
	 * @param input
	 */
	public DataSetGenerator(InputStream input) {
		super();
		this.input = input;
	}

	public InputStream getInput() {
		return input;
	}
	
	public void setInput(InputStream input) {
		this.input = input;
	}
	
	public void setPopulation(Population population) {
		this.population = population;
	}
	
	public Population getPopulation() {
		return population;
	}
	
}
