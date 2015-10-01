package it.jenyus.model;

/**
 * Interfaccia di un cromosoma.
 * 
 * Il parametro <i>T</i> rappresenta un qualsiasi tipo di gene.
 *
 */
public interface IChromosome<T extends Object> {

	/**
	 * 
	 * @return
	 * 		Un array contenente tutti i geni del cromosoma.
	 */
	public T[] getGenes();
	
	/**
	 * 
	 * @param pos
	 * 		Posizione del gene all'interno del cromosoma.
	 * 		L'insieme dei geni è un array e, quindi, <i>pos</i> può variare da 0 a lunghezza dell'array -1.
	 * @return
	 * 		Restituisce il gene in posizione <i>pos</i>.
	 */
	public T geneAt(int pos);
	
	/**
	 * 
	 * @return
	 * 		Restituisce un gene in maniera randomica.
	 */
	public T randomGene();
	
	/**
	 * Modifica il gene in una determinata posizione.
	 * 
	 * @param pos
	 * 		La posizione del gene all'interno del cromosoma.
	 * @param newGene
	 * 		Il nuovo gene da sostituire con quello presente in posizione <i>pos</i>.
	 */
	public void changeGene(int pos, T newGene);
}
