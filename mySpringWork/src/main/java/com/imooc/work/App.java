package com.imooc.work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        UserService userService = context.getBean(UserService.class);
        userService.doSomething();
        ItemService itemService = context.getBean(ItemService.class);
        itemService.callUser();
    }
}
