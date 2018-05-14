package com.llb.dp.service.imp;

import com.github.pagehelper.PageHelper;
import com.llb.dp.dao.UserMapper;
import com.llb.dp.dao.UserMapperCustom;
import com.llb.dp.model.User;
import com.llb.dp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by robin on 2018/5/11.
 */
@Service(value = "userService")
public class UserServiceImp implements IUserService {

    @Autowired
    private UserMapperCustom userMapperCustom;

    @Autowired
    private UserMapper userMapper;


    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return userMapperCustom.selectUsers();
    }


}
