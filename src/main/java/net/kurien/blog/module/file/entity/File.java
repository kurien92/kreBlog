package net.kurien.blog.module.file.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class File {
    private Integer fileNo;
    private String fileType;
    private String filePath;
    private String fileName;
    private String fileStoredName;
    private String fileExtension;
    private String fileMime;
    private Long fileSize;
    private String fileUploadIp;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fileUploadTime;
	
	public Integer getFileNo() {
		return fileNo;
	}
	public void setFileNo(Integer fileNo) {
		this.fileNo = fileNo;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileStoredName() {
		return fileStoredName;
	}
	public void setFileStoredName(String fileStoredName) {
		this.fileStoredName = fileStoredName;
	}
	public String getFileExtension() {
		return fileExtension;
	}
	public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	public String getFileMime() {
		return fileMime;
	}
	public void setFileMime(String fileMime) {
		this.fileMime = fileMime;
	}
	public Long getFileSize() {
		return fileSize;
	}
	public void setFileSize(Long fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileUploadIp() {
		return fileUploadIp;
	}
	public void setFileUploadIp(String fileUploadIp) {
		this.fileUploadIp = fileUploadIp;
	}
	public Date getFileUploadTime() {
		return fileUploadTime;
	}
	public void setFileUploadTime(Date fileUploadTime) {
		this.fileUploadTime = fileUploadTime;
	}
	
	@Override
	public String toString() {
		return "File [fileNo=" + fileNo + ", fileType=" + fileType + ", filePath=" + filePath + ", fileName=" + fileName
				+ ", fileStoredName=" + fileStoredName + ", fileExtension=" + fileExtension + ", fileMime=" + fileMime
				+ ", fileSize=" + fileSize + ", fileUploadIp=" + fileUploadIp + ", fileUploadTime=" + fileUploadTime
				+ "]";
	}
}