package it.jenyus.environment.tsp;

import it.jenyus.environment.knapsack.KnapsackDataSetGenerator;
import it.jenyus.environment.knapsack.KnapsackRandomGenerator;
import it.jenyus.model.GeneticAlgorithm;
import it.jenyus.model.IGenerator;
import it.jenyus.ui.console.Command;
import it.jenyus.ui.console.Console;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TSPGA_Console extends Console{

	private TSPProblem problem;
	private IGenerator generator;
	private boolean isManualMode;
	private boolean isRandomGenerator;
	private File file;
	private Character ori, dest;
	
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "Problema del commesso viaggiatore";
	}

	@Override
	public List<Command> getCommands() {
		// TODO Auto-generated method stub
		List<Command> c = new ArrayList<Command>();
		c.add(new Command("file", "Choose tsp configuration file", "-f", "setPar"));
		c.add(new Command("syntax", "Show file syntax", "-sy", "syntax"));
		c.add(new Command("gen", "Choose population generator method (r=random generator, otherwise will be a dataset generator with source specified by -f option)", "-g", "setGen"));
		c.add(new Command("manual", "Choose manual input mode (y/n)", "-m", "mode"));
		c.add(new Command("ori", "Choose origin node", "-o", "ori"));
		c.add(new Command("dest", "Choose destination node", "-d", "dest"));
		return c;
	}
	
	public void ori(Command cmd, String s){
		ori = s.charAt(0);
		cmd.setDefaultValue(String.valueOf(ori.charValue()));
	}
	
	public void dest(Command cmd, String s){
		dest = s.charAt(0);
		cmd.setDefaultValue(String.valueOf(dest.charValue()));
	}
	
	public void setGen(Command cmd, String s){
		if(s.equals("r"))
			isRandomGenerator=true;
		else isRandomGenerator=false;
		cmd.setDefaultValue(String.valueOf(isRandomGenerator));
	}
	
	public void mode(Command cmd, String s){
		if(s.equals("y"))
			isManualMode = true;
		else
			isManualMode = false;
		cmd.setDefaultValue(String.valueOf(isManualMode));
	}
	
	public void syntax(Command cmd, String s){
		BufferedReader buf = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/it/jenyus/environment/tsp/tspSyntax.txt")));
		try {
			String line = buf.readLine();
			while(line!=null)
			{
				System.out.println(line);
				line = buf.readLine();
			}
			buf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setPar(Command cmd, String p){
		try {
			file = new File(p);
			problem = new TSPProblem(conf, isManualMode?System.in:new FileInputStream(file));
			cmd.setDefaultValue(file.getPath());
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
	}

	@Override
	public GeneticAlgorithm algorithm() {
		// TODO Auto-generated method stub
		if(isRandomGenerator)
			generator = new TSPRandomGenerator(conf.getCHROMOSOME_LENGTH());
		else
			try {
				generator = new TSPDataSetGenerator(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return new TSPGA(generator, conf.getNUM_OF_ITERATIONS(), problem, conf, ori, dest);
	}

}
