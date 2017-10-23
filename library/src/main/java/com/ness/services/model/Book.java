package com.ness.services.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="library")
public class Book implements Serializable{

    static final int MAX_LENGTH_TITLE = 30;
    static final int MAX_LENGTH_AUTHOR = 20;
    static final int MAX_LENGTH_DESCRIPTION = 50;
    static final int MAX_LENGTH_USERNAME = 20;
	
	@Id
    private String id;
	private String title;
	private String author;
	private String description;
    /*private IssueStatus issueStatus;*/
	private String issueStatus;
    private String issuedTo;
    private Date addedDate;
    private Date removedDate;
    private Date updatedDate;
	/**
	 * 
	 */
	public Book() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIssueStatus() {
		return issueStatus;
	}
	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}
	public String getIssuedTo() {
		return issuedTo;
	}
	public void setIssuedTo(String issuedTo) {
		this.issuedTo = issuedTo;
	}
	public Date getAddedDate() {
		return addedDate;
	}
	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}
	public Date getRemovedDate() {
		return removedDate;
	}
	public void setRemovedDate(Date removedDate) {
		this.removedDate = removedDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	public void update(String issueStatus, String issuedTo, Date updatedDate) {
        this.issueStatus = issueStatus;
        this.issuedTo = issuedTo;
        this.updatedDate = updatedDate;
    }
	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", author=" + author
				+ ", description=" + description + ", issueStatus="
				+ issueStatus + ", issuedTo=" + issuedTo + ", addedDate="
				+ addedDate + ", removedDate=" + removedDate + ", updatedDate="
				+ updatedDate + "]";
	}
}
