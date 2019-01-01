package leakyIntegrateFireNeuron;
/**
 * This class simulates a dendrite.
 * @author Levi Dworkin
 *
 */
public class Pipe {
	private int weight, spikeCounter, train;
	private boolean event, spiking;
	static int dt;
	private int pNum;
	
	public Pipe(int weight) {
		this.weight = weight;
		spikeCounter = 0;
		event = false;
		pNum = 0;
		train=0;
		spiking = false;
	}
		
	public int getTrain() {
		return train;
	}

	public void setTrain(int train) {
		this.train = train;
	}

	public int getpNum() {
		return pNum;
	}

	public void setpNum(int pNum) {
		this.pNum = pNum;
	}

	public boolean isSpiking() {
		return spiking;
	}

	public void setSpiking(boolean spiking) {
		this.spiking = spiking;
	}

	public boolean isEvent() {
		if(pNum==0 && train==6 || pNum==1 && train==4) {
			event = true;
		}else {
			event = false;
		}
		return event;
	}


	public void setEvent(boolean event) {
		this.event = event;
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
