/*
 * Purpose:
 * 
 * To declare the capabilities of a Monitoring Observer Object so that:
 * 	1. All Monitoring Observer Objects that conform to such capabilities
 * 		can be manipulated in a uniform way. 
 * 		e.g.: Any MonitorSubject Object can call on any compliant
 * 			  MonitorObserver subject in the same way. e.g. Alert(Red)
 * 
 * 	2. Observer Objects/Listeners can hold a reference to any of these Objects and
 * 		have such references changed during runtime.
 * 		e.g: A MonitorObserver can be removed, added
 */
public interface MonitorObserver {
	public void down();				/* when a monitor indicates loss of service */
	public void up();				/* when a monitor indicates service restored */
}
