package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.ServiceAutosave;

public interface ServiceAutosaveService {
    void add(ServiceAutosave serviceAutosave);
    ServiceAutosave get(String serviceName, Long serviceNo);
    ServiceAutosave get(Long asNo);
    void remove(String serviceName, Long serviceNo);
    void remove(Long asNo);
    int count();
    void removeAll();
}
