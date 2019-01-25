package com.liqingdong.core;

import com.liqingdong.core.mapper.MenuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Resource
    private MenuMapper menuMapper;

    @Test
    public void contextLoads() {
        List<Map<String, Object>> menus = menuMapper.getMenus();
        System.out.println(menus.size());
    }

}

