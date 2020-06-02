package net.kurien.blog.module.file.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.kurien.blog.module.file.entity.File;

public interface FileService {
	public int upload(String uploadPath, String serviceName, byte[] fileBytes, String originalFilename, long fileSize, String contentType, String uploadIp) throws NoSuchAlgorithmException, IOException;
	public java.io.File download(String serviceName, int serviceNo);
    public List<File> getList(List<Integer> fileNos);
    public File get(int fileNo);
    public int getCount(Integer fileNo);
    public void insert(File file);
    public void delete(int fileNo);
    public void deleteList(List<Integer> fileNos);
    public void deleteAll();
}