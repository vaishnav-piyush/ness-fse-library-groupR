package com.ness.services.service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ness.services.model.AuditBO;
import com.ness.services.model.BookBO;
import com.ness.services.model.BookEventBO;

@Service(value="EventService")
public class EventService{
	private static final Logger LOGGER = LoggerFactory.getLogger(EventService.class);
	
	@Autowired
	private PublishService publishService;
	
	public void raiseEvent(BookEventBO<BookBO> eventBO, String eventType) throws Exception{
		LOGGER.info("EventService.raiseEvent() Entry");
		AuditBO auditBO = new AuditBO();
		auditBO.setEventName(eventType);
		auditBO.setEventId(eventBO.getT().getId());
		auditBO.setLoginName("FSEGroup");
		auditBO.setNotes(getNotes(eventBO, eventType));
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		String date = dateFormat.format(calendar.getTime());
		auditBO.setUpdateDate(dateFormat.parse(date));
		LOGGER.info("EventService.raiseEvent() auditBO: " + auditBO.toString());
		LOGGER.info("EventService.raiseEvent() Exit");
	}
	
	public String getNotes(BookEventBO<BookBO> eventBO, String eventType){
		String result = "";
		if(BookEventBO.EVENT_BOOK_ADDED.equalsIgnoreCase(eventType)){
			result = "Book Added Id: " + eventBO.getT().getId();
		}else if(BookEventBO.EVENT_BOOK_UPDATED.equalsIgnoreCase(eventType)){
			result = "Book Updated: Status: " + eventBO.getT().getIssueStatus() + " IssuedTo: " + eventBO.getT().getIssuedTo();
		}else if(BookEventBO.EVENT_BOOK_SEARCHED.equalsIgnoreCase(eventType)){
			result = "Book Searched Id: " + eventBO.getT().getId();
		}else if(BookEventBO.EVENT_BOOK_DELETED.equalsIgnoreCase(eventType)){
			result = "Book Deleted Id: " + eventBO.getT().getId();
		}else{
			result = "Temp Notes";
		}
		return result;
	}
}
