package com.tangyongdong.sale.user.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tangyongdong
 * @create 2018-05-08 11:07
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenAuthRequest {
    private String userToken;
    private String accessToken;
}
