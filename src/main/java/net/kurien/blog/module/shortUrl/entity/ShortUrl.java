package net.kurien.blog.module.shortUrl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ShortUrl {
	private int shortUrlNo;
	private String realUrl;
	private String encodedUrl;
	private Long visitCount;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String createIp;
	
	public int getShortUrlNo() {
		return shortUrlNo;
	}
	public void setShortUrlNo(int shortUrlNo) {
		this.shortUrlNo = shortUrlNo;
	}
	public String getRealUrl() {
		return realUrl;
	}
	public void setRealUrl(String realUrl) {
		this.realUrl = realUrl;
	}
	public String getEncodedUrl() {
		return encodedUrl;
	}
	public void setEncodedUrl(String encodedUrl) {
		this.encodedUrl = encodedUrl;
	}
	public Long getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(Long visitCount) {
		this.visitCount = visitCount;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateIp() {
		return createIp;
	}
	public void setCreateIp(String createIp) {
		this.createIp = createIp;
	}
	
	@Override
	public String toString() {
		return "ShortUrl [shortUrlNo=" + shortUrlNo + ", realUrl=" + realUrl + ", encodedUrl=" + encodedUrl
				+ ", visitCount=" + visitCount + ", createTime=" + createTime + ", createIp=" + createIp + "]";
	}
}
