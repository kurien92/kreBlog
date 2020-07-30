package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;

import java.util.List;

public interface ServiceAutosaveService {
    void add(ServiceAutosave serviceAutosave);
    ServiceAutosave get(String serviceName, Long asNo);
    List<ServiceAutosave> getList(String serviceName, String serviceAsUsername);
    void remove(String serviceName, String serviceAsUsername);
    void remove(Long asNo);
    int count();
    void removeAll();
    boolean isExist(Long asNo);
}
