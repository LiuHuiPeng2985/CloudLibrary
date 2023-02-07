package com.liu.service.impl;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 12:15
 */

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.liu.domain.Book;
import com.liu.domain.Record;
import com.liu.domain.User;
import com.liu.entity.PageResult;
import com.liu.mapper.BookMapper;
import com.liu.mapper.RecordMapper;
import com.liu.service.BookService;
import com.liu.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private RecordService recordService;

    @Override
    public PageResult selectNewBooks(Integer pageNum, Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> page = bookMapper.selectNewBooks();
        PageResult pageResult = new PageResult(page.getTotal(),page.getResult());
        return pageResult;
    }

    @Override
    public Book findById(Integer id) {
        return bookMapper.findById(id);
    }

    @Override
    public Integer borrowBook(Book book) {
        Book b = this.findById(book.getId());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        b.setBorrowTime(dateFormat.format(new Date()));
        b.setStatus("1");
        b.setBorrower(book.getBorrower());
        b.setReturnTime(book.getReturnTime());
        return bookMapper.editBook(b);
    }

    @Override
    public PageResult search(Book book, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Page<Book> bookPage = bookMapper.searchBooks(book);
        return new PageResult(bookPage.getTotal(),bookPage.getResult());
    }

    @Override
    public Integer addBook(Book book) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //设置新增图书的上架时间
        book.setUploadTime(dateFormat.format(new Date()));
        return bookMapper.addBook(book);
    }

    @Override
    public Integer editBook(Book book) {
        return bookMapper.editBook(book);
    }

    @Override
    public PageResult searchBorrowed(Book book, Integer pageNum, Integer pageSize, User user) {
        PageHelper.startPage(pageNum,pageSize);
        //将当前用户名放入查询条件中
        book.setBorrower(user.getName());
        Page<Book> bookPage = null;
        if ("ADMIN".equals(user.getRole())){
            //管理员
            bookPage = bookMapper.selectBorrowed(book);
        }
        else {
            //普通用户
            bookPage = bookMapper.selectMyBorrowed(book);
        }
        return new PageResult(bookPage.getTotal(),bookPage.getResult());

    }

    @Override
    public boolean returnBook(Integer id, User user) {
        Book book = this.findById(id);
        boolean result = book.getBorrower().equals(user.getName());
        if (result){
            book.setStatus("2");
            bookMapper.editBook(book);
        }
        return result;
    }

    @Override
    public Integer returnConfirm(Integer id) {
        Book book = this.findById(id);
        Record record = this.setRecord(book);
        book.setStatus("0");
        book.setBorrower("");
        book.setBorrowTime("");
        book.setReturnTime("");
        Integer result = bookMapper.editBook(book);
        if (result == 1){
            //新增借阅记录
            return recordService.addRecord(record);
        }
        return 0;
    }

    /**
     * 设置借阅记录信息
     * @param book
     * @return
     */
    private  Record setRecord(Book book){
        Record record = new Record();
        record.setBookname(book.getName());
        record.setBookisbn(book.getIsbn());
        record.setBorrower(book.getBorrower());
        record.setBorrowTime(book.getBorrowTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        record.setRemandTime(dateFormat.format(new Date()));
        return record;
    }


}
