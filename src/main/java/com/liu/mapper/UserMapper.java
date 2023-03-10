package com.liu.mapper;

/*
 * @author  LiuHuiPeng
 * @date    2022/3/24 11:27
 */

import com.liu.domain.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    @Select("select * from user where user_email = #{email} AND user_password = #{password} AND user_status != '1'")
    @Results(id = "userMap",value = {
            //column代表表中的字段，property对应实体类的属性
            @Result(id = true,column = "user_id",property = "id"),
            @Result(column = "user_name",property = "name"),
            @Result(column = "user_password",property = "password"),
            @Result(column = "user_email",property = "email"),
            @Result(column = "user_role",property = "role"),
            @Result(column = "user_status",property = "status")
    })
    User login(User user);

}
