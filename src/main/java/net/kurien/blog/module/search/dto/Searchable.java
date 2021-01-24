package net.kurien.blog.module.search.dto;

public interface Searchable {
    SearchDto search(String[] queries);
}
