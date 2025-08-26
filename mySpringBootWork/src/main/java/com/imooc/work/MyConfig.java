package com.imooc.work;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig {

    @Bean
    public ItemService getItemService(){
        return new ItemService();
    }
}
