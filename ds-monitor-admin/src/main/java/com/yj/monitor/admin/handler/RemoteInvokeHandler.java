package com.yj.monitor.admin.handler;

import cn.hutool.http.ContentType;
import cn.hutool.http.Header;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.domain.Method;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import org.springframework.stereotype.Component;

/**
 * @Author gaolei
 * @Date 2022/1/25 下午2:21
 * @Version 1.0
 */
@Component
public class RemoteInvokeHandler {

    public String remoteInvoke(String url, Method method, Object[] param) {
        HttpResponse response = HttpUtil.createPost(url)
                .header(Header.CONTENT_TYPE, ContentType.JSON.getValue())
                .body(JSON.toJSONString(new RemoteMonitorReqVO(method.getcName(), method.getmName(), param)))
                .execute();
        return response.body();
    }

}
