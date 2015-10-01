package it.jenyus.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Population {

	private List<Chromosome<?>> individuals;
	private double totalFitnessValue;
	private Comparator<Chromosome<?>> comp= new Comparator<Chromosome<?>>() {
		@Override
		public int compare(Chromosome<?> o1, Chromosome<?> o2) {

			return (int) ((o1.getFitnessValue()*1000)-(o2.getFitnessValue()*1000));
		}
	};;
	
	public Population(int size){
		individuals = new ArrayList<Chromosome<?>>(size);
	}
	
	public Population(List<Chromosome<?>> individuals) {
		super();
		this.individuals = individuals;
	}
	
	public double getTotalFitnessValue() {
		totalFitnessValue=0;
		for(int i=0; i<individuals.size(); i++)
		{
			Chromosome<?> g = individuals.get(i);
			totalFitnessValue+=g.getFitnessValue();
		}
		return totalFitnessValue;
	}
	
	public void setTotalFitnessValue(double totalFitnessValue) {
		this.totalFitnessValue = totalFitnessValue;
	}

	public int getSize(){
		return individuals.size();
	}
	
	public Population() {
		individuals = new ArrayList<Chromosome<?>>();
	}

	public List<Chromosome<?>> getIndividuals() {
		return individuals;
	}
	
	
	public void setIndividuals(List<Chromosome<?>> li) {
		this.individuals = li;
	}
	
	public void evaluatePopulation(FitnessFunction<Chromosome<?>> fitnessFunction){
		if(fitnessFunction!=null){
			for(int i=0; i<individuals.size(); i++){
				Chromosome<?> c = individuals.get(i);
				c.setFitnessValue(fitnessFunction.evaluate(c));
			}
		}
	}
	
	public Chromosome<?> getBestIndividual(){
		Chromosome<?> best = individuals.get(0);
		for(int i=1; i<individuals.size(); i++){
			Chromosome<?> c = individuals.get(i);
			if(c.getFitnessValue()>best.getFitnessValue())
				best = c;
		}
		return best;
	}
	
	public void normalizeFitnessValues() {
		totalFitnessValue = getTotalFitnessValue();
		for(int i=0; i<individuals.size(); i++)
		{
			Chromosome<?> g = individuals.get(i);
			g.setFitnessNormalizedValue(g.getFitnessValue()/totalFitnessValue);
		}
		
		Collections.sort(individuals, comp);

	}

}
