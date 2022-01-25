package com.yj.monitor.core.rpc;

import com.alibaba.fastjson.JSON;
import com.yj.monitor.api.constant.RemoteInvokeStatus;
import com.yj.monitor.api.req.RemoteMonitorReqVO;
import com.yj.monitor.api.rsp.RemoteInvokeRspVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final Logger logger = LoggerFactory.getLogger(RemoteInvoke.class);

    /**
     * 1、请求参数 返回参数
     * 2、调用方法校验 不能任何方法都能反射调用
     */
    @ResponseBody
    @RequestMapping(value = "/monitor", method = RequestMethod.POST)
    public RemoteInvokeRspVO invoke(@RequestBody RemoteMonitorReqVO reqVO) {
        return doInvoke(reqVO);
    }

    @ResponseBody
    @RequestMapping(value = "/actuator-metrics", method = RequestMethod.POST)
    public Object metrics(@RequestBody RemoteMonitorReqVO reqVO) {
        return metric(reqVO);
    }

    private RemoteInvokeRspVO doInvoke(RemoteMonitorReqVO reqVO) {
        logger.info("Remote invoke req: {}", JSON.toJSONString(reqVO));
        try {
            Object invoke = doReflect(reqVO);
            return RemoteInvokeRspVO.successData(invoke);
        } catch (ClassNotFoundException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.ILLEGAL_ARGUMENT, "class not found");
        } catch (NoSuchMethodException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.NOT_FOUND, "method not found");
        } catch (IllegalAccessException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.NOT_ACCESS, "Illegal access");
        } catch (InvocationTargetException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.INTERNET_ERROR, "Invocation exception");
        } catch (InstantiationException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.INTERNET_ERROR, "Reflect instance error!");
        }
    }

    private Object metric(RemoteMonitorReqVO reqVO) {
        logger.info("Remote invoke req: {}", JSON.toJSONString(reqVO));
        try {
            return doReflect(reqVO);
        } catch (ClassNotFoundException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.ILLEGAL_ARGUMENT, "class not found");
        } catch (NoSuchMethodException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.NOT_FOUND, "method not found");
        } catch (IllegalAccessException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.NOT_ACCESS, "Illegal access");
        } catch (InvocationTargetException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.INTERNET_ERROR, "Invocation exception");
        } catch (InstantiationException e) {
            logger.error("Reflect invoke error!", e);
            return RemoteInvokeRspVO.build(RemoteInvokeStatus.INTERNET_ERROR, "Reflect instance error!");
        }
    }

    private Object doReflect(RemoteMonitorReqVO reqVO) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        if (null == reqVO.getParams() || 0 == reqVO.getParams().length) {
            return reflectNoParam(reqVO);
        } else {
            return reflectWithParam(reqVO);
        }
    }


    private Object reflectNoParam(RemoteMonitorReqVO reqVO) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName(reqVO.getClassName());
        Object o = aClass.newInstance();
        Method method = aClass.getMethod(reqVO.getMethod());
        Object[] params = reqVO.getParams();
        return method.invoke(o);
    }

    private Object reflectWithParam(RemoteMonitorReqVO reqVO) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName(reqVO.getClassName());
        Object o = aClass.newInstance();
        Method method = aClass.getDeclaredMethod(reqVO.getMethod(), Integer.class);
        Object[] params = reqVO.getParams();
        return method.invoke(o, params);
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        Class<?> aClass = Class.forName("com.yj.monitor.core.service.ActuatorMetricService");
        Object o = aClass.newInstance();
        Method method = aClass.getMethod("getGcLiveDataSize", Integer.class);
        Object [] params = new Object[1];
        params[0] = 10000;
        method.invoke(o, params);
    }

}
