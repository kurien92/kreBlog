package net.kurien.blog.module.post.vo;

import java.sql.Timestamp;

public class Post {
	private Integer postNo;
	private String postAuthor;
	private String postPassword;
	private String postTitle;
	private String postContent;
	private PostViewStatus postView;
	private PostPublishStatus postPublish;
	private Timestamp postWriteTime;
	private Timestamp postReservationTime;
	private String postWriteIp;
	
	public Integer getPostNo() {
		return postNo;
	}
	public void setPostNo(Integer postNo) {
		this.postNo = postNo;
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
	public String getPostTitle() {
		return postTitle;
	}
	public void setPostTitle(String postTitle) {
		this.postTitle = postTitle;
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
	public Timestamp getPostWriteTime() {
		return postWriteTime;
	}
	public void setPostWriteTime(Timestamp postWriteTime) {
		this.postWriteTime = postWriteTime;
	}
	public Timestamp getPostReservationTime() {
		return postReservationTime;
	}
	public void setPostReservationTime(Timestamp postReservationTime) {
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
		return "Post [postNo=" + postNo + ", postAuthor=" + postAuthor + ", postPassword=" + postPassword
				+ ", postTitle=" + postTitle + ", postContent=" + postContent + ", postView=" + postView
				+ ", postPublish=" + postPublish + ", postWriteTime=" + postWriteTime + ", postReservationTime="
				+ postReservationTime + ", postWriteIp=" + postWriteIp + "]";
	}
}
