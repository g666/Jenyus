package it.jenyus.model;

/**
 * Interfaccia di un generatore di popolazioni. 
 *
 */
public interface IGenerator {

	/**
	 * Genera una nuova popolazione.
	 * 
	 * @param populationSize
	 * 		Dimensione della nuova popolazione (intesa come numero di individui).
	 * @param chromosomeLength
	 * 		Lunghezza di un cromosoma.
	 * @return
	 */
	public Population generatePopulation(int populationSize, int chromosomeLength);
}
