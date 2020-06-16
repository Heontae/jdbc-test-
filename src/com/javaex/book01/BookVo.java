package com.javaex.book01;

public class BookVo {


	//필드
	private int book_id , author_id;
	private String title,pubs,pub_date;
	
	
	//생성자
	public BookVo() {
		
	}
	public BookVo(int book_id) {
		this.book_id = book_id;
	}
	public BookVo(String title,String pubs,String pub_date , int author_id) {
		this.author_id = author_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
	}
	public BookVo(int book_id,  String title, String pubs, String pub_date,int author_id) {
		this.book_id = book_id;
		this.author_id = author_id;
		this.title = title;
		this.pubs = pubs;
		this.pub_date = pub_date;
	}
	
	//g/s
	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getAuthor_id() {
		return author_id;
	}

	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPubs() {
		return pubs;
	}

	public void setPubs(String pubs) {
		this.pubs = pubs;
	}

	public String getPub_date() {
		return pub_date;
	}

	public void setPub_date(String pub_date) {
		this.pub_date = pub_date;
	}
	
	//메소드
	@Override
	public String toString() {
		return "BookVo [book_id=" + book_id + ", author_id=" + author_id + ", title=" + title + ", pubs=" + pubs
				+ ", pub_date=" + pub_date + "]";
	}

}
