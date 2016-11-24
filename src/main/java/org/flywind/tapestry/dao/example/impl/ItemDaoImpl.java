package org.flywind.tapestry.dao.example.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.flywind.tapestry.dao.base.AbstractFBaseDao;
import org.flywind.tapestry.dao.example.ItemDao;
import org.flywind.tapestry.entities.example.Item;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl extends AbstractFBaseDao<Item> implements ItemDao {

	@Override
	public List<Item> getAllItemsByParentId(Long parentId) {
		StringBuilder hql = new StringBuilder("from Item ");
		hql.append("where parentId =:parentId");
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("parentId", parentId);
		return super.query(hql.toString(), params);
	}

}
