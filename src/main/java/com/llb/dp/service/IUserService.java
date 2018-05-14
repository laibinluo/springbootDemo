package com.llb.dp.service;

import com.llb.dp.model.User;

import java.util.List;

/**
 * created by robin on 2018/5/11.
 */

public interface IUserService {
    List<User> findAllUser(int pageNum, int pageSize);

    int insert(User user);
}
