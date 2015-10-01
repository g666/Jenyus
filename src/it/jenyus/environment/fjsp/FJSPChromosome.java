package it.jenyus.environment.fjsp;

import java.util.Random;

import it.jenyus.model.Chromosome;

public class FJSPChromosome extends Chromosome<Operation> {

	private Random rnd = new Random();
	private int job;
	private double makespan;
	
	public void setMakespan(double makespan) {
		this.makespan = makespan;
	}
	
	/**
	 * 
	 */
	public FJSPChromosome(int length, int job) {
		super(length);
		this.job = job;
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param genes
	 */
	public FJSPChromosome(Operation[] genes, int job) {
		super(genes);
		this.job = job;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Operation randomGene() {
		// TODO Auto-generated method stub
		int task = rnd.nextInt(3);
		int machine = rnd.nextInt(4);
//		if(job==3 && task==3){
//			task = rnd.nextInt(2)+1;
//		}
		return new Operation(task, job, machine);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		Operation[] o = getGenes();
		StringBuilder s = new StringBuilder();
		for(int i=0; i<o.length; i++){
			//s.append("("+String.valueOf(o[i].getJob())+", "+String.valueOf(o[i].getTask())+", "+String.valueOf(o[i].getMachine()+"),"));
		}
		return s.toString()+" --> "+String.valueOf(makespan);
	}

}
