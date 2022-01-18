package com.example.demo.common.config.security.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.example.demo.entity.request.system.UserInfo;
import com.example.demo.entity.system.AccountEntity;
import com.example.demo.entity.system.User;
import com.example.demo.service.system.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author banyue
 * date 2020-04-16
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * Locates the user based on the username. In the actual implementation, the search
     * may possibly be case sensitive, or case insensitive depending on how the
     * implementation instance is configured. In this case, the <code>UserDetails</code>
     * object that comes back may have a username that is of a different case than what
     * was actually requested..
     *
     * @param userName the username identifying the user whose data is required.
     * @return a fully populated user record (never <code>null</code>)
     * @throws UsernameNotFoundException if the user could not be found or the user has no
     *                                   GrantedAuthority
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AccountEntity user = userService.loadUserByUsername(userName);
        if(user == null ){
            throw new UsernameNotFoundException("");
        }
        return new UserInfo(userName, user.getPassword(), user.getRoles());
    }
}
