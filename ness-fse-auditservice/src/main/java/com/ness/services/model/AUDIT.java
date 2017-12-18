package com.ness.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "AUDIT")
public class AUDIT implements Serializable {

	static final int MAX_LENGTH_ID = 11;
	static final int MAX_LENGTH_EVENT_NAME = 30;
	static final int MAX_LENGTH_EVENT_ID = 30;
	static final int MAX_LENGTH_LOGIN_NAME = 30;
	static final int MAX_LENGTH_NOTES = 50;

	@Id
	@Column(name = "ID")
	private int id;

	@Column(name = "EVENT_NAME")
	private String eventName;

	@Column(name = "EVENT_ID")
	private String eventId;

	@Column(name = "LOGIN_NAME")
	private String loginName;

	@Column(name = "UPDATE_DATE")
	private Date updatedDate;

	@Column(name = "NOTES")
	private String notes;

	public static int getMaxLengthEventName() {
		return MAX_LENGTH_EVENT_NAME;
	}

	public static int getMaxLengthEventId() {
		return MAX_LENGTH_EVENT_ID;
	}

	public static int getMaxLengthLoginName() {
		return MAX_LENGTH_LOGIN_NAME;
	}

	public static int getMaxLengthNotes() {
		return MAX_LENGTH_NOTES;
	}

	public static int getMaxLengthId() {
		return MAX_LENGTH_ID;
	}

	/**
	 * 
	 */
	public AUDIT() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public String toString() {
		return "AUDIT [id=" + id + ", eventName=" + eventName + ", eventId=" + eventId + ", loginName=" + loginName
				+ ", updatedDate=" + updatedDate + ", notes=" + notes + "]";
	}

}
