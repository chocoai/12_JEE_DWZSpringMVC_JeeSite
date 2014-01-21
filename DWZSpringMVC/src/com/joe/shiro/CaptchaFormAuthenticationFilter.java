package com.joe.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.util.WebUtils;

import com.joe.SecurityConstants;

/**
 * 
 */

public class CaptchaFormAuthenticationFilter extends BaseFormAuthenticationFilter {

	private String captchaParam = SecurityConstants.CAPTCHA_KEY;

	public String getCaptchaParam() {
		return captchaParam;
	}

	protected String getCaptcha(ServletRequest request) {
		System.out.println("in CaptchaFormAuthenticationFilter.getCaptcha() ");
		return WebUtils.getCleanParam(request, getCaptchaParam());
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request,
			ServletResponse response) {
		System.out.println("in CaptchaFormAuthenticationFilter.createToken() ");
		String username = getUsername(request);
		String password = getPassword(request);
		String captcha = getCaptcha(request);
		boolean rememberMe = isRememberMe(request);
		String host = getHost(request);
		return new CaptchaUsernamePasswordToken(username, password, rememberMe,
				host, captcha);
	}

}
