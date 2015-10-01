package it.jenyus.environment.tsp;

import it.jenyus.model.Chromosome;
import it.jenyus.model.DataSetGenerator;
import it.jenyus.model.GAConfiguration;
import it.jenyus.model.Population;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class TSPDataSetGenerator extends DataSetGenerator{

	private Population population;
	private int chromosomeLength;
	
	public TSPDataSetGenerator(InputStream input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Population generatePopulation(int populationSize,int chromosomeLength) {
		this.chromosomeLength = chromosomeLength;
		init();
		return population;
	}


	public void init(){
		String name=null,dataset=null;
		Character [] genes;
		List<Chromosome<?>> chromosomes= new ArrayList<Chromosome<?>>();
		Scanner sc=null;
		try {
			sc= new Scanner(input);
			int numrow=0;
			while(sc.hasNextLine()){
				String line=sc.nextLine();
				numrow++;
				if(!line.startsWith("#")){
					if(line.contains("name")){
						name=sc.nextLine();
						StringTokenizer t= new StringTokenizer(name,",");
						while(t.hasMoreTokens()){
							t.nextToken();
						}
						numrow++;
					}
					else if(line.contains("DataSet")){
						while(sc.hasNextLine()){
							dataset=sc.nextLine();
							StringTokenizer dd= new StringTokenizer(dataset,",");
							genes= new Character[chromosomeLength];
							int i=0;
							while(dd.hasMoreTokens()){
								String c= dd.nextToken();
								Character c1= c.charAt(0);
								genes[i]=c1;
								i++;
							}
							TSPChromosome chromosome= new TSPChromosome(chromosomeLength);
							chromosome.setGenes(genes);
							chromosomes.add(chromosome);
							numrow++;
						}
						population= new Population(chromosomes);
					}
					else
						continue;
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			sc.close();
		}

	}

}
