package com.very.ok.result;

public class Result {
    private static final String success_code = "0";
    private static final String success_msg = "ok";

    private static final String fail_code = "1";

    private String code;
    private String msg;
    private Object data;

    public Result(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static Result ok() {
        return new Result(success_code, success_msg);
    }

    public static Result ok(Object data) {
        Result result = new Result(success_code, success_msg);
        result.setData(data);
        return result;
    }

    public static Result fail(String code, String msg) {
        return new Result(code, msg);
    }

    public static Result fail(String msg) {
        return new Result(fail_code, msg);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }
}
