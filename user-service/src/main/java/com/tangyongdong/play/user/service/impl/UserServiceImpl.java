package com.tangyongdong.play.user.service.impl;

import com.tangyongdong.base.api.IdWorkerApi;
import com.tangyongdong.base.common.exception.BusinessException;
import com.tangyongdong.play.user.constant.DefualtStatusEnum;
import com.tangyongdong.play.user.constant.RedisConstant;
import com.tangyongdong.play.user.mapper.UserLoginMapper;
import com.tangyongdong.play.user.mapper.UserMapper;
import com.tangyongdong.play.user.service.UserService;
import com.tangyongdong.play.user.config.BusinessErrorCode;
import com.tangyongdong.play.user.domain.db.User;
import com.tangyongdong.play.user.domain.db.UserLogin;
import com.tangyongdong.play.user.response.UserResponse;
import com.tangyongdong.play.user.util.DateUtil;
import com.tangyongdong.play.user.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author tangyongdong
 * @create 2018-05-07 11:03
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private IdWorkerApi idWorkerApi;

    @Override
    public UserResponse userLogin(String phone) {
        //1.查询用户信息
        User user = userMapper.selectByPhone(phone);
        if (user == null) {
            log.info("user not found,phone:{}", phone);
            throw new BusinessException(BusinessErrorCode.USER_NOT_FOUND);
        }

        //2.记录用户登录信息
        Date date = new Date();
        UserLogin userLogin = UserLogin.builder()
                .id(idWorkerApi.getId().getData())
                .userId(user.getId())
                .status(DefualtStatusEnum.VALID.getStatus())
                .loginTime(date)
                .build();
        int count = userLoginMapper.insert(userLogin);
        if (count != 1) {
            log.info("user login error,userId:{}", user.getId());
            throw new BusinessException(BusinessErrorCode.USER_LOGIN_ERROR);
        }

        //3.生成accessToken并存redis 6小时
        String dateStr = DateUtil.date2Long(date);
        String accessToken = MD5Util.sign(user.getToken(), dateStr);
        redisTemplate.opsForValue().set(RedisConstant.USER_ACCESS_TOKEN_KEY.concat(user.getToken()), accessToken, 6L, TimeUnit.HOURS);

        //4.返回
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .token(user.getToken())
                .name(user.getName())
                .phone(phone)
                .gender(user.getGender())
                .sysAdmin(user.getSysAdmin())
                .accessToken(accessToken)
                .build();

        return userResponse;
    }

    @Override
    public Boolean accessTokenAuth(String userToken, String accessToken) {
        String trueAccessToken = redisTemplate.opsForValue().get(RedisConstant.USER_ACCESS_TOKEN_KEY.concat(userToken));
        if (StringUtils.isEmpty(trueAccessToken) || !trueAccessToken.equals(accessToken)) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }
}
