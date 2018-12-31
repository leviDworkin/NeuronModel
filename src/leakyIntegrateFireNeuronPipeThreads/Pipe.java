package leakyIntegrateFireNeuronPipeThreads;

public class Pipe {
	private int weight;
	private int spikeCounter;
	private long spikeTime;
	static int dt;
	private int threadNumber;
	private PipeThread pThread;
	
	public Pipe(int weight) {
		this.weight = weight;
		spikeCounter = 0;
		spikeTime = 0;
		pThread = new PipeThread(this);
	}
//	public Pipe(Pipe other) {
//		weight = other.weight;
//		spikeCounter = other.spikeCounter;
//		spikeTime = other.spikeTime;
//		pThread = other.pThread;
//	}
	
	
	

	public int getThreadNumber() {
		return threadNumber;
	}

	public void setThreadNumber(int threadNumber) {
		this.threadNumber = threadNumber;
	}

	public PipeThread getpThread() {
		return pThread;
	}
	public void setpThread(PipeThread pThread) {
		this.pThread = pThread;
	}

	public long getSpikeTime() {
		return spikeTime;
	}

	public void setSpikeTime(long spikeTime) {
		this.spikeTime = spikeTime;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public double getWeight() {
		return weight;
	}

	public int getSpikeCounter() {
		return spikeCounter;
	}

	public void setSpikeCounter(int spikeCounter) {
		this.spikeCounter = spikeCounter;
	}
	
}
