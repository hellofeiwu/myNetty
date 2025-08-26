package com.imooc.work;

import org.springframework.beans.factory.annotation.Autowired;

public class ItemService {
    @Autowired
    UserService userService;

    public void callUser(){
        userService.doSomething();
    }
}
