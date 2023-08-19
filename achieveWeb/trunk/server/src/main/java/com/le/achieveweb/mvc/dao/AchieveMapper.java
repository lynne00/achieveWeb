package com.le.achieveweb.mvc.dao;

import com.le.achieveweb.mvc.model.entity.AchieveItem;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface AchieveMapper {
    void saveCategory(HashMap<String,Object> map);
    void saveItem(HashMap<String,Object> map);
    void saveItemRecord(HashMap<String,Object> map);
    void saveTag(HashMap<String,Object> map);
    void saveRelationItemTag(HashMap<String,Object> map);
    String queryCategoryIdByUserIdCategoryName(String userId,String categoryName);
    String queryItemIdByUserIdItemName(String userId,String itemName);
    String queryTagIdByUserIdTagName(String userId,String tagName);
}
