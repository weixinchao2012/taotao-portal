package com.taotao.portal.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.taotao.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.utils.HttpClientUtil;

@Service
public class UserServiceImpl implements UserService {
	
	@Value("${SSO_BASE_URL}")
	public String SSO_BASE_URL="";
	
	@Value("${SSO_USER_TOKEN}")
	private String SSO_USER_TOKEN="";
	
	@Value("${SSO_PAGE_LOGIN}")
	public String SSO_PAGE_LOGIN="";
	

	public String getSSO_BASE_URL() {
		return SSO_BASE_URL;
	}


	public String getSSO_USER_TOKEN() {
		return SSO_USER_TOKEN;
	}



	public String getSSO_PAGE_LOGIN() {
		return SSO_PAGE_LOGIN;
	}


	@Override
	public TbUser getUserByToken(String token) {
		try {
			//调用sso系统的服务，根据token取用户信息
			String json = HttpClientUtil.doGet(SSO_BASE_URL + SSO_USER_TOKEN + token);
			//把json转换成TaotaoREsult
			TaotaoResult result = TaotaoResult.formatToPojo(json, TbUser.class);
			if (result.getStatus() == 200) {
				TbUser user = (TbUser) result.getData();
				return user;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}