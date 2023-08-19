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
    // 新建项目
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
    // 添加标签
    @RequestMapping("/addTag")
    @ResponseBody
    public Result addTag(@RequestBody AchieveView achieveView, HttpSession session) {
        return achieveService.addTag(achieveView, session);
    }
}
