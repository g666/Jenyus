package it.jenyus.model;



public interface ISelector {

	public Population select(Population actualPopulation) throws Exception;
}
