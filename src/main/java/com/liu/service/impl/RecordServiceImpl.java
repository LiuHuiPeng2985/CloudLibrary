package com.liu.service.impl;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/27 23:27
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liu.domain.Record;
import com.liu.domain.User;
import com.liu.entity.PageResult;
import com.liu.mapper.RecordMapper;
import com.liu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public Integer addRecord(Record record) {
        return recordMapper.addRecord(record);
    }

    @Override
    public PageResult searchRecords(Record record, Integer pageNum, Integer pageSize, User user) {
        //设置分页参数
        PageHelper.startPage(pageNum,pageSize);
        //如果当前用户是普通用户，只能查询自己的借阅记录
        if (!"ADMIN".equals(user.getRole())){
            record.setBorrower(user.getName());
        }
        Page<Record> page = recordMapper.searchRecords(record);
        return new PageResult(page.getTotal(),page.getResult());
    }
}
