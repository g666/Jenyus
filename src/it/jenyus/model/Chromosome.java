package it.jenyus.model;


/**
 * Generico cromosoma.
 * 
 * Con riferimento all'evoluzione naturale, esso viene paragonato ad un individuo.
 * Il motivo è nella conseguente riduzione di overhead.
 * 
 * Il parametro <i>T</i> rappresenta un qualsiasi tipo di gene.
 *
 */
public abstract class Chromosome<T> implements IChromosome<T> {

	private T[] genes;
	private double fitnessValue, fitnessNormalizedValue;
	
	// Lunghezza del cromosoma.
	private int length;
	
	/**
	 * 
	 */
	public Chromosome() {
		super();
	}

	public Chromosome(T[] genes) {
		super();
		this.genes = genes;
		this.length = genes.length;
	}
	
	public Chromosome(int length) {
		super();
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}

	public double getFitnessValue() {
		return fitnessValue;
	}
	
	public void setFitnessValue(double fitnessValue) {
		this.fitnessValue = fitnessValue;
	}
	
	public double getFitnessNormalizedValue() {
		return fitnessNormalizedValue;
	}
	
	public void setFitnessNormalizedValue(double fitnessNormalizedValue) {
		this.fitnessNormalizedValue = fitnessNormalizedValue;
	}
	
	@Override
	public T geneAt(int pos) {
		// TODO Auto-generated method stub
		if(genes.length>pos && pos>=0)
			return genes[pos];
		else
			return null;
	}
	
	@Override
	public void changeGene(int pos, T newGene) {
		// TODO Auto-generated method stub
		if(genes.length>pos && pos>0)
			genes[pos] = newGene;	
	}
	
	@Override
	public T[] getGenes() {
		// TODO Auto-generated method stub
		return genes;
	}
	
	@SuppressWarnings("unchecked")
	public void setGenes(Object[] genes2) {
		if(genes2.length==length)
			this.genes = (T[]) genes2;
		else
			throw new IllegalArgumentException("Incorrect value of new chromosome length.");
	}
}
