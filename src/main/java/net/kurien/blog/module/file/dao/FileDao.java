package net.kurien.blog.module.file.dao;

import java.util.List;

import net.kurien.blog.module.file.entity.File;

public interface FileDao {
    public List<File> selectList(List<Integer> fileNos);
    public File selectOne(int fileNo);
    public int selectCount(Integer fileNo);
    public boolean isExistFilename(String randomizeString);
    public void insert(File file);
    public void delete(int fileNo);
    public void deleteList(List<Integer> fileNos);
    public void deleteAll();
}