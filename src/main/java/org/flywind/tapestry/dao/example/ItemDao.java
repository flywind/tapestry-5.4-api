package org.flywind.tapestry.dao.example;

import java.util.List;

import org.flywind.tapestry.dao.base.FBaseDao;
import org.flywind.tapestry.entities.example.Item;

public interface ItemDao extends FBaseDao<Item>{

	public List<Item> getAllItemsByParentId(Long parentId);
}
