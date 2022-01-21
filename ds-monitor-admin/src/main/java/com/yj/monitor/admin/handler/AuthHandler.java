package com.yj.monitor.admin.handler;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.domain.Token;
import com.yj.monitor.api.util.Rc4Util;
import org.springframework.stereotype.Component;

/**
 * @Author gaolei
 * @Date 2022/1/21 下午12:48
 * @Version 1.0
 */
@Component
public class AuthHandler {

    /**
     * todo
     * 1 list
     * 2 config
     * @param encodeToken
     * @return
     */
    public Boolean validToken(String encodeToken) {
        String authToken = Rc4Util.decrypt(encodeToken);
        Token token = JSON.parseObject(authToken, Token.class);
        return "gaoleif1e629".equals(token.getAccessKey()) && "123654".equals(token.getSecret());
    }


}
