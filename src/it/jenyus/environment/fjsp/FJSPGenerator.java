package it.jenyus.environment.fjsp;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import it.jenyus.model.IGenerator;
import it.jenyus.model.Population;

public class FJSPGenerator implements IGenerator {

	@Override
	public Population generatePopulation(int populationSize,
			int chromosomeLength) {
		try {
			BufferedReader buf = new BufferedReader(new InputStreamReader(new FileInputStream("src/it/jenyus/environment/fjsp/fjspconf.txt")));
			String line = buf.readLine();
			while(line!=null){
				if(!line.startsWith("#")){
					
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
