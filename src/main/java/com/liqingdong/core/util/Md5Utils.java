package com.liqingdong.core.util;

import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;

/**
 * @author liqingdong
 * @since 2019/01/29 22:38
 */
public class Md5Utils {
    private static final Charset charset = Charset.forName("UTF-8");

    public static String encode(String raw) {
        return DigestUtils.md5DigestAsHex(raw.getBytes(charset));
    }
}
