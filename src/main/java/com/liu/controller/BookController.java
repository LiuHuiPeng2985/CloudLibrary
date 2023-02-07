package com.liu.controller;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 12:19
 */

import com.liu.domain.Book;
import com.liu.domain.User;
import com.liu.entity.PageResult;
import com.liu.entity.Result;
import com.liu.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/selectNewbooks")
    public ModelAndView selectNewBooks(){
        PageResult pageResult = bookService.selectNewBooks(1,5);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books_new");
        modelAndView.addObject("pageResult", pageResult);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/findById")
    public Result<Book> findById(Integer id){
        try {
            Book book = bookService.findById(id);
            if (book == null){
                return new Result<Book>(false,"查询图书失败");
            }
            return new Result<Book>(true,"查询图书成功",book);
        }catch (Exception e){
            e.printStackTrace();
            return new Result<Book>(false,"查询图书失败");
        }
    }

    @ResponseBody
    @RequestMapping("/borrowBook")
    public Result borrowBook(Book book, HttpSession session){
        try {
            User user = (User) session.getAttribute("USER_SESSION");
            book.setBorrower(user.getName());
            Integer count = bookService.borrowBook(book);
            if (count != 1){
                return new Result(false,"借书失败");
            }
            return new Result(true,"借书成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"借书失败");
        }
    }


    @RequestMapping("/search")
    public ModelAndView search(Book book, Integer pageNum, Integer pageSize, HttpServletRequest request){
        if (pageNum==null){
            pageNum = 1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        PageResult pageResult = bookService.search(book, pageNum, pageSize);
        ModelAndView modelAndView = new ModelAndView();
        //设置页面
        modelAndView.setViewName("books");
        //将查询到的数据返回前端
        modelAndView.addObject("pageResult",pageResult);
        //将book中查询条件回显到页面中
        modelAndView.addObject("search",book);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/addBook")
    public Result addBook(Book book){
        try {
            Integer count = bookService.addBook(book);
            if (count != 1){
                return new Result(false,"新增图书失败!");
            }
            return new Result(true,"新增图书成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"新增图书失败!");
        }
    }

    @ResponseBody
    @RequestMapping("/editBook")
    public Result editBook(Book book){
        try {
            Integer count = bookService.editBook(book);
            if (count != 1){
                return new Result(false,"编辑失败!");
            }
            return new Result(true,"编辑成功!");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"编辑失败!");
        }
    }

    @RequestMapping("/searchBorrowed")
    public ModelAndView searchBorrowed(Book book,Integer pageNum,Integer pageSize,HttpServletRequest request){
        if (pageNum==null){
            pageNum = 1;
        }
        if (pageSize==null){
            pageSize=10;
        }
        User user = (User) request.getSession().getAttribute("USER_SESSION");
        PageResult pageResult = bookService.searchBorrowed(book,pageNum,pageSize,user);
        ModelAndView modelAndView = new ModelAndView();
        //设置界面
        modelAndView.setViewName("book_borrowed");
        //将查询到的数据返回前端
        modelAndView.addObject("pageResult",pageResult);
        //将book中查询条件回显到页面中
        modelAndView.addObject("search",book);
        modelAndView.addObject("pageNum",pageNum);
        modelAndView.addObject("gourl",request.getRequestURI());
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping("/returnBook")
    public Result returnBook(Integer id,HttpSession session){
        User user = (User) session.getAttribute("USER_SESSION");
        try {
            boolean result = bookService.returnBook(id, user);
            if (!result){
                return new Result(false,"申请还书失败");
            }
            return new Result(true,"申请还书成功");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"申请还书失败");
        }
    }

    @ResponseBody
    @RequestMapping("/returnConfirm")
    public Result returnConfirm(Integer id){
        try {
            Integer result = bookService.returnConfirm(id);
            if (result != 1){
                return new Result(false,"审核失败");
            }
            return new Result(true,"审核通过");
        }catch (Exception e){
            e.printStackTrace();
            return new Result(false,"审核失败");
        }
    }





}
