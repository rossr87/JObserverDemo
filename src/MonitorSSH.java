import java.util.List;
import java.util.ArrayList;

public class MonitorSSH implements MonitorSubject {
	private int alarm_counter = 0;			/* number of alarms associated with this Monitor */
	private String name = "SSH Monitor";
	private List<MonitorObserver> obs_alarms = new ArrayList<MonitorObserver>();

	/*
	 * Alarms will call this method so that the Monitor can become
	 * aware of them.
	 */
	
	@Override
	public void register_observer(MonitorObserver obs_alarm) {
		
		obs_alarms.add(obs_alarm);
		alarm_counter++;
	}
	
	/*
	 * Alarms will call this method so that the Monitor no longer
	 * manipulates those that no longer require it and resources are
	 * released.
	 */
	
	@Override
	public void unregister_observer(MonitorObserver obs_alarm) {
	    int index = obs_alarms.indexOf(obs_alarm);
	    obs_alarms.remove(index);
	    alarm_counter--;
	}
	
	@Override
	public void notify_observers() {
        for (MonitorObserver obs_alarm : obs_alarms) {
        	obs_alarm.down();
        	obs_alarm.up();
        }
	}
	
	public String get_name() {
		return this.name;
	}
	
	public List<MonitorObserver> get_obs_alarms() {
		return this.obs_alarms;
	}
	
	/*
	 * Given a specific email, return a specific alarm
	 */
	public MonitorObserver get_alarm(String email) {
		for (MonitorObserver alarm : obs_alarms) {
			SSHAlarmObserver ssh_alarm = (SSHAlarmObserver)alarm;
			if (email.equals(ssh_alarm.get_email())) {
				return alarm;
			}
		}
		return null;
	}
	
	/*
	 * Returns the number of alarms associated with this monitor.
	 */
	public int get_alarm_counter() {
		return this.alarm_counter;
	}
}
