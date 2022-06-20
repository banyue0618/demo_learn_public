package com.banyue.dubbo.common.user.service;

import com.banyue.dubbo.common.user.dto.User;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author: zhangsp
 * @date 2022/6/17 22:11
 * @Version 1.0
 */
public interface UserService {

    User getUserById(Long id);

}
