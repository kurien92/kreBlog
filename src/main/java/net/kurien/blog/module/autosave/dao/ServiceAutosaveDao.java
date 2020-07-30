package net.kurien.blog.module.autosave.dao;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;

import java.util.List;

public interface ServiceAutosaveDao {
    void insert(ServiceAutosave serviceAutosave);

    List<ServiceAutosave> selectByServiceNameAndAsUsername(String serviceName, String serviceAsUsername);

    ServiceAutosave selectByAsNo(String serviceName, Long asNo);

    void deleteByServiceNameAndAsUsername(String serviceName, String serviceAsUsername);

    void deleteByAsNo(Long asNo);

    int selectCount();

    int selectCount(Long asNo);

    void deleteAll();
}