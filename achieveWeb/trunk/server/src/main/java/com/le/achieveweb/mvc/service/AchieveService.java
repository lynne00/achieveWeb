package com.le.achieveweb.mvc.service;

import cn.hutool.core.util.IdUtil;
import com.le.achieveweb.error.BusinessException;
import com.le.achieveweb.error.EmBusinessErr;
import com.le.achieveweb.mvc.dao.AchieveMapper;
import com.le.achieveweb.mvc.model.vo.AchieveView;
import com.le.achieveweb.response.Result;
import com.le.achieveweb.util.Constants;
import com.le.achieveweb.util.ResultUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AchieveService {
    private final AchieveMapper achieveMapper;
    public Result createCategory(AchieveView achieveView, HttpSession session) {
        if (achieveView.getCategoryName() == null || achieveView.getCategoryName().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        }
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        String categoryExist = achieveMapper.queryCategoryIdByUserIdCategoryName(String.valueOf(session.getAttribute(Constants.USERID)),achieveView.getCategoryName());
        if(categoryExist!=null){
            throw new BusinessException(EmBusinessErr.DATA_EXISTED);
        }
        HashMap<String, Object> map = new HashMap();
        String categoryId = IdUtil.simpleUUID();
        map.put("id", categoryId);
        map.put("userId", session.getAttribute(Constants.USERID));
        map.put("categoryName", achieveView.getCategoryName());
        achieveMapper.saveCategory(map);
        return ResultUtil.success("创建分类成功");
    }
    public Result createItem(AchieveView achieveView, HttpSession session) {
        if (achieveView.getItemName() == null || achieveView.getItemName().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        }
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        String itemExist = achieveMapper.queryItemIdByUserIdItemName(String.valueOf(session.getAttribute(Constants.USERID)),achieveView.getItemName());
        if(itemExist!=null){
            throw new BusinessException(EmBusinessErr.DATA_EXISTED);
        }
        HashMap<String, Object> map = new HashMap();
        String itemId = IdUtil.simpleUUID();
        String userId = (String) session.getAttribute(Constants.USERID);
        map.put("id", itemId);
        map.put("userId", session.getAttribute(Constants.USERID));
        map.put("goalCategoryId", achieveMapper.queryCategoryIdByUserIdCategoryName(userId, achieveView.getCategoryName()));
        map.put("itemName", achieveView.getItemName());
        achieveMapper.saveItem(map);
        return ResultUtil.success("创建项目成功");
    }
    public Result createItemRecord(AchieveView achieveView, HttpSession session) {
        if (achieveView.getReachDate() == null || achieveView.getReachDate().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        }
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        HashMap<String, Object> map = new HashMap();
        String recordItemId = IdUtil.simpleUUID();
        String userId = (String) session.getAttribute(Constants.USERID);
        map.put("id", recordItemId);
        map.put("userId", session.getAttribute(Constants.USERID));
        map.put("itemId", achieveMapper.queryItemIdByUserIdItemName(userId, achieveView.getItemName()));
        map.put("reachQuantity", achieveView.getReachQuantity());
        map.put("reachDate", achieveView.getReachDate());
        map.put("createAt", LocalDateTime.now());
        achieveMapper.saveItemRecord(map);
        return ResultUtil.success("创建记录成功");
    }
    public Result createTag(AchieveView achieveView, HttpSession session) {
        if (achieveView.getTagName() == null || achieveView.getTagName().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
        }
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        String tagExist = achieveMapper.queryTagIdByUserIdTagName(String.valueOf(session.getAttribute(Constants.USERID)),achieveView.getTagName());
        if(tagExist!=null){
            throw new BusinessException(EmBusinessErr.DATA_EXISTED);
        }
        HashMap<String, Object> map = new HashMap();
        String tagId = IdUtil.simpleUUID();
        map.put("id", tagId);
        map.put("userId", session.getAttribute(Constants.USERID));
        map.put("tagName", achieveView.getTagName());
        achieveMapper.saveTag(map);
        return ResultUtil.success("创建标签成功");
    }
    public Result addTag(AchieveView achieveView, HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        String userId = (String) session.getAttribute(Constants.USERID);
        HashMap<String, Object> map = new HashMap();
        String relationItemTagId = IdUtil.simpleUUID();
        map.put("id", relationItemTagId);
        map.put("userId",userId);
        map.put("itemId", achieveMapper.queryItemIdByUserIdItemName(userId, achieveView.getItemName()));
        map.put("tagId", achieveMapper.queryTagIdByUserIdTagName(userId, achieveView.getTagName()));
        String tagExist = achieveMapper.queryReItemTagIdByUserIdTagItem(map);
        if(tagExist!=null){
            throw new BusinessException(EmBusinessErr.DATA_EXISTED);
        }
        achieveMapper.saveRelationItemTag(map);
        return ResultUtil.success("添加标签成功");
    }
    public Result getCategory(HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        List<String> category = achieveMapper.queryCategoryByUserId((String) session.getAttribute(Constants.USERID));
        return ResultUtil.success(category);
    }
    public Result getItem(HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        List<String> item = achieveMapper.queryItemByUserId((String) session.getAttribute(Constants.USERID));
        return ResultUtil.success(item);
    }
    public Result getItemRecord(HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        List<AchieveView> itemRecord = achieveMapper.queryItemRecordByUserId((String) session.getAttribute(Constants.USERID));
        return ResultUtil.success(itemRecord);
    }
    public Result getTag(HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        List<String> tag = achieveMapper.queryTagByUserId((String) session.getAttribute(Constants.USERID));
        return ResultUtil.success(tag);
    }
    public Result getItemRecordByCategory(String categoryName, HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        List<AchieveView> itemRecord = achieveMapper.queryItemRecordByCategoryNameUserId(categoryName, (String) session.getAttribute(Constants.USERID));
        return ResultUtil.success(itemRecord);
    }
    public Result getTagByItem(String itemName, HttpSession session) {
        if(session.getAttribute(Constants.USERID) == null || session.getAttribute(Constants.USERID).equals("")){
            throw new BusinessException(EmBusinessErr.LOGIN_NOT_EXISTED);
        }
        String itemId = achieveMapper.queryItemIdByUserIdItemName((String) session.getAttribute(Constants.USERID), itemName);
        List<String> tag = achieveMapper.queryTagByItemId(itemId);
        return ResultUtil.success(tag);
    }
}
