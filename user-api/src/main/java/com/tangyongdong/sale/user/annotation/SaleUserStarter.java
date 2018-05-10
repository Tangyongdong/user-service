package com.tangyongdong.sale.user.annotation;

import com.tangyongdong.sale.user.api.fallback.RabbitApiFallback;
import com.tangyongdong.sale.user.api.fallback.UserApiFallback;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangyongdong
 * @create 2018-05-08 13:13
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Import({FeignClientConfiguration.class, UserApiFallback.class, RabbitApiFallback.class})
public @interface SaleUserStarter {
}
