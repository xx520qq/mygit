package com.dt87.config;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dt87.entity.MiaoshaUser;
import com.dt87.service.impl.MiaoshaUserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;


@Service
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private MiaoshaUserServiceImpl miaoshaUserServiceImpl;
	
	public boolean supportsParameter(MethodParameter parameter) {
		Class<?> clazz = parameter.getParameterType();
		return clazz== MiaoshaUser.class;
	}

	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
		
		String paramToken = request.getParameter(miaoshaUserServiceImpl.COOKIE_TOOKEN_NAME);
		String cookieToken = getCookieValue(request, miaoshaUserServiceImpl.COOKIE_TOOKEN_NAME);
		if(StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken)) {
			return null;
		}
		String token = StringUtils.isEmpty(paramToken)?cookieToken:paramToken;
		return miaoshaUserServiceImpl.getUserByToken(token,response);
	}

	private String getCookieValue(HttpServletRequest request, String cookieName) {
		Cookie[]  cookies = request.getCookies();
		if(null!=cookies) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().equals(cookieName)) {
					return cookie.getValue();
				}
			}
		}
		return null;
	}

}
