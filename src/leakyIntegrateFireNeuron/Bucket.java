package leakyIntegrateFireNeuron;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;

public class Bucket {
	private double capacity;
	private double currentSize;
	private double leak;
	private double gamma;
	private ArrayList<Pipe> pipes;
	private long startingTime;
	private BucketThread thread;
	
	public Bucket(double capacity, double leak, double gamma, int numOfPipes, int[] weights) {
		super();
		currentSize = 0;
		this.capacity = capacity;
		this.leak = leak;
		this.gamma = gamma;
		pipes = new ArrayList<>(numOfPipes);
		for(int i=0; i<numOfPipes; i++) {
			Pipe p = new Pipe(weights[i]);
			p.setpNum(i);
			pipes.add(p);
		}
		Pipe.dt = 0;
		thread = new BucketThread(this);
	}
	/**
	 * Starts the clock
	 */
	public void startTime() {
		startingTime = System.nanoTime()/1000000;
		System.out.println("Starting time="+startingTime);
		System.out.println(toString());
		thread.start();
	}
	/**
	 * updates the current amount of water in the bucket, and outputs a spike if
	 * the amount inside passed the threshold
	 */
	public void checkStats() {
		currentSize = sum() - leak*Pipe.dt; //sum() calculates by the spike counter, hence 
		if(currentSize>capacity) {
			System.out.println(toString());
			System.out.println("Spike at "+System.nanoTime()/1000000);
			currentSize = 0;
			Pipe.dt=0;
			for (Pipe pipe : pipes) {
				pipe.setSpikeCounter(0);
			}   
		}
		if (currentSize<0) {
			currentSize=0;
		}
	}
	private double sum() {
		double sum = 0;
		for (Pipe pipe : pipes) {
			sum = sum + pipe.getWeight()*gamma*pipe.getSpikeCounter(); 
			pipe.setEvent(false);
		}
		return sum;
	}
	
	public double getCapacity() {
		return capacity;
	}
	public void setCapacity(double capacity) {
		this.capacity = capacity;
	}
	public double getCurrentSize() {
		return currentSize;
	}
	public void setCurrentSize(double currentSize) {
		this.currentSize = currentSize;
	}
	public double getLeak() {
		return leak;
	}
	public void setLeak(double leak) {
		this.leak = leak;
	}
	public double getGamma() {
		return gamma;
	}
	public void setGamma(double gamma) {
		this.gamma = gamma;
	}
	public ArrayList<Pipe> getPipes() {
		return pipes;
	}
	public void setPipes(ArrayList<Pipe> pipes) {
		this.pipes = pipes;
	}
	public long getStartingTime() {
		return startingTime;
	}
	public void setStartingTime(long startingTime) {
		this.startingTime = startingTime;
	}

	@Override
	public String toString() {
		String s = "["+"pipe1="+pipes.get(0).getTrain()+", pipe2="+pipes.get(1).getTrain()+"]";
		return "Bucket [capacity=" + (int)capacity + ", currentSize=" + currentSize
				+ ", dt=" + Pipe.dt + "]"+s;
	}
	
	
}
