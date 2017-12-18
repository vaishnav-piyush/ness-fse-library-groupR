/**
 * 
 */
package com.ness.services.model;

/**
 * @author P7109857
 *
 */
public class AuditBO {
	
	private String eventName;
	private String eventId;
	private String loginName;
	private String notes;

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "AuditBO [eventName=" + eventName + ", eventId=" + eventId + ", loginName=" + loginName + ", notes="
				+ notes + "]";
	}
}
