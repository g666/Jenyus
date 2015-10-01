package it.jenyus.environment.tsp;

import java.util.Random;

import it.jenyus.model.Chromosome;
import it.jenyus.model.GAConfiguration;


public class TSPChromosome extends Chromosome<Character>{

	private Random r = new Random();
	private Character ori, dest;
	
	/**
	 * 
	 */
	public TSPChromosome(Character ori, Character dest) {
		super();
		// TODO Auto-generated constructor stub
		this.ori = ori;
		this.dest = dest;
	}

	/**
	 * @param genes
	 */
	public TSPChromosome(Character[] genes) {
		super(genes);
		// TODO Auto-generated constructor stub
		this.ori = genes[0];
		this.dest = genes[genes.length-1];
	}

	/**
	 * @param length
	 */
	public TSPChromosome(int length, Character ori, Character dest) {
		super(length);
		// TODO Auto-generated constructor stub
		this.ori = ori;
		this.dest = dest;
	}
	
	/**
	 * @param length
	 */
	public TSPChromosome(int length) {
		super(length);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Character randomGene() {
		return Character.valueOf(((char)(r.nextInt(getLength()-1)+65)));
	}

	@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		for(int i=0; i<getGenes().length; i++)
			str.append(geneAt(i));
		return str.toString()+" --> "+(
				(double)((int)(getFitnessValue()*1000))/1000
				)+" --> "+(1/getFitnessValue());
	}

	@Override
	public void setGenes(Object[] genes2) {
		// TODO Auto-generated method stub
		super.setGenes(genes2);
		this.ori = getGenes()[0];
		this.dest = getGenes()[getGenes().length-1];
	}
}
