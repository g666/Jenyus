package it.jenyus.model;


public interface GeneticOperator {

	public Population execute(Population initialPopulation, int type, Filter<Chromosome<?>,?> filter) throws Exception;
	
	public abstract GAConfiguration getConfiguration();

	public abstract void setConfiguration(GAConfiguration conf);
}
