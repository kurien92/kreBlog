package net.kurien.blog.module.shortUrl.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ServiceShortUrl {
	private String serviceName;
	private int serviceNo;
	private int shortUrlNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String createIp;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public int getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}
	public int getShortUrlNo() {
		return shortUrlNo;
	}
	public void setShortUrlNo(int shortUrlNo) {
		this.shortUrlNo = shortUrlNo;
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
		return "ServiceShortUrl [serviceName=" + serviceName + ", serviceNo=" + serviceNo + ", shortUrlNo=" + shortUrlNo
				+ ", createTime=" + createTime + ", createIp=" + createIp + "]";
	}
}