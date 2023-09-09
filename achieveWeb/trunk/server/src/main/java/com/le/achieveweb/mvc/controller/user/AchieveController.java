package com.le.achieveweb.mvc.controller.user;

import com.le.achieveweb.mvc.model.vo.AchieveView;
import com.le.achieveweb.mvc.service.AchieveService;
import com.le.achieveweb.response.Result;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

//跨域
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/achieve")
public class AchieveController {
    private final AchieveService achieveService;
    // 新建分类
    @RequestMapping("/newCategory")
    @ResponseBody
    public Result newCategory(@RequestBody AchieveView achieveView, HttpSession session) {
        return achieveService.createCategory(achieveView, session);
    }
    // 新建项目
    @RequestMapping("/newItem")
    @ResponseBody
    public Result newItem(@RequestBody AchieveView achieveView, HttpSession session) {
        return achieveService.createItem(achieveView, session);
    }
    // 新建项目记录
    @RequestMapping("/newItemRecord")
    @ResponseBody
    public Result newItemRecord(@RequestBody AchieveView achieveView, HttpSession session) {
        return achieveService.createItemRecord(achieveView, session);
    }
    // 新建标签
    @RequestMapping("/newTag")
    @ResponseBody
    public Result newTag(@RequestBody AchieveView achieveView, HttpSession session) {
        return achieveService.createTag(achieveView, session);
    }
    // 项目添加标签
    @RequestMapping("/addTag")
    @ResponseBody
    public Result addTag(@RequestBody AchieveView achieveView, HttpSession session) {
        return achieveService.addTag(achieveView, session);
    }
    //用户id查询分类
    @RequestMapping("/getCategory")
    @ResponseBody
    public Result getCategory(HttpSession session) {
        return achieveService.getCategory(session);
    }
    //用户id查询项目
    @RequestMapping("/getItem")
    @ResponseBody
    public Result getItem(HttpSession session) {
        return achieveService.getItem(session);
    }
    //用户id查询项目记录
    @RequestMapping("/getItemRecord")
    @ResponseBody
    public Result getItemRecord(HttpSession session) {
        return achieveService.getItemRecord(session);
    }
    //用户id查询标签
    @RequestMapping("/getTag")
    @ResponseBody
    public Result getTag(HttpSession session) {
        return achieveService.getTag(session);
    }
    //分类查询项目,项目记录
    @RequestMapping("/getItemRecordByCategory")
    @ResponseBody
    public Result getItemRecordByCategory(@RequestBody String categoryName, HttpSession session) {
        return achieveService.getItemRecordByCategory(categoryName, session);
    }
    //项目查询标签
    @RequestMapping("/getTagByItem")
    @ResponseBody
    public Result getTagByItem(@RequestBody String itemName, HttpSession session) {
        return achieveService.getTagByItem(itemName,session);
    }
}
