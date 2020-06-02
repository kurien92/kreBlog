package net.kurien.blog.module.file.service;

import java.util.List;
import java.util.Set;

import net.kurien.blog.module.file.entity.ServiceFile;

public interface ServiceFileService {
	public ServiceFile get(String serviceName, int serviceNo, int fileNo);
	public List<ServiceFile> getFiles(String serviceName, int serviceNo);
	
	public void add(ServiceFile serviceFile);
	public void addFiles(String serviceName, Integer serviceNo, Integer[] fileNos, String serviceFileWriteIp);
	
	public void remove(String serviceName, int serviceNo, int fileNo);
	public void removeFilesByNo(String serviceName, int serviceNo, Integer[] fileNos);
	public void removeFiles(String serviceName, int serviceNo);
	public void removeAll();
	public void syncFiles(String string, Integer postNo, Set<Integer> useFilesNo, String serviceFileWriteIp);
}
