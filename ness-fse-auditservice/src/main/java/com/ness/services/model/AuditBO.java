package com.ness.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

public class AuditBO implements Serializable {
	
	@NotEmpty
    @Size(max = AUDIT.MAX_LENGTH_ID)
	private int id;
	
	
	@NotEmpty
    @Size(max = AUDIT.MAX_LENGTH_EVENT_NAME)
	private String event_name;
	
	@NotEmpty
    @Size(max = AUDIT.MAX_LENGTH_EVENT_ID)
	private String event_id;
	
	@NotEmpty
    @Size(max = AUDIT.MAX_LENGTH_LOGIN_NAME)
	private String login_name;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm a z")
    private Date update_date;
    
    @Size(max = AUDIT.MAX_LENGTH_NOTES)
    private String notes;

   
	public AuditBO() {}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEvent_name() {
		return event_name;
	}

	public void setEvent_name(String event_name) {
		this.event_name = event_name;
	}

	public String getEvent_id() {
		return event_id;
	}

	public void setEvent_id(String event_id) {
		this.event_id = event_id;
	}

	public String getLogin_name() {
		return login_name;
	}

	public void setLogin_name(String login_name) {
		this.login_name = login_name;
	}

	public Date getUpdate_date() {
		return update_date;
	}

	public void setUpdate_date(Date update_date) {
		this.update_date = update_date;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	
	/*@Override
	public String toString() {
		return "BookBO [id=" + id + ", title=" + title + ", author=" + author
				+ ", description=" + description + ", issueStatus="
				+ issueStatus + ", issuedTo=" + issuedTo + ", addedDate="
				+ addedDate + ", removedDate=" + removedDate + ", updatedDate="
				+ updatedDate + "]";
	}*/
}
