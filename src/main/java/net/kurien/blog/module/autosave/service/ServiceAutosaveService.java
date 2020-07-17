package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;

import java.util.List;

public interface ServiceAutosaveService {
    void add(ServiceAutosave serviceAutosave);
    List<ServiceAutosave> get(String serviceName, Long serviceNo);
    List<ServiceAutosave> get(Long asNo);
    void remove(String serviceName, Long serviceNo);
    void remove(Long asNo);
    int count();
    void removeAll();
}
