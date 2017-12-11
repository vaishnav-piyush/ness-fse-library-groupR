/**
 * 
 */
package com.ness.services.model;

import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author P7109857
 *
 */
public class AuditBO {
	
	@NotEmpty
    @Size(max = Audit.MAX_LENGTH_EVENT_NAME)
	private String eventName;
	
	@NotEmpty
    @Size(max = Audit.MAX_LENGTH_EVENT_ID)
	private String eventId;
	
	@NotEmpty
    @Size(max = Audit.MAX_LENGTH_LOGIN_NAME)
	private String loginName;
	
    private Date updateDate;
    
    @Size(max = Audit.MAX_LENGTH_NOTES)
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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "AuditBO [eventName=" + eventName + ", eventId=" + eventId + ", loginName=" + loginName + ", updateDate="
				+ updateDate + ", notes=" + notes + "]";
	}
}
