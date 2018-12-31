package leakyIntegrateFireNeuronPipeThreads;

public class PipeThread extends Thread {
	private Pipe pipe;

	public PipeThread(Pipe p) {
		pipe = p;
	}
	public void run() {
		while(true) {
			
			double rand = Math.random();
			if (rand>.8) {
				pipe.setSpikeCounter(pipe.getSpikeCounter()+1);
				pipe.setSpikeTime(System.nanoTime()/1000000);
			}
			try {
				synchronized(this){
				      Pipe.dt++;
//				      System.out.println("Thread "+pipe.getThreadNumber()+": "+Pipe.dt);
				    }
				
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		
	}
}
