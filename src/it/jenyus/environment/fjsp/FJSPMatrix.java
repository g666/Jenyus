package it.jenyus.environment.fjsp;

import java.util.Random;

public class FJSPMatrix {
	
	public static int job=5;
	public static int machine1=1;
	public static int machine2=1;
	public static int matrix [][][]= new int [job][machine1] [machine2];
	
	public static void main(String[] args) {
		
		Random r= new Random();
		
		for(int i=0; i<job; i++)
			for(int j=0; j<machine1; j++)
				for(int z=0; z<machine2; z++)
					matrix[i][j][z]=r.nextInt(4)+2;
		
		for(int i=0; i<job; i++)
			for(int j=0; j<machine1; j++)
				for(int z=0; z<machine2; z++)
					System.out.println(matrix[i][j][z]);
	}
}
