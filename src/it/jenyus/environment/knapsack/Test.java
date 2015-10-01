package it.jenyus.environment.knapsack;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		FileOutputStream fos = new FileOutputStream("it/jenyus/environment/knapsack/test.txt");
		FileOutputStream buf = new FileOutputStream(new File("C:/Users/Mentalist/Documents/Magistrale/RicercaOperativa/Knapsack/knapsack2.mos"));
		
		buf.write(("model ModelName \n" +
				"uses \"mmxprs\"; !gain access to the Xpress-Optimizer solver \n\n" +
				"!sample declarations section \n" +
				"declarations \n").getBytes());
		
		Random r = new Random();
		StringBuilder f = new StringBuilder();
		StringBuilder v = new StringBuilder();
		f.append("f:");
		v.append("v:");
		for(int i=0; i<100; i++){
			buf.write(("x"+i+", ").getBytes());
		}
		buf.write((": mpvar\n" +
				"b=235 \n" +
				"end-declarations\n\nmaxf:= ").getBytes());
		StringBuilder bbb = new StringBuilder();
		for(int i=0; i<100; i++){
			int fi = r.nextInt(100)+10;
			int vi = r.nextInt(100)+10;
			buf.write((String.valueOf(fi)+"*x"+i+" + ").getBytes());
			bbb.append(String.valueOf(vi)+"*x"+i+" + ");
			if(i<99){
				f.append(String.valueOf(fi)+"-");
				v.append(String.valueOf(vi)+"-");
			}else{
				f.append(String.valueOf(fi));
				v.append(String.valueOf(vi));				
			}
		}
		fos.write((f.toString()+"\n"+v.toString()+"\nb:235").getBytes());
		fos.flush();fos.close();
		buf.write(("\n\nx666:= "+bbb.toString()+"\nv1:=x666<=b\n\n").getBytes());
		for(int i=0; i<100; i++){
			buf.write(("x"+i+" is_binary\n").getBytes());
		}
		buf.write(("maximize(maxf)\n\nwriteln(\"Function value: \"+getobjval)\n" +
				"writeln(\"Capacity: \"+getsol(x666))\n" +
				"!...\n" +
				"writeln(\"End running model\")\n" +
				"end-model\n").getBytes());
		buf.close();
	}

}
