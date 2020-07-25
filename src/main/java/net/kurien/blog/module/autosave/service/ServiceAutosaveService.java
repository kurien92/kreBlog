package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;

import java.util.List;

public interface ServiceAutosaveService {
    void add(ServiceAutosave serviceAutosave);
    List<ServiceAutosave> getList(String serviceName, String serviceAsUsername);
    List<ServiceAutosave> getList(Long asNo);
    void remove(String serviceName, String serviceAsUsername);
    void remove(Long asNo);
    int count();
    void removeAll();
}
