package com.zos.security.core.social.weixin.connect;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

import com.zos.security.core.social.weixin.api.Weixin;
import com.zos.security.core.social.weixin.api.WeixinUserInfo;

/**
 * 微信 api 适配器, 将微信 api 的数据模型转为 spring social 的标准模型
 *
 * @author 01Studio
 *
 */
public class WeixinAdapter implements ApiAdapter<Weixin> {
	
	private String openId;
	
	public WeixinAdapter() {}
	
	public WeixinAdapter(String openId){
		this.openId = openId;
	}

	/**
	 * 连通性测试
	 *
	 * @param api 微信操作用户信息接口
	 * @return 布尔型
	 */
	@Override
	public boolean test(Weixin api) {
		return true;
	}

	/**
	 * 适配数据
	 *
	 * @param api 微信操作用户信息接口
	 * @param values 标准模型
	 */
	@Override
	public void setConnectionValues(Weixin api, ConnectionValues values) {
		WeixinUserInfo profile = api.getUserInfo(openId);
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());
	}

	/**
	 * 加载用户主页数据
	 *
	 * @param api 微信操作用户信息接口
	 * @return 用户主页信息
	 */
	@Override
	public UserProfile fetchUserProfile(Weixin api) {
		return null;
	}

	/**
	 * 更新微信状态
	 *
	 * @param api 微信操作用户信息接口
	 * @param message 消息
	 */
	@Override
	public void updateStatus(Weixin api, String message) {
		//do nothing
	}

}
