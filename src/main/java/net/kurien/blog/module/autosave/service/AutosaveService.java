package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.Autosave;

public interface AutosaveService {
    Autosave get(Long atNo);
    void save(Autosave autosave);
    void remove(Long atNo);
    int count();
    void removeAll();
}
