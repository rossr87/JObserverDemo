import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
 * This is the "Core" part of the Monitoring System that:
 * 
 * - Contains all the Monitors. Remember a Monitor is a Subject in the Observer Pattern.
 * Each will:
 * 	- Allow Observers( Alarms) to register and unregister.
 *  - Notify Alarms to be triggered in the appropriate case. 
 *  
 *  Most of the applications execution time, in it's early development phase at least,
 *  will be spend waiting for input in the loop defined here.
 *  
 */
public class MonitorCore {
	private static final int MAIN_MENU = 0;
	
	private static final int ADD_MONITOR = 1;
	private static final int REMOVE_MONITOR = 2;
	private static final int DISPLAY_AVAILABLE_MONITORS = 3;
	private static final int CREATE_ALARM = 4;
	private static final int REMOVE_ALARM = 5;
	private static final int DISPLAY_CURRENT_ALARMS = 6;
	
	private static final int SSH_MONITOR = 1;
	
	private List<MonitorSubject> monitors = new ArrayList<MonitorSubject>();
	
	public void core_launch() {
		Scanner sc = new Scanner(System.in);
		int option = 0;
	
		do {
			display_menu();
			option = sc.nextInt();
		
			switch (option) {
				case MAIN_MENU:
					System.out.println("Goodbye.");
					break;
				case ADD_MONITOR:
					add_monitor(sc);
					break;
				case REMOVE_MONITOR:
					System.out.println("2. Select Monitor to Remove: ");
					break;
				case DISPLAY_AVAILABLE_MONITORS:
					display_monitors();
					break;
				case CREATE_ALARM:
					create_alarm(sc);
					break;
				case REMOVE_ALARM:
					System.out.println("5. Remove Alarm");
					break;
				case DISPLAY_CURRENT_ALARMS:
					display_alarms();
					break;
				default:
					System.out.println("Option not supported.");
					break;
			}	
		} while (option != MAIN_MENU);
		sc.close();
	}
	
	/*
	 * Careful! We're using the same scanner object to read data
	 * from System.in! Manipulating multiple scanner objects
	 * associated with the same stream will lead to chaos!
	 * 
	 * DO NOT CLOSE THE STANDARD INPUT STREAM!
	 */
	private void add_monitor(Scanner scm) {
		int option = 0;
		
		do {
			System.out.println("Select Monitor to Add: ");
			System.out.println("1. SSH");
			System.out.print("Enter Choice: ");
			option = scm.nextInt();
			switch (option) {
				case MAIN_MENU:
					System.out.println("Back to Main Menu");
					break;
				case SSH_MONITOR:
					
					/*
					 * HERE IS WHERE WE INSTANTIATE A SUBJECT THAT WILL HAVE
					 * OBSERVERS. IT IS IN THIS POSITION WHERE THE OBSERVER
					 * PATTERN IS USED.
					 */
					
					/*
					 * When this function returns, the handle to this instance:
					 * ssh_monitor will be lost. However, in the next line monitors.add
					 * runs and the reference is copied to the array of monitors.
					 * This means that a handle to this instance of the MonitorSSH is
					 * available in the arraylist.
					 */
					MonitorSubject ssh_monitor = new MonitorSSH();
					monitors.add(ssh_monitor);
					System.out.println("SSH Monitor Added!");
					break;
				default:
					System.out.println("Option not supported!");
					break;
			}
		} while (option != MAIN_MENU);
	}
	
	private void display_menu() {
		System.out.println("Welcome to DEMOnMonitor v0.1");
		System.out.println("1. Add Monitor.");
		System.out.println("2. Remove Monitor.");
		System.out.println("3. Display Available Monitors");
		System.out.println("4. Create an Alarm");
		System.out.println("5. Remove an Alarm");
		System.out.println("6. Display Current Alarms");
		System.out.println("Enter Choice: ");
	}
	
	private void display_monitors() {
		/*
		 * BE CAREFUL HERE!
		 * The cast will obviously have to change as we add new types of monitors.
		 * But we could improve this with another Interface for monitors.
		 */
		System.out.println("Currently : " + monitors.size());
		for (MonitorSubject mon : monitors) {
			MonitorSSH ssh_mon = (MonitorSSH)mon;
			System.out.println(ssh_mon.get_name());
		}
	}
	
	//precondition: at least one monitor must exist
	//postcondition: a new alarm is created and associated with a monitor.
	private void create_alarm(Scanner sca) {
		
		/*
		 * We could use the following defensive programming, but using defensive programming
		 * would violate our precondition. Ok, this may be preferred option however,
		 * leave commented out for this example.
		if (monitors.size < 1) {
			System.out.println("Must create a Monitor first!");
			return;
		}
		*/
		sca.nextLine();
		
		System.out.println("Create New Alarm");
		
		System.out.println("Enter Email Address: ");
		String email = sca.nextLine();
		
		System.out.println("Enter Mobile Phone Number: ");
		String sms = sca.nextLine();
		/*
		 * HERE IS WHERE WE INSTANTIATE AN OBSERVER OBJECT AND USE THE REGISTER
		 * FUNCTION IN THE SUBJECT SO THAT THE SUBJECT CAN REFER TO OUR OBSERVERS.
		 */
		/*
		 * We would obviously have to ask the user which monitor they wish to
		 * associate an alarm with, however in this demo, we will assume the one we have
		 * just added.
		 */
		MonitorObserver ssh_alarm = new SSHAlarmObserver(email, sms);
		monitors.get(0).register_observer(ssh_alarm);
	}
	
	private void display_alarms() {
		
		for (MonitorSubject mon : monitors) {
			MonitorSSH ssh_mon = (MonitorSSH)mon;
			for (MonitorObserver alarm : ssh_mon.get_obs_alarms()) {
				SSHAlarmObserver ssh_alarm = (SSHAlarmObserver)alarm;
				System.out.println(ssh_alarm.email);
			}
		}
	}
}
