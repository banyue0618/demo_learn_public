package com.example.demo.common.config.security.filter;

import com.example.demo.entity.system.RoleMenu;
import com.example.demo.service.system.RoleMenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/** FilterInvocationSecurityMetadataSource 权限资源过滤器接口 继承了 SecurityMetadataSource（权限资源接口）
 *
 * Spring Security是通过SecurityMetadataSource来加载访问时所需要的具体权限；Metadata是元数据的意思
 * @author banyue
 * date 2020-06-02
 *
 * 自定义权限资源过滤器，实现动态的权限验证
 * 当访问一个url时，返回这个url所需要的访问权限
 *
 */
@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final Logger log = LoggerFactory.getLogger(MyFilterInvocationSecurityMetadataSource.class);

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Autowired
    private RoleMenuService roleMenuService;

    /**
     * Accesses the {@code ConfigAttribute}s that apply to a given secure object.
     *
     * @param object the object being secured
     * @return the attributes that apply to the passed in secured object. Should return an
     * empty collection if there are no applicable attributes.
     * 返回本次访问需要的权限，可以有多个权限
     * 如果本方法返回null的话，意味着当前这个请求不需要任何角色就能访问
     *
     * @throws IllegalArgumentException if the passed object is not of a type supported by
     *                                  the <code>SecurityMetadataSource</code> implementation
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // object是一个URL，被用户请求的url
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        List<RoleMenu> menuList = roleMenuService.selectMenuRoleListForGlobal();
        if(null != menuList && menuList.size()>0){
            Map<String, List<RoleMenu>> listMap = menuList.stream().collect(Collectors.groupingBy(RoleMenu::getMenuId));
            for(Map.Entry<String, List<RoleMenu>> entry : listMap.entrySet()){
                if(antPathMatcher.match(entry.getKey(), requestUrl)){
                    int size = entry.getValue().size();
                    String[] values = new String[size];
                    for (int i = 0; i < size; i++) {
                        values[i] = entry.getValue().get(i).getRoleId();
                    }
                    log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, values);
                    return SecurityConfig.createList(values);
                }
            }
        }
        //此处做逻辑控制，如果没有匹配上的，返回一个默认具体权限，防止漏缺资源配置
        log.info("当前访问路径是{},这个url所需要的访问权限是{}", requestUrl, "ROLE_LOGIN");
        return SecurityConfig.createList("ROLE_LOGIN");
    }

    /**
     * If available, returns all of the {@code ConfigAttribute}s defined by the
     * implementing class.
     * <p>
     * This is used by the { AbstractSecurityInterceptor} to perform startup time
     * validation of each {@code ConfigAttribute} configured against it.
     *
     * 此处方法如果做了实现，返回了定义的权限资源列表，
     * Spring Security会在启动时校验每个ConfigAttribute是否配置正确
     * 如果不需要校验，这里实现方法，方法体直接返回null即可
     *
     * @return the {@code ConfigAttribute}s or {@code null} if unsupported
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    /**
     * Indicates whether the {@code SecurityMetadataSource} implementation is able to
     * provide {@code ConfigAttribute}s for the indicated secure object type.
     *
     * @param clazz the class that is being queried
     *              方法返回类对象是否支持校验
     *              web项目一般使用FilterInvocation来判断，或者直接返回true
     *
     * @return true if the implementation can process the indicated class
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
