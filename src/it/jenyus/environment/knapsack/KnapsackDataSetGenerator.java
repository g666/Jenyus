package it.jenyus.environment.knapsack;

import it.jenyus.environment.tsp.DataSetGeneratorException;
import it.jenyus.model.Chromosome;
import it.jenyus.model.DataSetGenerator;
import it.jenyus.model.Population;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class KnapsackDataSetGenerator extends DataSetGenerator{

	public KnapsackDataSetGenerator(InputStream input) {
		super(input);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Population generatePopulation(int populationSize,int chromosomeLength) {
		// TODO Auto-generated method stub
		population = new Population(populationSize);
		List<Chromosome<?>> li = new ArrayList<Chromosome<?>>();
		BufferedReader buf = new BufferedReader(new InputStreamReader(input));
		try {
			int num = 0;
			String line = buf.readLine();
			while(line!=null){
				num++;

				if(!line.startsWith("#") && line.length()>0){

					StringTokenizer tok = new StringTokenizer(line, ":");
					if(tok.countTokens()==2){
						String start = tok.nextToken();
						String end = tok.nextToken().replaceAll(" ", "");
						try {
//							if(start.equals("f")){
//								filleArray(function, end, "-");
//							}else if(start.equals("v")){
//								filleArray(constraints, end, "-");
//							}else if(start.equals("w")){
//								filleArray(weigths, end, "-");
//							}else if(start.equals("b")){
//								capacity = Integer.parseInt(end);
//							}else 
								if(start.equals("d")){
								Boolean[] gene = new Boolean[chromosomeLength];
								filleArray(gene, end, ",");
								KnapsackChromosome ch = new KnapsackChromosome(gene);
								li.add(ch);
							}

						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							throw new DataSetGeneratorException("Incorrect syntax", num);
						}
					}
					//						else
					//							throw new DataSetGeneratorException("Incorrect syntax", num);
				}
				line = buf.readLine();
			}
			buf.close();
			population.setIndividuals(li);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return population;
	}

//	private void filleArray(int[] a, String line, String delimiter) throws Exception{
//		StringTokenizer tok = new StringTokenizer(line, delimiter);
//		int i=0;
//		while(tok.hasMoreElements()){
//			String token = tok.nextToken();
//			try {
//				int n = Integer.parseInt(token);
//				a[i]=n;
//				i++;
//			} catch (NumberFormatException e) {
//				// TODO: handle exception
//				throw new NumberFormatException(token+" cannot be formatted.");
//			}
//		}
//	}

	private void filleArray(Boolean[] a, String line, String delimiter) throws Exception{
		StringTokenizer tok = new StringTokenizer(line, delimiter);
		int i=0;
		while(tok.hasMoreElements()){
			String token = tok.nextToken();
			if(token.equals("1"))
				a[i]=true;
			else if(token.equals("0"))
				a[i]=false;
			else
				throw new IllegalArgumentException(token+" cannot be formatted.");
			i++;
		}
	}


	public static void main(String[] args) throws Exception{
		FileInputStream f = new FileInputStream(new File("C:/Users/Mentalist/Documents/Dropbox_666/Dropbox/MapYou/Ricerca Operativa/" +
				"Progetto AG/Jenyus/src/it/jenyus/environment/knapsack/knapsackconf.txt"));
		KnapsackDataSetGenerator k = new KnapsackDataSetGenerator(f);
		k.generatePopulation(0, 5);
		
		System.out.println();
		for(Chromosome<?> c: k.getPopulation().getIndividuals())
			System.out.println(c);
	}
}
