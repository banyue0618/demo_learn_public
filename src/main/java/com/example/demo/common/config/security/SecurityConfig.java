package com.example.demo.common.config.security;

import com.example.demo.common.config.security.filter.MyFilterInvocationSecurityMetadataSource;
import com.example.demo.common.config.security.handler.MyAccessDeniedHandler;
import com.example.demo.common.config.security.handler.MyAuthenticationFailHandler;
import com.example.demo.common.config.security.handler.MyAuthenticationSuccessHandler;
import com.example.demo.common.config.security.handler.MyLogoutSuccessHandler;
import com.example.demo.common.config.security.manager.MyAccessDecisionManager;
import com.example.demo.common.config.security.provider.MyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * 配置身份校验入口
 * @author banyue
 * date 2020-04-16
 *
 * EnableGlobalMethodSecurity 与 EnableWebSecurity 区别
 * @EnableGlobalMethodSecurity(prePostEnabled =true) 启用Security 方法级别得权限控制
 * @EnableWebSecurity 表示启用Security
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyAuthenticationProvider provider;

    @Autowired
    private MyAuthenticationFailHandler myAuthenticationFailHandler;

    @Autowired
    private MyAuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private MyAccessDeniedHandler myAccessDeniedHandler;

    @Autowired
    private MyLogoutSuccessHandler myLogoutSuccessHandler;

    @Autowired
    private MyFilterInvocationSecurityMetadataSource myFilterInvocationSecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;


    /**
     *
     * <pre>
     * &#064;Override
     * protected void configure(AuthenticationManagerBuilder auth) {
     * 	auth
     * 	// enable in memory based authentication with a user named
     * 	// &quot;user&quot; and &quot;admin&quot;
     * 	.inMemoryAuthentication().withUser(&quot;user&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;).and()
     * 			.withUser(&quot;admin&quot;).password(&quot;password&quot;).roles(&quot;USER&quot;, &quot;ADMIN&quot;);
     * }
     *
     * </pre>
     * spring security 5版本之后 支持多种加密格式 passwordEncoder
     * @param auth the {@link AuthenticationManagerBuilder} to use
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(provider);
//        auth.inMemoryAuthentication().passwordEncoder(new MyPasswordEncoder()).withUser("user").password("123456").roles("ADMIN");
    }

    /**
     * Override this method to configure the {@link HttpSecurity}. Typically subclasses
     * should not invoke this method by calling super as it may override their
     * configuration. The default configuration is:
     *
     * <pre>
     * http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
     * </pre>
     *
     * @param http the {@link HttpSecurity} to modify
     * @throws Exception if an error occurs
     *
     * Spring Security4默认是开启CSRF的，所以需要请求中包含CSRF的token信息，可以关闭csrf（关闭后有安全漏洞） and().csrf().disable();
     *
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http); authorizeRequests().anyRequest().authenticated() antMatchers().access("hasRole('ADMIN') and hasIpAddress('192.168.1.1')")
        http
                .authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                //采用自定义权限策略
                object.setAccessDecisionManager(myAccessDecisionManager);
                object.setSecurityMetadataSource(myFilterInvocationSecurityMetadataSource);
                return object;
            }
        })
                .and()
//                .authorizeRequests().anyRequest().access("@rbacService.hasPermission(request,authentication)")
//                .and()
                .formLogin().loginProcessingUrl("/postLogin").usernameParameter("loginName").passwordParameter("password")
                .successHandler(myAuthenticationSuccessHandler)
                .failureHandler(myAuthenticationFailHandler)
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .sessionManagement().invalidSessionUrl("/login/invalid")
                .and()
                .exceptionHandling().accessDeniedHandler(myAccessDeniedHandler)
                .and()
                .logout().logoutUrl("/logout").logoutSuccessHandler(myLogoutSuccessHandler);
        http.csrf().disable();

    }

    /**
     * Override this method to configure {@link WebSecurity}. For example, if you wish to
     * ignore certain requests.
     *
     * 配置放行的资源
     *
     * @param web
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**", "/captcha", "/favicon.ico");
    }
}
