package com.example.demo.service.impl.system;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.example.demo.entity.system.Menu;
import com.example.demo.mapper.system.MenuDao;
import com.example.demo.service.system.MenuService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements MenuService {

    /**
     * 默认查询全局有效菜单记录
     *
     * @return
     */
    @Override
    public List<Menu> selectMenuListForGlobal() {
        return this.baseMapper.selectList(new EntityWrapper<Menu>().eq("del_flag", "0"));
    }
}
