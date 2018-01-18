package com.chuanqi56.logistics.rxjavaretrofit;

import android.content.Context;
import android.text.TextUtils;

import com.chuanqi56.logistics.utils.DebugLog;

/**
 * 
 * @Description: 路径常量配置界面
 * @author lizhengbo95
 * @date 2015-9-22 上午10:43:11
 * 
 */
public class ConstantUrl {
	// 与后端约定版本号，用于区分不同业务，如：兼容以前版本优惠券显示隐藏问题。
	public static final String VERSION_TAG = "159";
	// （版本：V2.6.0；作者：lizhengbo95；时间：2016/12/20 19:26 ）说明：所有接口都加入版本号control_app_version
	// （如：版本为V2.6.0传给后台的版本号为control_app_version=260_Android，修复的小版本不影响接口的保持跟大版本一样，否则改为262），提供给后台，不需加入签名
	public static final String CONTROL_APP_VERSION = "342_Android";

	//##############牢记：上生产环境时配置，打开注释
//	public static final String DEFUALT_SERVER_IP = "121.41.30.96";//服务器默认IP，当域名解析失败或者非常慢时使用
//	public static final String TD_APP_ID = "012113230E91E14E48840EEE31634352";//talkingdata 【生产环境】ID
//	public static final String TD_AD_Tracking_APP_ID = "8DA3A64F31914A74997F66FD7D1AF68F";//talkingdata 【生产环境】AD_Tracking广告监控
//	public static final String BUGLY_APPID = "900032890";//腾讯Bugly 【生产环境】ID
//	public static final boolean BUGLY_ISDEBUG = false;//腾讯Bugly 【生产环境】是否打印日志
//	public static final String BUGLY_APP_VERSION = "3.4.2_10-27";//腾讯Bugly 【生产环境】版本号
//	public static final String XIAONENG_SETTING_ID1 = "kf_9319_1484127703792";//【生产环境】xioanengsdk客服组id示例online
//	public static final String XIAONENG_GUIJI_URL = "https://yunying.urfresh.cn/web/cs_order_detail.do?orderCode=";//【生产环境】xioanengsdk客服添加轨迹的轨迹连接；
//	public static final String TINGYUN_KEY = "d97530507b3e4e08801e9b0ff00f8527";//【测试环境】听云测试key

	//##############上测试环境时，打开注释
	public static final String DEFUALT_SERVER_IP = "121.40.212.68";//测试环境，服务器默认IP，当域名解析失败或者非常慢时使用
	public static final String TD_APP_ID = "2CA31B77816A26B84210ACEFCCCB6758";//talkingdata 【测试环境】ID
	public static final String TD_AD_Tracking_APP_ID = "45B31DC77AB3446DAF45985582B64063";//talkingdata 【测试环境】AD_Tracking广告监控
	public static final String BUGLY_APPID = "900032227";//腾讯Bugly 【测试环境】ID
	public static final boolean BUGLY_ISDEBUG = true;//腾讯Bugly 【测试环境】是否打印日志
	public static final String BUGLY_APP_VERSION = "3.4.2_10-27";//腾讯Bugly 【测试环境】版本号
	public static final String XIAONENG_SETTING_ID1 = "kf_9319_1482754070093";//【测试环境】xioanengsdk客服组id示例android_test
	public static final String XIAONENG_GUIJI_URL = "https://test.yunying.urfresh.cn/web/cs_order_detail.do?orderCode=";//【测试环境】xioanengsdk客服添加轨迹的轨迹连接；
	public static final String TINGYUN_KEY = "d97530507b3e4e08801e9b0ff00f8527";//【测试环境】听云测试key

	//##############【必填】
	public static final String XIAONENG_SIETID = "kf_9319";//xioanengsdk企业id，即企业唯一标识。格式示例：kf_9979【必填】
	public static final String XIAONENG_SDKKEY = "E346E67B-4BBA-4B53-9012-475F120C8765";//xioanengsdk企业key，即小能通行密钥【必填】


	//下一代Android打包工具，100个渠道包只需要10秒钟【当打包渠道数量大于30个时，推荐使用】；
	// 如果不使用时需要在AndroidManifest中打开统计Talkingdata
	//系统计算渠道ID时，读取渠道ID的优先级如下：“多渠道打包工具”产生的渠道ID> AndroidManifest.xml中配置的渠道ID>TCAgent.init(this, "您的 App ID", "渠道 ID")方法配置的渠道ID。
//	public static final boolean IS_PACKERNG = true;
	public static final boolean IS_PACKERNG = false;


//	private static final String HOST = "app-test.urfresh.cn";
//	public static final String HOST = "app-staging.urfresh.cn";
	public static final String HOST = "https://apiitem.urfresh.cn/";
//	public static final String HOST = "https://test-apiitem.urfresh.cn/";

	private static final String PROTOCOL = "https";
	private static final String PORT = "12049";
	private static final String VERSION = "/app/v3/";//版本号
	private static final String VERSION_V5 = "/app/v5/";//新增接口版本号

	//为了设置切换模式动态获取
	private static String getDefaultHostUrl(Context context, String serverIP){
		String defaultValue;
		if(TextUtils.isEmpty(serverIP)){
//			String host = SharePreferUtils.getHost(context);
//			defaultValue = TextUtils.isEmpty(host) ? HOST : host;
			defaultValue = "";
		}else {
			defaultValue = serverIP;
		}
		DebugLog.e("当前环境：" + defaultValue);
		return PROTOCOL + "://" + defaultValue + ":" + PORT + VERSION;
	}
	public static String getFirstHandUrl(Context context, String serverIP){
		return getDefaultHostUrl(context,serverIP) + "first_hand";
	}
	public static String getHostUrl(Context context, String serverIP){
		return getDefaultHostUrl(context,serverIP) + "get_host";
	}

	// 首次握手
//	public static final String FIRST_HAND_URL = TEST_URL + "first_hand";
	public static final int FIRST_HAND_TAG = 1002;
	// 获得服务器端口地址
//	public static final String GET_HOST_URL = TEST_URL + "get_host";
	public static final int GET_HOST_TAG = 1003;

	//定位门店
	public static final String LOCATE_SHOP3_URL = VERSION_V5 + "locate_shop3";//合并后的接口
	public static final int GET_LOCATION_SHOP_TAG = 1004;

	//版本2.3.0--xiaoniu--使用了新接口
	public static final String GET_SETTING_URL = VERSION_V5 + "get_settings2";
	public static final int GET_SETTING_TAG = 1005;

	public static final String BARCODE_SKU = "wx/barcode_sku";


}
