package hr.fer.zemris.java.fractals;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import hr.fer.zemris.java.fractals.viewer.IFractalProducer;
import hr.fer.zemris.java.fractals.viewer.IFractalResultObserver;
import hr.fer.zemris.math.Complex;
import hr.fer.zemris.math.ComplexPolynomial;
import hr.fer.zemris.math.ComplexRootedPolynomial;

public class FractalProducer implements IFractalProducer {
	public static final double CONVERGENCE_TRESHOLD = 0.001;
	public static final int NUM_Of_ITERATIONS = 64;
	private static ComplexRootedPolynomial rootedPolynomial;
	private static ComplexPolynomial polynomial;
	ExecutorService pool;
	Complex constant;
	
	
	private int numberOfAvailableProcessors = Runtime.getRuntime().availableProcessors();
	private int noJobs = 8 * numberOfAvailableProcessors;
	
	private static class threadFactory implements ThreadFactory {

		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			thread.setDaemon(true);
			return thread;
		}
		
	}
	
	public FractalProducer(ComplexRootedPolynomial rootedPolynomial){
		
		this.rootedPolynomial = rootedPolynomial;
		polynomial = rootedPolynomial.toComplexPolynom();
		pool = Executors.newFixedThreadPool(numberOfAvailableProcessors, new threadFactory());
	}
	

	/**
	 *  * Method for calculating the fractals using Newton-Raphson
	 * iteration.
	 */
	
	@Override
	public void produce(double reMin, double reMax,	double imMin, double imMax,	int width, int height,long requestNo,
			IFractalResultObserver observer) {
		
		short[] data = new short[width * height];
		List<Future<?>> results = new ArrayList<>();
		int offset;
		
		for(int i = 0; i < noJobs; i++) {
			int yMin = i * noJobs;
			int yMax = (i + 1) * noJobs -1;
			if (i == noJobs -1) {
				yMax = height - 1 ;
			}
			offset = yMin * width;
			
			doJob job = new doJob(data, reMin, reMax, imMin, imMax, width, height, yMin, yMax,offset);
			
			results.add(pool.submit(job));
		}
		for (Future<?> job : results) {
				try {
					job.get();
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}


observer.acceptResult(data, (short) (polynomial.order() + 1), requestNo);

	}
	
	private static class doJob implements Runnable {

		private short data[];
		double reMin;
		double reMax;
		double imMin;
		double imMax;
		int yMin;
		int yMax;
		int width;
		int height;
		int offset;
		
		public doJob(short[] data, double reMin, double reMax,double imMin, double imMax, int width, int height, int yMin, int yMax, int offset) {
			this.data = data;
			this.reMin = reMin;
			this.reMax = reMax;
			this.imMin = imMin;
			this.imMax = imMax;
			this.width = width;
			this.height = height;
			this.yMin = yMin;
			this.yMax = yMax;
			this.offset = offset;			
		}
		@Override
		public void run(){
			ComplexPolynomial derivedPolynomial = polynomial.derive();
			
			for (int y = yMin; y <= yMax; y++) {
				for (int x = 0; x < width; x++) {
					double cre = x / (width - 1.0) * (reMax - reMin) + reMin;
					double cim = (height - 1.0 - y) / (height - 1) * (imMax - imMin) + imMin;
					
					int iter = 0;
					Complex zn = new Complex(cre, cim);
					Complex oldZn;
					Complex numerator;
					Complex denominator;
					Complex fraction;
					double module;
					short index;
					
					while(true) {
						numerator = polynomial.apply(zn);
						denominator = derivedPolynomial.apply(zn);
						fraction = numerator.divide(denominator);
						oldZn = zn.sub(fraction);
						iter++;
						module = zn.sub(oldZn).module();
						zn = oldZn;
						if(module <= CONVERGENCE_TRESHOLD || iter >= NUM_Of_ITERATIONS) {
							break;
						}
						
					}
					
					index = (short) rootedPolynomial.indexOfClosestRootFor(oldZn, CONVERGENCE_TRESHOLD);

					if (index == -1) {
						data[offset++] = 0;
					} else {
						data[offset++] = (short) (index + 1);
					}
				}
		
			}

		}
	}
}
