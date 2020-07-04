package net.kurien.blog.module.file.service;

import java.util.List;
import java.util.Set;

import net.kurien.blog.module.file.entity.ServiceFile;

public interface ServiceFileService {
	public ServiceFile get(String serviceName, Integer serviceNo, Integer fileNo);
	public List<ServiceFile> getFiles(String serviceName, Integer serviceNo);

	public int getCount(Integer fileNo);
	public int getCount(String serviceName, Integer serviceNo);

	public void add(ServiceFile serviceFile);
	public void addFiles(String serviceName, Integer serviceNo, Integer[] fileNos, String serviceFileWriteIp);

	public void remove(Integer fileNo) throws Exception;
	public void remove(String serviceName, Integer serviceNo, Integer fileNo);
	public void removeFilesByNo(String serviceName, Integer serviceNo, Integer[] fileNos);
	public void removeFiles(String serviceName, Integer serviceNo);
	public void removeAll();
	public void syncFiles(String serviceName, Integer postNo, Set<Integer> useFilesNo, String serviceFileWriteIp);
}
