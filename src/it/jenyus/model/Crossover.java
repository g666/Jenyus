package it.jenyus.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Crossover implements GeneticOperator {

	private ISelector selector;
	private GAConfiguration conf;
	private Random rnd;

	public Crossover() {
		selector=new ParentSelector();
		rnd = new Random();
	}

	@Override
	public GAConfiguration getConfiguration() {
		return conf;
	}

	@Override
	public void setConfiguration(GAConfiguration conf) {
		this.conf = conf;
	}

	@Override
	public Population execute(Population initialPopulation, int type,
			Filter<Chromosome<?>,?> filter) throws Exception {
		List<Chromosome<?>> nl = new ArrayList<Chromosome<?>>();
		int a = initialPopulation.getSize()/2;

		for(int i=0; i<a; i++){
			Population p = selector.select(initialPopulation);
			List<Chromosome<?>> l = p.getIndividuals();
			if(type==GAConfiguration.POINT_CROSSOVER)
				nl.addAll(applyCrossover(l.get(0), l.get(1), filter));
			else nl.addAll(applyUniformCrossover(l.get(0), l.get(1), filter));
		}
		return new Population(nl);
	}

	private List<Chromosome<?>> applyCrossover(Chromosome<?> chromosome, Chromosome<?> chromosome2,
			Filter<Chromosome<?>,?> filter) {
		List<Chromosome<?>> nl = new ArrayList<Chromosome<?>>();
		//simpleT
		Object[] genes1= chromosome.getGenes();
		Object[] genes2= chromosome2.getGenes();

		if(genes1.length>=conf.getEND_POINT_CROSSOVER() &&
				genes1.length>conf.getSTRAT_POINT_CROSSOVER() &&
				genes2.length>=conf.getEND_POINT_CROSSOVER() &&
				genes2.length>conf.getSTRAT_POINT_CROSSOVER()){
			for(int i=conf.getSTRAT_POINT_CROSSOVER(); i<conf.getEND_POINT_CROSSOVER(); i++){
				Object t = genes2[i];
				genes2[i]=genes1[i];
				genes1[i]=t;
			}
			chromosome.setGenes(genes1);
			chromosome2.setGenes(genes2);
			if(filter!=null){
				if(filter.isAdmissible(chromosome))
					nl.add(chromosome);
				else{
					filter.makeAdmissible(chromosome);
					nl.add(chromosome);
				}
				if(filter.isAdmissible(chromosome2))
					nl.add(chromosome2);
				else{
					filter.makeAdmissible(chromosome2);
					nl.add(chromosome2);
				}
			}
		}

		return nl;

	}


	private List<Chromosome<?>> applyUniformCrossover(Chromosome<?> c1,Chromosome<?> c2,
			Filter<Chromosome<?>,?> filter){

		List<Chromosome<?>> nl = new ArrayList<Chromosome<?>>();
		Object[] genes1= c1.getGenes();
		Object[] genes2= c2.getGenes();
		boolean[] mask= new boolean[genes1.length];
		for(int i=0; i<mask.length; i++){
			mask[i] = rnd.nextBoolean();
		}

		for(int i=0; i<mask.length; i++){
			boolean m= mask[i];
			Object g1 = genes1[i];
			Object g2 = genes2[i];
			if(m){
				genes1[i]=g1;
				genes2[i]=g2;
			}
			else{
				genes1[i]=g2;
				genes2[i]=g1;
			}
		}
		c1.setGenes(genes1);
		c2.setGenes(genes2);

		if(filter!=null){
			if(filter.isAdmissible(c1))
				nl.add(c1);
			else 
			{
				filter.makeAdmissible(c1);
				nl.add(c1);
			}
			if(filter.isAdmissible(c2))
				nl.add(c2);
			else 
			{
				filter.makeAdmissible(c2);
				nl.add(c2);
			}
		}

		return nl;

	}

}
