package it.jenyus.model;

public interface FitnessFunction<T extends Chromosome<?>> {

	public double evaluate(T c);

}
