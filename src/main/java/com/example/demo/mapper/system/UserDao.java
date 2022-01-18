package com.example.demo.mapper.system;

import com.example.demo.entity.system.AccountEntity;
import com.example.demo.entity.system.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author banyue
 * @since 2020-03-31
 */
public interface UserDao extends BaseMapper<User> {
    /**
     * 根据用户登录帐号查询用户以及所属角色
     * @param userName
     * @return
     */
    @Select("select t.login_name,t.password,group_concat(r.enname) as roles from sys_role r right join (SELECT u.login_name,u.password,ur.role_id from sys_user u left join sys_user_role ur on u.id = ur.user_id where u.login_name = #{userName}) t on r.id = t.role_id GROUP BY t.login_name")
    AccountEntity loadUserByUsername(@Param("userName")String userName);
}
