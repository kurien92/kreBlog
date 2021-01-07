package net.kurien.blog.module.file.dao;

import java.util.List;

import net.kurien.blog.module.file.entity.ServiceFile;

public interface ServiceFileDao {
	ServiceFile selectOne(String serviceName, Integer serviceNo, Integer fileNo);
	List<ServiceFile> selectList(String serviceName, Integer serviceNo);
	int selectCount(Integer fileNo);
	int selectCount(String serviceName, Integer serviceNo);
	void insert(ServiceFile serviceFile);
	void insertFiles(List<ServiceFile> serviceFiles);
	void delete(Integer fileNo);
	void delete(String serviceName, Integer serviceNo, Integer fileNo);
	void deleteList(String serviceName, Integer serviceNo, Integer[] fileNos);
	void deleteList(String serviceName, Integer serviceNo);
	void deleteList(String serviceName);

	void deleteAll();
}
