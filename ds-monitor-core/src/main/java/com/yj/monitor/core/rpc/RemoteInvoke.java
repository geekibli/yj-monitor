package com.yj.monitor.core.rpc;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.constant.MonitorMethods;
import com.yj.monitor.api.constant.RemoteInvokeStatus;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rsp.RemoteInvokeRspVO;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午4:39
 * @Version 1.0
 */
@RestController
public class RemoteInvoke {

    /**
     * 1、请求参数 返回参数
     * 2、调用方法校验 不能任何方法都能反射调用
     */
    @ResponseBody
    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public RemoteInvokeRspVO invoke(@RequestBody RemoteMonitorReqVO reqVO) {
        return doInvoke(reqVO);
    }

    private RemoteInvokeRspVO doInvoke(RemoteMonitorReqVO reqVO) {
        System.err.println("reqVO " + JSON.toJSONString(reqVO));
        try {
            Class<?> aClass = Class.forName(reqVO.getClassName());
            Method method = aClass.getMethod(reqVO.getMethod());
            Object invoke = method.invoke(aClass, reqVO.getParams());
            return RemoteInvokeRspVO.successData(invoke);
        } catch (ClassNotFoundException e) {
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.ILLEGAL_ARGUMENT, "class not found");
        } catch (NoSuchMethodException e) {
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.NOT_FOUND, "method not found");
        } catch (IllegalAccessException e) {
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.NOT_ACCESS, "Illegal access");
        } catch (InvocationTargetException e) {
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.INTERNET_ERROR, "Invocation exception");
        }
    }

}
