package com.example.demo.service.system;

import com.example.demo.entity.system.Menu;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * <p>
 * 菜单表 服务类
 * </p>
 *
 * @author banyue
 * @since 2020-05-19
 */
public interface MenuService extends IService<Menu> {

    /**
     * 默认查询全局有效菜单记录
     * @return
     */
    List<Menu> selectMenuListForGlobal();
}
