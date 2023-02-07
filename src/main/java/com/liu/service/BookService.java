package com.liu.service;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 12:15
 */

import com.liu.domain.Book;
import com.liu.domain.User;
import com.liu.entity.PageResult;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


public interface BookService {

    /**
     * @param pageNum   当前的页数
     * @param pageSize  每一页的条数
     * @return
     */
    PageResult selectNewBooks(Integer pageNum,Integer pageSize);


    /**
     * 根据id查询图书信息
     */
    Book findById(Integer id);

    /**
     * 借阅图书
     */
    Integer borrowBook(Book book);

    /**
     * 图书搜索
     */
    PageResult search(Book book,Integer pageNum,Integer pageSize);

    /**
     * 新增图书
     */
    Integer addBook(Book book);

    /**
     * 编辑图书
     */
    Integer editBook(Book book);

    /**
     * 查询当前借阅
     */
    PageResult searchBorrowed(Book book, Integer pageNum, Integer pageSize, User user);

    /**
     * 申请归还图书
     */
    boolean returnBook(Integer id,User user);

    /**
     * 确认还书
     */
    Integer returnConfirm(Integer id);
}
