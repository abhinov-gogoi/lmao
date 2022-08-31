package scheduler;

import com.netcat.meow.Email.MailPushData;
import com.netcat.meow.Utility.Literal;
import com.netcat.meow.Utility.Utility;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
public class Scheduler {

	/**
	 * This scheduler runs on 1st day of every month
	 */
	@Scheduled(cron = "0 0 0 1 * *")
	private void firstDayOfMonth() {
		try {
				System.out.println("Attendance status 'NA' inserted for worker");
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}
	/**
	 * Scheduler
	 */
	@Scheduled(fixedDelay = 60000, initialDelay = 10000)
	@RequestMapping(value = "sendEmailScheduler", method = RequestMethod.GET)
	private void sendEmailScheduler() {
		try {
			if (MailPushData.isTHREAD_RUNNING()) {
				/**
				 * Make the flag false
				 */
				MailPushData.setTHREAD_RUNNING(Literal.FALSE);
				/**
				 * Push the email
				 */
				MailPushData.getInstance().pushMail();
				/**
				 * Make the flag true
				 */
				MailPushData.setTHREAD_RUNNING(Literal.TRUE);
			}
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
		}
	}
	/**
	 *
	 * @return
	 */
	@RequestMapping(value = "getEmailQueueList", method = RequestMethod.GET)
	private int getEmailQueueList() {
		try {
			/**
			 * Push the email
			 */
			return MailPushData.getInstance().getQueueCount();
		} catch (Exception e) {
			Utility.printStackTrace(e, this.getClass().getName());
			return 0;
		}
	}

	/**
	 * TODO :: write a scheduler to delete unverified emails after some time
	 */


}
