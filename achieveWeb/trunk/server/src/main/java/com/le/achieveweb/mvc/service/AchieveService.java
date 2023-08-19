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

@Service
@RequiredArgsConstructor
public class AchieveService {
    private final AchieveMapper achieveMapper;
    public Result createCategory(AchieveView achieveView, HttpSession session) {
        if (achieveView.getCategoryName() == null || achieveView.getCategoryName().equals("")) {
            throw new BusinessException(EmBusinessErr.INPUT_BLANK);
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
        HashMap<String, Object> map = new HashMap();
        String tagId = IdUtil.simpleUUID();
        map.put("id", tagId);
        map.put("userId", session.getAttribute(Constants.USERID));
        map.put("tagName", achieveView.getTagName());
        achieveMapper.saveTag(map);
        return ResultUtil.success("创建分类成功");
    }
    public Result addTag(AchieveView achieveView, HttpSession session) {
        String userId = (String) session.getAttribute(Constants.USERID);
        HashMap<String, Object> map = new HashMap();
        String relationItemTagId = IdUtil.simpleUUID();
        map.put("id", relationItemTagId);
        map.put("itemId", achieveMapper.queryItemIdByUserIdItemName(userId, achieveView.getItemName()));
        map.put("tagId", achieveMapper.queryTagIdByUserIdTagName(userId, achieveView.getTagName()));
        achieveMapper.saveRelationItemTag(map);
        return ResultUtil.success("添加标签成功");
    }
}
