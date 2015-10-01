package it.jenyus.environment.fjsp;

import java.util.Set;

import it.jenyus.model.Filter;



public class FJSPFilter implements Filter<FJSPChromosome,Operation> {

	private int matrix[][][];
	private boolean makeAdmissible=true;

	public FJSPFilter(int matrix[][][]) {
		this.matrix=matrix;
	}

	@Override
	public boolean isAdmissible(FJSPChromosome c) {

		Operation[] op=c.getGenes();
		for(int i=0; i<op.length; i++){
			Operation o1=op[i];
		//	if(contains(o1.getTask(), op, true)>1 || contains(o1.getMachine(), op, false)>1)
				return false;
		}
		return true;
	}

	private int contains(int c, Operation[] cc, boolean isTask){
		int n=0;
		for(int i=0; i<cc.length; i++)
		{
		//	if(isTask && cc[i].getTask()==c)
				n++;
		//	else if(!isTask && cc[i].getMachine()==c)
				n++;
		}
		return n;
	}

	@Override
	public void makeAdmissible(FJSPChromosome c) {

		Operation[] op=c.getGenes();
		for(int i=0; i<op.length; i++){
			Operation o1=op[i];
			//if(contains(o1.getTask(), op, true)>1 || contains(o1.getMachine(), op, false)>1){
				op[i]=c.randomGene();
				i--;
			}
		}
	//}

	@Override
	public void setOfAllele(Set<Operation> set) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<Operation> setOfAllele() {
		// TODO Auto-generated method stub
		return null;
	}
}
