package com.tangyongdong.play.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author tangyongdong
 * @create 2018-05-07 10:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String token;
    private String name;
    private String phone;
    private Integer gender;
    private Integer sysAdmin;
    private String accessToken;
}
