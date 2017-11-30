import java.util.Objects;

/*
 * These are all concrete Observers. There are many of them and they have
 * lots in common, ergo, it would be beneficial to define this commonalities
 * inside an abstract class.
 */

public class SSHAlarmObserver implements MonitorObserver {
	public String alarm_name;			/* e.g. SSH Alarm  */
	public String email;				
	public String sms;					/* Mobile/Cell number */
	public String notification;			/* e.g. Site is down! */
	
	SSHAlarmObserver(String email, String sms) {
		this.email = email;
		this.sms = sms;
	}
	
	@Override
	public boolean equals(Object o) {

        if (o == this) {
	        return true;
	    }
	         
        if (!(o instanceof SSHAlarmObserver))
            return false;

	    /*
	     * Downcast so we can treat like a MonitorObserver.
	     */ 
	    SSHAlarmObserver obs_alarm = (SSHAlarmObserver) o;
	    
	    return (
	    		Objects.equals(alarm_name, obs_alarm.get_name()) &&
	    		Objects.equals(email, obs_alarm.get_email()) &&
	    		Objects.equals(sms, obs_alarm.get_sms()) &&
	    		Objects.equals(notification, obs_alarm.get_notification())
	    		);
	     
	}
	
	public String get_name() {
		return this.alarm_name;
	}
	
	public String get_email() {
		return this.email;
	}
	
	public String get_sms() {
		return this.sms;
	}
	
	public String get_notification() {
		return this.notification;
	}
	
	public void down() {
		System.out.println("SSH is Down!");
	}
	public void up() {
		System.out.println("SSH is Up!");
	}
}
