/*
 * Purpose:
 * 
 * To declare the capabilities of a Monitoring Subject Object so that:
 * 	1. All Monitoring Subjects Objects that conform to such capabilities
 * 		can be manipulated in a uniform way.
 * 	2. Observer Objects/Listeners can hold a reference any of these Objects and
 * 		have such references changed during runtime.
 */

public interface MonitorSubject {
	public void register_observer(MonitorObserver notify);
	public void unregister_observer(MonitorObserver notify);
}
