package it.jenyus.environment.tsp;

import it.jenyus.model.RandomGenerator;

public class TSPRandomGenerator extends RandomGenerator<TSPChromosome> {

	public TSPRandomGenerator(int chromosomeLength) {
		super(chromosomeLength);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TSPChromosome generateChromosome() {

		Character[] gene = new Character[chromosomeLength];
		TSPChromosome o= new TSPChromosome(chromosomeLength);
		for(int i=0; i<5; i++)
		{
			boolean done = false;
			while(!done){
				Character crt = o.randomGene();
				if(!contains(crt, gene))
				{
					gene[i] = crt;
					break;
				}
			}
			//			gene[i] = o.randomGene();
		}
		gene[gene.length-1]=gene[0];
		o.setGenes(gene);
		return o;
	}

	private boolean contains(Character c, Character[] cc){
		for(int i=0; i<cc.length; i++)
			if(cc[i]==c)
				return true;
		return false;
	}

}
