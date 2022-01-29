package com.yj.monitor.admin.handler;

import com.google.common.base.Throwables;
import com.yj.monitor.admin.cache.ExceptionCache;
import com.yj.monitor.admin.exception.BizException;
import com.yj.monitor.admin.exception.ForbiddenException;
import com.yj.monitor.admin.exception.UnauthorizedException;
import com.yj.monitor.admin.exception.UriResourceNotFoundException;
import com.yj.monitor.api.domain.HttpRequestStatus;
import com.yj.monitor.api.rsp.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

/**
 * @author gaolei
 */
@ControllerAdvice
public class MonitorExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(MonitorExceptionHandler.class);

    @Value("${spring.servlet.multipart.max-file-size:20MB}")
    private String fileSize;

    @Resource
    private ExceptionCache exceptionCache;

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public Response unauthorized(HttpServletRequest req, UnauthorizedException e) {
        printWarn(req, e);
        return Response.build(HttpRequestStatus.UNAUTHORIZED);
    }

    /**
     * 无权限异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ResponseBody
    public Response forbidden(HttpServletRequest req, ForbiddenException e) {
        printWarn(req, e);
        return Response.build(HttpRequestStatus.FORBIDDEN);
    }

    /**
     * 参数错误异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response illegalArgument(HttpServletRequest req, IllegalArgumentException e) {
        printSummaryWarn(req, e);
        return Response.build(HttpRequestStatus.ILLEGAL_ARGUMENT.getCode(), e.getMessage());
    }


    @ExceptionHandler(BizException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Response yjException(HttpServletRequest req, BizException e) {
        printError(req, e);
        // TODO record and alarm
        // weChatHookService.asyncSafeAlarm(e.getCode(), e, MDC.getCopyOfContextMap().get(Constants.TRACE_ID), true);
        exceptionCache.saveLog(e);
        return Response.build(e.getCode(), e.getMsg());
    }

    /**
     * 参数缺失异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response missingServletRequestParameter(HttpServletRequest req, MissingServletRequestParameterException e) {
        printSummaryWarn(req, e);
        return Response.build(HttpRequestStatus.ILLEGAL_ARGUMENT.getCode(), e.getMessage());
    }

    /**
     * 请求方法不合法
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public Response missingServletRequestParameter(HttpServletRequest req, HttpRequestMethodNotSupportedException e) {
        printSummaryWarn(req, e);
        return Response.build(HttpRequestStatus.METHOD_NOT_ALLOW.getCode(), e.getMessage());
    }

    /**
     * 请求的Uri资源不存在
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(UriResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Response uriResourceNotFound(HttpServletRequest req, UriResourceNotFoundException e) {
        printSummaryWarn(req, e);
        return Response.build(HttpRequestStatus.NOT_FOUND.getCode(), e.getMessage());
    }

    /**
     * 参数不合法
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response methodArgumentNotValid(HttpServletRequest req, MethodArgumentNotValidException e) {
        printSummaryWarn(req, e);
        Optional<String> msgOp = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .findFirst();
        return Response.build(HttpRequestStatus.ILLEGAL_ARGUMENT.getCode(), msgOp.orElse(""));
    }

    /**
     * 请求Content-type不支持
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public Response httpMediaTypeNotSupported(HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
        printSummaryWarn(req, e);
        return Response.build(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), e.getMessage());
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Response missingRequestHeader(HttpServletRequest req, MissingRequestHeaderException e) {
        printSummaryWarn(req, e);
        return Response.build(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }

    /**
     * 默认系统异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public Response defaultException(HttpServletRequest req, Exception e) {
        printError(req, e);
        return Response.error(HttpRequestStatus.INTERNAL_SERVER_ERROR.getMsg());
    }

    private void printWarn(HttpServletRequest req, Exception e) {
        String print = print(req, e);
        logger.warn(print);
    }

    private void printError(HttpServletRequest req, Exception e) {
        String print = print(req, e);
        logger.error(print);
    }

    private void printSummaryWarn(HttpServletRequest req, Exception e) {
        String print = printSummary(req, e);
        logger.warn(print);
    }

    /**
     * 文件大小限制异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseBody
    public Response fileSizeLimitException(HttpServletRequest req, Exception e) {
        printSummaryWarn(req, e);
        return Response.build(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE.value(), "文件超出最大支持(" + fileSize + ")");
    }


    public static String print(HttpServletRequest request, Throwable e) {
        StringBuffer sb = new StringBuffer();
        sb.append(Throwables.getStackTraceAsString(e));
        return sb.toString();
    }

    public static String printSummary(HttpServletRequest request, Throwable e) {
        StringBuffer sb = new StringBuffer();
        sb.append(e.getMessage());
        return sb.toString();
    }
}