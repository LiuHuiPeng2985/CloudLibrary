package com.liu.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/27 23:25
 */

import com.liu.domain.Record;
import com.liu.domain.User;
import com.liu.entity.PageResult;

public interface RecordService {
    /**
     * 新增借阅记录
     */
    Integer addRecord(Record record);

    /**
     * 查询借阅记录
     */
    PageResult searchRecords(Record record, Integer pageNum, Integer pageSize, User user);
}
