package it.jenyus.model;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ParentSelector implements ISelector {

	@Override
	public Population select(Population actualPopulation) throws Exception {
		actualPopulation.normalizeFitnessValues();

		List<Chromosome<?>> newpopulation = actualPopulation.getIndividuals();
		Random rnd = new Random();
		List<Chromosome<?>> newPop = new ArrayList<Chromosome<?>>();
		for(int i=0; i<2; i++){
			double prob = ((double)(rnd.nextInt(100)+1))/100;
			Chromosome<?> g = getFirstOverThreshold(prob, newpopulation);
			if(g!=null && !newPop.contains(g)){
				newpopulation.remove(g);
				newPop.add(g);
			}else continue;

		}

		return new Population(newPop);
	}
	
	private Chromosome<?> getFirstOverThreshold(double threshold, List<Chromosome<?>> l){
		for(int i=0; i<l.size(); i++){
			Chromosome<?> g = l.get(i);
			if(g.getFitnessNormalizedValue()>threshold)
				return g;
		}
		return l.get(l.size()-1);
	}

}
