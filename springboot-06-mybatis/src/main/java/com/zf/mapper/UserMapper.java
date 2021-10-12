package com.zf.mapper;


import com.zf.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {

    List<User> getAllUser();

    User getUserById(@Param("id") int id);

    int addUser(User user);

    int updateUser(User user);

    int deleteUser(@Param("id") int id);




}
