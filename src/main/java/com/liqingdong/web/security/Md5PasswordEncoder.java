package com.liqingdong.web.security;

import com.liqingdong.core.util.Md5Utils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * 自定义密码加密
 *
 * @author liqingdong
 * @since 2019/01/29 22:46
 */
@Component
public class Md5PasswordEncoder implements PasswordEncoder {

    private static final String salt = "{DES|DONG}";

    @Override
    public String encode(CharSequence rawPassword) {
        return Md5Utils.encode(salt + rawPassword.toString() + salt);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(encode(rawPassword));
    }
}
