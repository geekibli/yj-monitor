package com.yj.monitor.api.domain;

import java.io.Serializable;

/**
 * @Author gaolei
 * @Date 2022/1/19 下午5:58
 * @Version 1.0
 */
public class Method implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cName;
    private String mName;
    private String mDesc;

    public Method() {
    }

    public Method(String cName, String mName, String mDesc) {
        this.cName = cName;
        this.mName = mName;
        this.mDesc = mDesc;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmDesc() {
        return mDesc;
    }

    public void setmDesc(String mDesc) {
        this.mDesc = mDesc;
    }

    @Override
    public String toString() {
        return "Method{" +
                "cName='" + cName + '\'' +
                ", mName='" + mName + '\'' +
                ", mDesc='" + mDesc + '\'' +
                '}';
    }
}
