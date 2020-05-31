package net.kurien.blog.module.visitor.dao;

import java.util.List;

import net.kurien.blog.domain.SearchCriteria;
import net.kurien.blog.module.visitor.entity.Visitor;

public interface VisitorDao {
	public void insert(Visitor visitor);

	public List<Visitor> selectList(SearchCriteria criteria);

	public Integer getCount(SearchCriteria criteria);
}
