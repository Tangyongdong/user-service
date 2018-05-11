package com.tangyongdong.play.user.mapper;

import com.tangyongdong.play.user.domain.db.UserLogin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserLoginMapper {

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(String id);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insert(UserLogin record);

    /**
     * 插入
     *
     * @param record
     * @return
     */
    int insertSelective(UserLogin record);

    /**
     * 查询
     *
     * @param id
     * @return
     */
    UserLogin selectByPrimaryKey(String id);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(UserLogin record);

    /**
     * 更新
     *
     * @param record
     * @return
     */
    int updateByPrimaryKey(UserLogin record);

    /**
     * 根据userId查询用户最新登录信息
     *
     * @param userToken
     */
    UserLogin selectByUserId(String userToken);
}