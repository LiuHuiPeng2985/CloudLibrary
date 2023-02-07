package com.liu.controller;

import com.liu.domain.Record;
import com.liu.domain.User;
import com.liu.entity.PageResult;
import com.liu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/27 23:38
 */

@Controller
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @RequestMapping("/searchRecords")
    public ModelAndView searchRecords(Record record, Integer pageNum, Integer pageSize, HttpServletRequest request){
        if (pageNum==null){
            pageNum = 1;
        }
        if (pageSize==null){
            pageSize = 10;
        }
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult = recordService.searchRecords(record,pageNum,pageSize,user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("record");
        //将查询到的数据返回前端
        modelAndView.addObject("pageResult",pageResult);
        //将book中查询条件回显到页面中
        modelAndView.addObject("search",record);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }
}
