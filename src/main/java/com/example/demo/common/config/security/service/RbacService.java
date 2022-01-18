package com.example.demo.common.config.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.system.UserRole;
import com.example.demo.service.system.UserRoleService;
import com.example.demo.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @author banyue
 * date 2020-05-26
 */
public interface RbacService {
    /**
     * 权限校验
     * @param request
     * @param authentication
     * @return
     */
    boolean hasPermission(HttpServletRequest request, Authentication authentication);
}
@Component("rbacService")
class RbacServiceImpl implements RbacService{

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private UserService userService;
    /**
     * 权限校验
     *
     * @param request
     * @param authentication
     * @return
     */
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {

        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
        //首先判断先当前用户是否是我们UserDetails对象。
        if(principal instanceof UserDetails){
            String userName = ((UserDetails) principal).getUsername();
            // 数据库读取 读取用户所拥有权限的所有URL 根据角色id查找
            Set<String> urls = new HashSet<>();
            userRoleService.selectList(new EntityWrapper<UserRole>().eq("user_id", userName));
            // 注意这里不能用equal来判断，因为有些URL是有参数的，所以要用AntPathMatcher来比较
            for (String url : urls) {
                if (antPathMatcher.match(url, request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }

        }
        return hasPermission;
    }
}
