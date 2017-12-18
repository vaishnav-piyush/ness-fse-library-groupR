package com.ness.services.model;


public class BookEventBO<T>{

	public static final String EVENT_BOOK_ADDED = "BOOK_ADDED";
	public static final String EVENT_BOOK_UPDATED = "BOOK_UPDATED";
	public static final String EVENT_BOOK_SEARCHED = "BOOK_SEARCHED";
	public static final String EVENT_BOOK_DELETED = "BOOK_DELETED";
	
	private T t;

	public BookEventBO(T t) {
		super();
		this.t = t;
	}

	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}
	
}
