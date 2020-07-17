package net.kurien.blog.module.autosave.dao;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;

import java.util.List;

public interface ServiceAutosaveDao {
    void insert(ServiceAutosave serviceAutosave);

    List<ServiceAutosave> selectByServiceNameAndServiceNo(String serviceName, Long serviceNo);

    List<ServiceAutosave> selectByAsNo(Long asNo);

    void deleteByServiceNameAndServiceNo(String serviceName, Long serviceNo);

    void deleteByAsNo(Long asNo);

    int selectCount();

    void deleteAll();
}