package net.kurien.blog.module.autosave.service;

import net.kurien.blog.module.autosave.entity.Autosave;

import java.util.List;

public interface AutosaveService {
    Autosave get(Long asNo);
    List<Autosave> getList(List<Long> asNos);
    void save(Autosave autosave);
    void remove(Long atNo);
    int count();
    void removeAll();
}
