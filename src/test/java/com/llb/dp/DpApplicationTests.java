package com.llb.dp;

import com.llb.dp.model.User;
import com.llb.dp.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DpApplicationTests {

    @Autowired
    IUserService  iUserService;

	@Test
	public void contextLoads() {
	}

    @Test
    public void testSelectUsers(){
        List<User> list  = iUserService.findAllUser(1, 10);

        for (User user: list){
            System.out.println(user);
        }
    }

    @Test
    public void testinsert(){
	    User user = new User();
	    user.setId(124);
	    user.setUsername("AAAAAAAAAAAA");

        iUserService.insert(user);
    }
}
