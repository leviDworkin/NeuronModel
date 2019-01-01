package leakyIntegrateFireNeuron;
/**
 * This class is a thread and the life of a bucket.
 * It runs iterations of time intervals of 100 milliseconds.
 * @author Levi Dworkin
 *
 */
public class BucketThread extends Thread {
	private Bucket b;

	public BucketThread(Bucket b) {
		super();
		this.b = b;
	}
	/**
	 * Generates spikes by each pipe at random. There'a a 20% chance for each pipe to spike.
	 * If pipe1 spikes it will then produce a spike train of 5 consecutive spikes over 5 time intervals.
	 * The same goes for pipe2 but only 3 consecutive spikes.
	 * If both pipes spike at the same time interval the program will output a coincidence.
	 */
	public void run(){

		while(true) {
			for (Pipe pipe : b.getPipes()) {
				if(pipe.getTrain()==0) {
					double rand = Math.random();
					if (rand>.8) {
						pipe.setSpikeCounter(pipe.getSpikeCounter()+1);
						pipe.setSpiking(true);
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
			//checks coincidence between two events e1 and e2, where e1 is a spike from pipe1,
			//and e2 from pipe2
		
			b.checkStats();
			
			if(e1 && e2) {
				System.out.println("\nCoincidence between two dendrites at time interval dt="+Pipe.dt);
				System.out.println("Current time = "+(System.nanoTime()/1000000) );
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

}
