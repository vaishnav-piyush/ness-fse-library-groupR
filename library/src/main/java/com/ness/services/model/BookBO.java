package com.ness.services.model;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class BookBO implements Serializable {
	
	private String id;

    @NotEmpty
    @Size(max = Book.MAX_LENGTH_TITLE)
    private String title;
    
    @NotEmpty
    @Size(max = Book.MAX_LENGTH_AUTHOR)
    private String author;
    
    @Size(max = Book.MAX_LENGTH_DESCRIPTION)
	private String description;
    
    @NotEmpty
    private String issueStatus;
    //private IssueStatus issueStatus;
    
    @Size(max = Book.MAX_LENGTH_USERNAME)
    private String issuedTo;
    
    private Date addedDate;
    private Date removedDate;
    private Date updatedDate;
	/**
	 * 
	 */
	public BookBO() {}
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
	@Override
	public String toString() {
		return "BookBO [id=" + id + ", title=" + title + ", author=" + author
				+ ", description=" + description + ", issueStatus="
				+ issueStatus + ", issuedTo=" + issuedTo + ", addedDate="
				+ addedDate + ", removedDate=" + removedDate + ", updatedDate="
				+ updatedDate + "]";
	}
}
