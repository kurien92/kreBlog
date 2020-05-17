package net.kurien.blog.module.post.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Post {
	private Integer postNo;
	private String categoryId;
	private String postAuthor;
	private String postPassword;
	private String postSubject;
	private String postContent;
	private PostViewStatus postView;
	private PostPublishStatus postPublish;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postWriteTime;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date postReservationTime;
	private String postWriteIp;
	
	public Integer getPostNo() {
		return postNo;
	}
	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getPostAuthor() {
		return postAuthor;
	}
	public void setPostAuthor(String postAuthor) {
		this.postAuthor = postAuthor;
	}
	public String getPostPassword() {
		return postPassword;
	}
	public void setPostPassword(String postPassword) {
		this.postPassword = postPassword;
	}
	public String getPostSubject() {
		return postSubject;
	}
	public void setPostSubject(String postSubject) {
		this.postSubject = postSubject;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public PostViewStatus getPostView() {
		return postView;
	}
	public void setPostView(PostViewStatus postView) {
		this.postView = postView;
	}
	public PostPublishStatus getPostPublish() {
		return postPublish;
	}
	public void setPostPublish(PostPublishStatus postPublish) {
		this.postPublish = postPublish;
	}
	public Date getPostWriteTime() {
		return postWriteTime;
	}
	public void setPostWriteTime(Date postWriteTime) {
		this.postWriteTime = postWriteTime;
	}
	public Date getPostReservationTime() {
		return postReservationTime;
	}
	public void setPostReservationTime(Date postReservationTime) {
		this.postReservationTime = postReservationTime;
	}
	public String getPostWriteIp() {
		return postWriteIp;
	}
	public void setPostWriteIp(String postWriteIp) {
		this.postWriteIp = postWriteIp;
	}
	
	@Override
	public String toString() {
		return "Post [postNo=" + postNo + ", categoryId=" + categoryId + ", postAuthor=" + postAuthor
				+ ", postPassword=" + postPassword + ", postSubject=" + postSubject + ", postContent=" + postContent
				+ ", postView=" + postView + ", postPublish=" + postPublish + ", postWriteTime=" + postWriteTime
				+ ", postReservationTime=" + postReservationTime + ", postWriteIp=" + postWriteIp + "]";
	}
}
