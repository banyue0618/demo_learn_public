package com.banyue.dubbo.user.service;

import com.banyue.dubbo.common.user.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/3/27 9:03
 * @Version 1.0
 */
@Repository
public interface UserDao extends JpaRepository<User, Long> {
}
