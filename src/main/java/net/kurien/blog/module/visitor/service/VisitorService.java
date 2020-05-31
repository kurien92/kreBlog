package net.kurien.blog.module.visitor.service;

import java.util.List;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.visitor.entity.Visitor;

public interface VisitorService {
	void collect(Visitor visitor);
	List<Visitor> getList(SearchCriteria criteria);
	Integer getCount(SearchCriteria criteria);
}
