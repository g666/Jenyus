package it.jenyus.environment.fjsp;

import java.util.Random;

import it.jenyus.model.GAConfiguration;
import it.jenyus.model.Problem;

public class FJSProblem extends Problem{

	public FJSProblem(GAConfiguration conf) {
		super(conf);
		// TODO Auto-generated constructor stub
	}

	private int [][][] matrix;
	
	public int[][][] getMatrix() {
		return matrix;
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		matrix = new int[3][3][4];
		Random r = new Random();
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<4; k++){
					matrix[i][j][k]=r.nextInt(20)+2;
				}
			}
		}
		System.out.println();
	}

}
