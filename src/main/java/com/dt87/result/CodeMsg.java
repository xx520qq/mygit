package com.dt87.result;

public class CodeMsg {
	private int code;
	private String msg;
	
	//通用异常
	public static CodeMsg SUCCESS = new CodeMsg(0, "success");
	public static CodeMsg SERVER_ERROR = new CodeMsg(500100, "服务端异常");
	//登录模块 5002XX
	public static CodeMsg MOBLE_NOFOUND = new CodeMsg(500200, "手机号不存在");
	public static CodeMsg MOBLE_ERROR = new CodeMsg(500230, "手机号格式错误");
	public static CodeMsg PASSWORD_EROOR = new CodeMsg(500210, "您输入的密码不正确，请重新输入");
	public static CodeMsg PASSWORD_ISNULL = new CodeMsg(500220, "密码不能为空");
	//商品模块 5003XX
	
	//订单模块 5004XX
	
	//秒杀模块 5005XX
	
	
	private CodeMsg(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public int getCode() {
		return code;
	}
	public String getMsg() {
		return msg;
	}
}
