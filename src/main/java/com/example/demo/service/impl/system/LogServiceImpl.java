package com.example.demo.service.impl.system;

import com.example.demo.entity.system.Log;
import com.example.demo.mapper.system.LogDao;
import com.example.demo.service.system.LogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogDao, Log> implements LogService {

}
