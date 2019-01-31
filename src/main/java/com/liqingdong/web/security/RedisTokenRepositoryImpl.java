package com.liqingdong.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 使用Redis将RememberMeToken持久化，可供集群间切换访问
 *
 * @author liqingdong
 * @since 2019/01/31 13:24
 */
@Component
public class RedisTokenRepositoryImpl implements PersistentTokenRepository {

    private static final String redis_token_prefix = "remember-me-token:";

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    @SuppressWarnings("unchecked")
    public void createNewToken(PersistentRememberMeToken token) {
        redisTemplate.opsForValue().setIfAbsent(redis_token_prefix + token.getSeries(), token);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        PersistentRememberMeToken token = (PersistentRememberMeToken) redisTemplate.opsForValue().get(redis_token_prefix + series);
        PersistentRememberMeToken rememberMeToken = new PersistentRememberMeToken(
                token.getUsername(), series, tokenValue, lastUsed);
        redisTemplate.opsForValue().setIfAbsent(series, rememberMeToken);
    }

    @Override
    @SuppressWarnings("unchecked")
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        return (PersistentRememberMeToken) redisTemplate.opsForValue().get(redis_token_prefix + seriesId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public void removeUserTokens(String username) {
        Set<String> keys = redisTemplate.keys(redis_token_prefix + "*");
        PersistentRememberMeToken rememberMeToken;
        List<String> matchKeys = new ArrayList<>();
        for (String key : keys) {
            rememberMeToken = (PersistentRememberMeToken) redisTemplate.opsForValue().get(key);
            if (username.equals(rememberMeToken.getUsername())) matchKeys.add(key);
        }
        redisTemplate.delete(matchKeys);
    }
}
