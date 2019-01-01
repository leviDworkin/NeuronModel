package leakyIntegrateFireNeuron;

import java.util.ArrayList;
/**
 * This class is a bucket with pipes and a leak simulating a neuron with dendrites. 
 * This model is designed for 2 pipes only.
 * @author Levi Dworkin
 *
 */
public class Bucket {
	private double capacity;
	private double currentSize;
	private double leak;
	private double gamma;
	private ArrayList<Pipe> pipes;
	private long startingTime;
	private BucketThread thread;
	/**
	 * Class Constructor
	 * @param capacity: threshold
	 * @param leak: Lets out a little bit of water at each time interval
	 * @param gamma: Spike size
	 * @param numOfPipes: Number of pipes attached to the bucket
	 * @param weights: Array of weights for each pipe according to importance
	 */
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
	 * Starts the clock and runs a BucketThread which is constant running time of the bucket.
	 */
	public void startTime() {
		startingTime = System.nanoTime()/1000000;
		System.out.println("Starting time="+startingTime);
		System.out.println(toString());
		thread.start();
	}
	/**
	 * updates the current amount of water in the bucket, and outputs a spike if
	 * the amount inside passes the threshold
	 */
	public void checkStats() {
		currentSize = sum() - leak*Pipe.dt; //sum() calculates by the spike counter, hence 
		if(currentSize>capacity) {
			System.out.println(toString());
			System.out.println("Spike at "+System.nanoTime()/1000000);
			currentSize = 0;
			Pipe.dt=0;
			System.out.println(currentSizeBucket());
			for (Pipe pipe : pipes) {
				pipe.setSpikeCounter(0);
			}   
		}
		else if (currentSize<0) {
			currentSize=0;
			System.out.println(toString());
		}else {
			System.out.println(toString());
		}
	}
	/**
	 * Sums up the amount of spikes from each pipe according to its weight and spike size.
	 * (In this model the spike size is gamma uniformly)
	 * @returns double
	 */
	private double sum() {
		double sum = 0;
		for (Pipe pipe : pipes) {
			sum = sum + pipe.getWeight()*gamma*pipe.getSpikeCounter(); 
			pipe.setSpiking(false);
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
	public String currentSizeBucket() {
		return "Bucket [capacity=" + (int)capacity + ", currentSize=" + currentSize
				+ ", dt=" + Pipe.dt + "]";
	}
	@Override
	public String toString() {
		String s = "["+"pipe1="+pipes.get(0).getTrain()+", pipe2="+pipes.get(1).getTrain()+"]";
		return "Bucket [capacity=" + (int)capacity + ", currentSize=" + currentSize
				+ ", dt=" + Pipe.dt + "]"+s;
	}
	
	
}
