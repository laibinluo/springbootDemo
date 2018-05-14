package com.llb.dp.controller;

import com.github.pagehelper.PageHelper;
import com.llb.dp.model.User;
import com.llb.dp.service.IUserService;
import com.llb.dp.service.imp.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.memory.HeapBlock;

/**
 * created by robin on 2018/5/11.
 */

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @ResponseBody
    @GetMapping("/all")
    public Object findAllUser(@RequestParam(name = "pageNum",required = false, defaultValue = "1") int pageNum,
                              @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        return userService.findAllUser(pageNum, pageSize);
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public int add(@RequestBody User user){
        System.out.println("=====================add =======================");
        System.out.println(user);
        return userService.insert(user);
    }

}
