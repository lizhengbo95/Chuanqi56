package com.chuanqi56.logistics.parameters;

import android.text.TextUtils;

import com.chuanqi56.logistics.application.MyApplication;
import com.chuanqi56.logistics.utils.CommonUtils;
import com.chuanqi56.logistics.utils.MD5;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @Description: HTTP网络接口请求参数——用户相关
 * @author lizhengbo95
 * @date 2015-9-11 下午5:29:51
 * 
 */
public class UserParameters extends BaseParameters {

	public UserParameters() {
		super();
	}

	
	/**
	 * 核对验证码，登录
	 * @param validateNum
	 * @param invitationNum
	 * @return
	 */
	public Map<String, String> user_check_rand_para_map(String checkSession, String validateNum, String invitationNum) {
		StringBuilder sb = new StringBuilder();
		sb.append(mPrivate_Key);
		sb.append(mApp_ID);
		sb.append(checkSession);
		sb.append(validateNum);
		if (!TextUtils.isEmpty(invitationNum)) {
			sb.append(invitationNum);
		}

		// （版本：V3.0.0；作者：lizhengbo95；时间：2017/5/4 17:55 ）说明：
		String androidId = CommonUtils.getAndroidId(MyApplication.getContext());
		sb.append("Android");
		sb.append(androidId);

		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", mApp_ID);
		map.put("session", checkSession);
		map.put("rand", validateNum);
		if (!TextUtils.isEmpty(invitationNum)) {
			map.put("invitation", invitationNum);
		} 
		map.put("sign", MD5.getSign(sb.toString()));

		map.put("type", "Android");
		map.put("device_id", androidId);
		return map;
	}

	/**
	 * 获取验证码，前端注册和登录的逻辑相同，均通过短信验证码，不使用自定义密码。
	 * 
	 * @param iPhoneNum
	 * @return
	 */
	public Map<String, String> get_validate_para_map(String iPhoneNum) {
		String secretStr = CommonUtils.genSecretStr();
		StringBuilder sb = new StringBuilder();
		sb.append(mPrivate_Key);
		sb.append(mApp_ID);
		sb.append(iPhoneNum);
		sb.append(secretStr);

		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", mApp_ID);
		map.put("number", iPhoneNum);
		map.put("secret", secretStr);
		map.put("sign", MD5.getSign(sb.toString()));
		return map;
	}

	/**
	 * 用户注销登录
	 * @return
	 */
	public Map<String, String> get_login_out_para_map() {
		String secretStr = CommonUtils.genSecretStr();
		StringBuilder sb = new StringBuilder();
		sb.append(mPrivate_Key);
		sb.append(mApp_ID);
		sb.append(mSession);
		sb.append(secretStr);

		Map<String, String> map = new HashMap<String, String>();
		map.put("app_id", mApp_ID);
		map.put("session", mSession);
		map.put("secret", secretStr);
		map.put("sign", MD5.getSign(sb.toString()));
		return map;
	}
	/**
	 * 意见反馈
	 * @return
	 */
	public Map<String, String> get_feedback_para_map(String voice) {
//		String secretStr = CommonUtils.genSecretStr();
//		StringBuilder sb = new StringBuilder();
//		sb.append(mPrivate_Key);
//		sb.append(mApp_ID);
//		sb.append(mSession);
//		sb.append(voice);
//
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("app_id", mApp_ID);
//		map.put("session", mSession);
//		map.put("voice", voice);
//		map.put("sign", getSign(sb.toString()));

		StringBuilder sb = getStringBuilder(true);
		sb.append(voice);

		Map<String, String> map = getParametersMap(true, sb);
		map.put("voice", voice);
		return map;
	}

	/**
	 * 获取商品信息(便利店)
	 * @return
	 */
	public Map<String, String> getBarcodeSku(String shop_id,String barcode) {
		String secretStr = CommonUtils.genSecretStr();
		StringBuilder sb = new StringBuilder();
//		sb.append(mPrivate_Key);
//		sb.append(mApp_ID);
//		sb.append(mSession);
//		sb.append(secretStr);
		sb.append(shop_id);
		sb.append(barcode);

		Map<String, String> map = new HashMap<String, String>();
//		map.put("app_id", mApp_ID);
//		map.put("session", mSession);
//		map.put("secret", secretStr);
		map.put("shop_id", shop_id);
		map.put("barcode", barcode);
//		map.put("sign", MD5.getSign(sb.toString()));
		return map;
	}
}
