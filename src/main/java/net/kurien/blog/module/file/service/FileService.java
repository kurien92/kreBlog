package net.kurien.blog.module.file.service;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import net.kurien.blog.module.file.dto.FileDTO;
import net.kurien.blog.module.file.entity.File;

public interface FileService {
	FileDTO upload(String uploadPath, String serviceName, FileDTO fileDto, String uploadIp) throws NoSuchAlgorithmException, IOException;
    List<FileDTO> upload(String uploadPath, String serviceName, List<FileDTO> fileDtos, String remoteAddr) throws IOException, NoSuchAlgorithmException;
    java.io.File download(String serviceName, int serviceNo);
    List<File> getList(List<Integer> fileNos);
    File get(int fileNo);
    int getCount(Integer fileNo);
    void insert(File file);
    void delete(int fileNo) throws Exception;
    void deleteList(List<Integer> fileNos);
    void deleteAll();
}