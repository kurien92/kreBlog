package net.kurien.blog.module.file.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ServiceFile {
	private String serviceName;
	private Integer serviceNo;
	private Integer fileNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date serviceFileWriteTime;
	private String serviceFileWriteIp;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Integer getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(Integer serviceNo) {
		this.serviceNo = serviceNo;
	}
	public Integer getFileNo() {
		return fileNo;
	}
	public void setFileNo(Integer fileNo) {
		this.fileNo = fileNo;
	}
	public Date getServiceFileWriteTime() {
		return serviceFileWriteTime;
	}
	public void setServiceFileWriteTime(Date serviceFileWriteTime) {
		this.serviceFileWriteTime = serviceFileWriteTime;
	}
	public String getServiceFileWriteIp() {
		return serviceFileWriteIp;
	}
	public void setServiceFileWriteIp(String serviceFileWriteIp) {
		this.serviceFileWriteIp = serviceFileWriteIp;
	}
	
	@Override
	public String toString() {
		return "ServiceFile [serviceName=" + serviceName + ", serviceNo=" + serviceNo + ", fileNo=" + fileNo
				+ ", serviceFileWriteTime=" + serviceFileWriteTime + ", serviceFileWriteIp=" + serviceFileWriteIp + "]";
	}
}