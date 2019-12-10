package com.book.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository  //通过spring注解定义一个DAO
public class AdminDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String MATCH_ADMIN_SQL="SELECT COUNT(*) FROM admin where admin_id = ? and password = ? ";
    private static final String RE_PASSWORD_SQL="UPDATE admin set password = ? where admin_id = ? ";
    private static final String GET_PASSWD_SQL="SELECT password from admin where admin_id = ?";

    public int getMatchCount(int adminId,String password){
        return jdbcTemplate.queryForObject(MATCH_ADMIN_SQL,new Object[]{adminId,password},Integer.class);// newobject[]为参数占位符对应的参数数组，最后一位为查询结果的处理回调接口
    }  //查询对象是否存在

    public int rePassword(int adminId,String newPasswd){
        return jdbcTemplate.update(RE_PASSWORD_SQL,new Object[]{newPasswd,adminId});
    }
    public String getPasswd(int id){
        return jdbcTemplate.queryForObject(GET_PASSWD_SQL,new Object[]{id},String.class);
    }

}
