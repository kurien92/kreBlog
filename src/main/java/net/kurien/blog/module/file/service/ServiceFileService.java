package net.kurien.blog.module.file.service;

import java.util.List;
import java.util.Set;

import net.kurien.blog.module.file.entity.ServiceFile;

public interface ServiceFileService {
	ServiceFile get(String serviceName, Integer serviceNo, Integer fileNo);
	List<ServiceFile> getFiles(String serviceName, Integer serviceNo);

	int getCount(Integer fileNo);
	int getCount(String serviceName, Integer serviceNo);

	void add(ServiceFile serviceFile);
	void addFiles(String serviceName, Integer serviceNo, Integer[] fileNos, String serviceFileWriteIp);

	void remove(Integer fileNo) throws Exception;
	void remove(String serviceName, Integer serviceNo, Integer fileNo);
	void removeFilesByNo(String serviceName, Integer serviceNo, Integer[] fileNos);
	void removeFiles(String serviceName, Integer serviceNo);
	void removeFiles(String content);
	void removeAll();

	void syncFiles(String serviceName, Integer postNo, Set<Integer> useFilesNo, String serviceFileWriteIp);
}
