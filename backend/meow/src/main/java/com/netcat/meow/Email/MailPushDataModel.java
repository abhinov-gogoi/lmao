package com.netcat.meow.Email;

import com.netcat.meow.Utility.Literal;

import java.util.List;

public class MailPushDataModel {
	private final String body;
	private List<String> attachments;
	private final String subject;
	private final String to;
	private String cc;
	private String fromName ="LMAO";

	public MailPushDataModel(String subject, String body, String to) {
		this.body = body;
		this.subject = subject;
		this.to = to;
	}

	public String getHtml_body_part() {
		return body;
	}

	public List<String> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<String> attachments) {
		this.attachments = attachments;
	}

	public String getSubject() {
		if(this.subject==null) {
			return Literal.APPLICATION;
		}
		return subject;
	}

	public String getTo() {
		return to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getFromName() {
		return fromName;
	}

	public void setFromName(String fromName) {
		this.fromName = fromName;
	}

}
