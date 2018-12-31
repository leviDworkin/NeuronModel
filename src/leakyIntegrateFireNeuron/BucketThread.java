package leakyIntegrateFireNeuron;

public class BucketThread extends Thread {
	private Bucket b;

	public BucketThread(Bucket b) {
		super();
		this.b = b;
	}
	/**
	 * Generates spikes at random by pipes.
	 */
	public void run(){

		while(true) {
			for (Pipe pipe : b.getPipes()) {
				if(pipe.getTrain()==0) {
					double rand = Math.random();
					if (rand>.8) {
						pipe.setSpikeCounter(pipe.getSpikeCounter()+1);
						pipe.setEvent(true);
						pipe.setTrain(pipe.getTrain()+1);
					}
				}else {
					pipe.setSpikeCounter(pipe.getSpikeCounter()+1);
					if(pipe.getpNum()==0 && pipe.getTrain()==6 || pipe.getpNum()==1 && pipe.getTrain()==4) {
						pipe.setTrain(0);
					}else {
						pipe.setTrain(pipe.getTrain()+1);
					}
				}
				
			}
			Pipe.dt++;

			boolean e1 = b.getPipes().get(0).isEvent();
			boolean e2 = b.getPipes().get(1).isEvent();
			//checks coincidence between two events e1 and e2, where e1 is a spike from pipe1 (e2, pipe2)
			if(e1 && e2) {
				System.out.println("\nCoincidence between two dendrites at time interval dt="+Pipe.dt);
				System.out.println("Current time = "+(System.nanoTime()/1000000) );
			}
		
			b.checkStats();
			System.out.println(b.toString());
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
