package leakyIntegrateFireNeuronPipeThreads;

public class BucketThread extends Thread {
	private Bucket b;

	public BucketThread(Bucket b) {
		super();
		this.b = b;
	}

	public void run(){
		for (Pipe pipe : b.getPipes()) {
			pipe.getpThread().start();
		}
		while(true) {
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
