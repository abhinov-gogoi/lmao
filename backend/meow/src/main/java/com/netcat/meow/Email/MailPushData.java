
package com.netcat.meow.Email;


import com.netcat.meow.Utility.Literal;

import java.sql.Timestamp;
import java.util.concurrent.ConcurrentLinkedQueue;


public class MailPushData {
	/**
	 * Hold the thread status
	 */
	private static boolean THREAD_RUNNING = Literal.TRUE;
	/**
	 * Hold the instance
	 */
	private static MailPushData instance = new MailPushData();
	/**
	 * Hold the mail queue
	 */
	private ConcurrentLinkedQueue<MailPushDataModel> queue_mail;

	/**
	 * Make constructor private to not make object
	 */
	private MailPushData() {
		queue_mail = new ConcurrentLinkedQueue<MailPushDataModel>();
	}

	/**
	 * 
	 * @param mailpushdatamodel
	 */
	public boolean addDatatoMail(MailPushDataModel mailpushdatamodel) {
		return queue_mail.add(mailpushdatamodel);
	}

	/**
	 * 
	 * @return
	 */
	public int getQueueCount() {
		return this.queue_mail.size();
	}

	/**
	 * 
	 * @return
	 */
	public boolean pushMail() {
		if (queue_mail.size() == 0) {
			System.out.println(new Timestamp(System.currentTimeMillis()) + " :: No Mail in Queue.......");
			return Literal.TRUE;
		} else {
			while(this.queue_mail.size()>0) {
				Mail.getInstance().sendEmail(this.queue_mail.poll());
			}
			return Literal.TRUE;
		}
	}

	/**
	 * 
	 * @return
	 */
	public static MailPushData getInstance() {
		return instance;
	}

	/**
	 * 
	 * @return
	 */
	public static boolean isTHREAD_RUNNING() {
		return THREAD_RUNNING;
	}

	/**
	 * 
	 * @param tHREAD_RUNNING
	 */
	public static void setTHREAD_RUNNING(boolean tHREAD_RUNNING) {
		THREAD_RUNNING = tHREAD_RUNNING;
	}

}
