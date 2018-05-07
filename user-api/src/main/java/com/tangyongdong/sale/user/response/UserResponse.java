package com.tangyongdong.sale.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangyongdong
 * @create 2018-05-07 10:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private String id;
    private String token;
    private String name;
    private String phone;
    private Integer gender;
    private Integer admin;
    private String accessToken;
}
