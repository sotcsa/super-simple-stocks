/**
 * Simulates the trading system.
 * 
 * @author Csaba Soti
 */
public class TradeSimulator implements Runnable {

	@Override
	public void run() {
		System.out.println("TradeSimulator running, name: " + Thread.currentThread().getName());		
	}

}
