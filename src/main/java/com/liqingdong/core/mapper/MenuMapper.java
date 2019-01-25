package com.liqingdong.core.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liqingdong
 * @since 2019/01/25 17:33
 */
@Repository
public class MenuMapper {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String,Object>> getMenus(){
        return jdbcTemplate.query("select * from t_sys_menu", (rs, rowNum) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("MENU_NAME",rs.getString("MENU_NAME"));
            map.put("MENU_CODE",rs.getString("MENU_CODE"));
            map.put("MENU_DESC",rs.getString("MENU_DESC"));
            map.put("IS_LEAF",rs.getString("IS_LEAF"));
            map.put("MENU_LEVEL",rs.getString("MENU_LEVEL"));
            map.put("CREATE_DATE",rs.getString("CREATE_DATE"));
            map.put("CREATE_BY",rs.getString("CREATE_BY"));

            return map;
        });
    }
}
