package com.tangyongdong.sale.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.tangyongdong.sale.base.exception.BusinessException;
import com.tangyongdong.sale.user.config.BusinessErrorCode;
import com.tangyongdong.sale.user.constant.DefualtStatusEnum;
import com.tangyongdong.sale.user.domain.db.User;
import com.tangyongdong.sale.user.domain.db.UserLogin;
import com.tangyongdong.sale.user.mapper.UserLoginMapper;
import com.tangyongdong.sale.user.mapper.UserMapper;
import com.tangyongdong.sale.user.response.UserResponse;
import com.tangyongdong.sale.user.service.UserService;
import com.tangyongdong.sale.user.util.DateUtil;
import com.tangyongdong.sale.user.util.IdWorker;
import com.tangyongdong.sale.user.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author tangyongdong
 * @create 2018-05-07 11:03
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserLoginMapper userLoginMapper;

    @Value("${redis.password}")
    private String redisPassword;

    @Override
    public UserResponse userLogin(String phone) {
        //1.查询用户信息
        User user = userMapper.selectByPhone(phone);
        if(user == null){
            BusinessException businessException = new BusinessException(BusinessErrorCode.USER_NOT_FOUND);
            System.out.println(JSON.toJSONString(businessException));
            throw businessException;
        }

        //2.记录用户登录信息
        Date date = new Date();
        UserLogin userLogin = UserLogin.builder()
                .id(IdWorker.unique())
                .userId(user.getId())
                .status(DefualtStatusEnum.VALID.getStatus())
                .loginTime(date)
                .build();
        int count = userLoginMapper.insert(userLogin);
        if(count != 1){
            throw new BusinessException(BusinessErrorCode.USER_LOGIN_ERROR);
        }

        //3.生成accessToken
        String dateStr = DateUtil.date2Long(date);
        String accessToken = MD5Util.sign(user.getToken(), dateStr);

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
}
