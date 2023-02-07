package com.liu.entity;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 12:13
 */

import java.io.Serializable;
import java.util.List;

public class PageResult implements Serializable {

    //总数
    private long total;
    //返回的数据集合
    private List rows;

    public PageResult(long total, List rows) {
        this.total = total;
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
