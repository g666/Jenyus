package it.jenyus.environment.fjsp;

public class Operation {

	private int machine1, job, machine2;

	/**
	 * @param task
	 * @param job
	 * @param machine
	 * @param cost
	 */
	public Operation(int job, int machine1, int machine2) {
		super();
		this.job = job;
		this.machine1 = machine1;
		this.machine2 = machine2;
	}

	public int getMachine1() {
		return machine1;
	}
	public int getMachine2() {
		return machine2;
	}
	public void setMachine1(int machine1) {
		this.machine1 = machine1;
	}
	public void setMachine2(int machine2) {
		this.machine2 = machine2;
	}
	/**
	 * @return the job
	 */
	public final int getJob() {
		return job;
	}

	/**
	 * @param job the job to set
	 */
	public final void setJob(int job) {
		this.job = job;
	}

	/**
	 * @return the machine
	 */

	
}
