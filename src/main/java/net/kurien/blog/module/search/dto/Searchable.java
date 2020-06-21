package net.kurien.blog.module.search.dto;

public interface Searchable {
    SearchDTO search(String[] queries);
}
