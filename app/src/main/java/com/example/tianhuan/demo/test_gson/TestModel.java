package com.example.tianhuan.demo.test_gson;

/**
 * Created by tianhuan on 17-3-10.
 */

public class TestModel {


    /**
     * errcode : 100
     * errmsg : 操作成功
     */

    private int errcode;
    private String errmsg;


    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(int errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    @Override
    public String toString() {
        return "TestModel{" +
                "errcode=" + errcode +
                ", errmsg='" + errmsg + '\'' +
                '}';
    }
}
