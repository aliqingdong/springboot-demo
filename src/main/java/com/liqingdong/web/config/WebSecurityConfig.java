package com.liqingdong.web.config;

import com.liqingdong.web.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * @author liqingdong
 * @since 2019/01/29 09:51
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)// 开启security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final Md5PasswordEncoder md5PasswordEncoder;

    private final CustomAuthenticationFailureHandler failureHandler;

    private final CustomAuthenticationSuccessHandler successHandler;

    private final CustomAuthenticationProvider authenticationProvider;

    private final CustomFilterSecurityInterceptor securityInterceptor;

    private final RedisTokenRepositoryImpl redisTokenRepository;

    private final CustomAjaxSessionStrategy ajaxSessionStrategy;

    @Autowired
    public WebSecurityConfig(Md5PasswordEncoder md5PasswordEncoder,
                             CustomAuthenticationFailureHandler failureHandler,
                             CustomAuthenticationSuccessHandler successHandler,
                             CustomAuthenticationProvider authenticationProvider,
                             CustomFilterSecurityInterceptor securityInterceptor,
                             RedisTokenRepositoryImpl redisTokenRepository,
                             CustomAjaxSessionStrategy ajaxSessionStrategy) {
        this.md5PasswordEncoder = md5PasswordEncoder;
        this.failureHandler = failureHandler;
        this.successHandler = successHandler;
        this.authenticationProvider = authenticationProvider;
        this.securityInterceptor = securityInterceptor;
        this.redisTokenRepository = redisTokenRepository;
        this.ajaxSessionStrategy = ajaxSessionStrategy;
    }

//    @Autowired
//    private CustomLoginFilter customLoginFilter;

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/login", "/404", "/rest/**").permitAll()
                .anyRequest().authenticated()//其他地址的访问均需验证权限
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/index")
                .failureUrl("/login")
                .successHandler(successHandler)
                .failureHandler(failureHandler)
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .and()
                .rememberMe()
                .rememberMeCookieName("CUSTOM_REMEMBER_ME")
                .key("DONG")
                .rememberMeParameter("rememberMe")
                .tokenValiditySeconds(1209600)
                .tokenRepository(redisTokenRepository)// 此处采用Redis存储RememberMeToken
                .and()
                // todo begin 这个配置还需完善，暂时通过SecurityBeanPostProcessor配置的相关策略
                .sessionManagement()
                .invalidSessionStrategy(ajaxSessionStrategy)
                .invalidSessionUrl("/login")
                .maximumSessions(1)// SessionManagementConfigurer.ConcurrencyControlConfigurer
                .expiredUrl("/login")
                .and() // SessionManagementConfigurer
                .and() // HttpSecurity
                // todo end 中间这一块配置待研究
//                .addFilterBefore(customLoginFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(securityInterceptor, FilterSecurityInterceptor.class);//添加自定义的过滤器
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
        auth.userDetailsService(userDetailsService()).passwordEncoder(md5PasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/**/*.js", "/**/*.css", "/**/*.jpg", "/**/*.png", "/**/*.gif");
    }

}
