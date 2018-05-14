package com.llb.dp.service.imp;

import com.github.pagehelper.PageHelper;
import com.llb.dp.dao.UserMapper;
import com.llb.dp.dao.UserMapperCustom;
import com.llb.dp.model.User;
import com.llb.dp.service.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by robin on 2018/5/11.
 */

@Service(value = "userService")
public class UserServiceImp implements IUserService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
        logger.debug("findAllUser : pageNum : " + pageNum + ", pageSize : " + pageSize);
        logger.info("findAllUser : pageNum : " + pageNum + ", pageSize : " + pageSize);
        logger.warn("findAllUser : pageNum : " + pageNum + ", pageSize : " + pageSize);
        logger.error("findAllUser : pageNum : " + pageNum + ", pageSize : " + pageSize);
        return userMapperCustom.selectUsers();
    }


}
