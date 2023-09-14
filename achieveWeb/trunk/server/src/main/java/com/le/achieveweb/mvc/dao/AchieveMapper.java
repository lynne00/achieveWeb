package com.le.achieveweb.mvc.dao;

import com.le.achieveweb.mvc.model.vo.AchieveView;
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
    String queryReItemTagIdByUserIdTagItem(HashMap<String,Object> map);
    List<String> queryCategoryByUserId(String userId);
    List<AchieveView> queryItemRecordByUserId(String userId);
    List<String>  queryTagByUserId(String userId);
    List<String>  queryItemByUserId(String userId);
    List<AchieveView> queryItemRecordByCategoryNameUserId(String categoryName, String userId);
    List<String> queryTagByItemId(String itemId);
    void deleteCategory(HashMap<String,Object> map);
    void deleteItem(HashMap<String,Object> map);
    void deleteItemRecord(HashMap<String,Object> map);
    void deleteTag(HashMap<String,Object> map);
    void deleteRelationItemTag(HashMap<String,Object> map);
}
