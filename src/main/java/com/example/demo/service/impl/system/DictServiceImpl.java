package com.example.demo.service.impl.system;

import com.example.demo.entity.system.Dict;
import com.example.demo.mapper.system.DictDao;
import com.example.demo.service.system.DictService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 字典表 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Service
public class DictServiceImpl extends ServiceImpl<DictDao, Dict> implements DictService {

}
