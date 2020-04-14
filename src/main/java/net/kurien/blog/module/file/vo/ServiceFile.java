package net.kurien.blog.module.file.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class ServiceFile {
	private String serviceName;
	private int serviceNo;
	private int fileNo;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date serviceFileWriteTime;
	private String serviceFileWriteIp;
	
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
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
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