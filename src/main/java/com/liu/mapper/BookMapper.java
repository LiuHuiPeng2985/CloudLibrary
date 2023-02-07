package com.liu.mapper;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 12:07
 */

import com.github.pagehelper.Page;
import com.liu.domain.Book;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface BookMapper {

    @Select("select * from book where book_status!=3 order by book_uploadtime desc")
    @Results(id = "bookMap",value = {
            @Result(id = true,column = "book_id",property = "id"),
            @Result(column = "book_name",property = "name"),
            @Result(column = "book_isbn",property = "isbn"),
            @Result(column = "book_press",property = "press"),
            @Result(column = "book_author",property = "author"),
            @Result(column = "book_pagination",property = "pagination"),
            @Result(column = "book_price",property = "price"),
            @Result(column = "book_uploadtime",property = "uploadTime"),
            @Result(column = "book_status",property = "status"),
            @Result(column = "book_borrower",property = "borrower"),
            @Result(column = "book_borrowtime",property = "borrowTime"),
            @Result(column = "book_returntime",property = "returnTime")
    })
    Page<Book> selectNewBooks();


    /**
     * 根据id查询图书信息
     */
    @Select("select * from book where book_id = #{id}")
    @ResultMap("bookMap")
    Book findById(Integer id);

    Integer editBook(Book book);


    //查询图书
    @Select({"<script>" +
            "select * from book " + "where book_status != '3'"+
            "<if test=\"name != null\"> and book_name like concat('%',#{name},'%')</if>" +
            "<if test=\"press != null\"> and book_press like concat('%',#{press},'%')</if>" +
            "<if test=\"author != null\"> and book_author like concat('%',#{author},'%')</if>" +
            "order by book_status asc" +
            "</script>"
    })
    @ResultMap("bookMap")
    Page<Book> searchBooks(Book book);

    //新增图书
    Integer addBook(Book book);

    @Select({"<script>" +
            "select * from book " + "where book_status in (1,2) and book_borrower = #{borrower}"+
            "<if test=\"name != null\"> and book_name like concat('%',#{name},'%')</if>" +
            "<if test=\"press != null\"> and book_press like concat('%',#{press},'%')</if>" +
            "<if test=\"author != null\"> and book_author like concat('%',#{author},'%')</if>" +
            "order by book_status desc" +
            "</script>"
    })
    @ResultMap("bookMap")
    Page<Book> selectMyBorrowed(Book book);

    @Select({"<script>" +
            "select * from book " + "where book_status = 1 and book_borrower = #{borrower}"+
            "<if test=\"name != null\"> and book_name like concat('%',#{name},'%')</if>" +
            "<if test=\"press != null\"> and book_press like concat('%',#{press},'%')</if>" +
            "<if test=\"author != null\"> and book_author like concat('%',#{author},'%')</if>" +
            "or book_status = 2" +
            "<if test=\"name != null\"> and book_name like concat('%',#{name},'%')</if>" +
            "<if test=\"press != null\"> and book_press like concat('%',#{press},'%')</if>" +
            "<if test=\"author != null\"> and book_author like concat('%',#{author},'%')</if>" +
            "order by book_status desc" +
            "</script>"
    })
    @ResultMap("bookMap")
    Page<Book> selectBorrowed(Book book);
}
