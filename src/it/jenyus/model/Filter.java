package it.jenyus.model;

import java.util.Set;


public interface Filter<T extends Chromosome<Y>, Y>{

	public boolean isAdmissible(T c);
	
	public void makeAdmissible(T c);
	
	public void setOfAllele(Set<Y> set);
	
	public Set<Y> setOfAllele();
}
