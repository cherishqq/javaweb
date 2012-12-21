package com.javaweb.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Photo {
	
	private Long id;
	private String title;
	private String description;
	private String fileExt;
	private Date createDate;
	private String restAttachmentUri;
	private Long userId;
	
	
	
	public Photo(Long id, String title, String description, String fileExt,
			Date createDate, String restAttachmentUri, Long userId) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.fileExt = fileExt;
		this.createDate = createDate;
		this.restAttachmentUri = restAttachmentUri;
		this.userId = userId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFileExt() {
		return fileExt;
	}
	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getRestAttachmentUri() {
		return restAttachmentUri;
	}
	public void setRestAttachmentUri(String restAttachmentUri) {
		this.restAttachmentUri = restAttachmentUri;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	 
}
