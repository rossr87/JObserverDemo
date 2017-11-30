/*
 * This is the entry point for the Monitoring System.
 * Here the 'Core' is instantiated and initialized.
 */

public class MonitorEntry {
	
	public static void main(String[] args) {
		/*
		 * Committing the sin of programming to an Implementation and 
		 * not an Interface!
		 */
		MonitorCore core = new MonitorCore();
		core.core_launch();
	}
}
