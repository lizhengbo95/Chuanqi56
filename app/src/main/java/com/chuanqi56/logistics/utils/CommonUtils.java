package com.chuanqi56.logistics.utils;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Color;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @Description: 普通常用的工具类
 * @author lizhengbo95
 * @date 2015-9-12 下午4:28:32
 * 
 */
public class CommonUtils {
//	private static Toast toast;

	/**
	 * 判断是否是手机号码
	 * 
	 * @param mobiles
	 *            手机号
	 * @return 符合正则表达的返回true，否则false
	 */
	public static boolean isMobile(String mobiles) {
//		Pattern pattern = Pattern.compile("^(1[3|4|5|7|8][0-9])\\d{8}$");
		Pattern pattern = Pattern.compile("^1\\d{10}$");
		Matcher matcher = pattern.matcher(mobiles);
		return matcher.matches();
	}

	/**
	 * 获得当前程序版本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static int getVersionCode(Context context) {
		PackageInfo packInfo = getPackageInfo(context);
		return (Integer) (packInfo == null ? "0" : packInfo.versionCode);
	}
	/**
	 * 获得当前程序版本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getVersionName(Context context) {
		PackageInfo packInfo = getPackageInfo(context);
		return packInfo == null ? "" : packInfo.versionName;
	}

	private static PackageInfo getPackageInfo(Context context) {
		// 获取packagemanager的实例
		PackageManager packageManager = context.getPackageManager();
		// getPackageName()是你当前类的包名，0代表是获取版本信息
		PackageInfo packInfo = null;
		try {
			packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			e.printStackTrace();
		}
		return packInfo;
	}

	/**
	 * 显示或隐藏输入法（开启或关闭软键盘）
	 * 
	 * @param context
	 * @param isShowSoft
	 * @param editText
	 *            获取焦点的编辑框
	 */
	public static void showSoftInput(Context context, boolean isShowSoft, EditText editText) {
		InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
		if (isShowSoft) {
			imm.showSoftInput(editText, 0);
		} else {
			imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
		}
	}

	/**
	 * 短暂显示Toast消息
	 * 
	 * @param context
	 * @param message
	 */
	public static void showShortToast(Context context, String message) {
		// Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
		// 此方式解决连续弹出导致延迟的现象
//		if (toast == null) {
//			toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
//		}
		Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
		toast.setText(message);
		toast.show();
	}

	/**
	 * 显示消息
	 * 
	 * @param context
	 * @param resId
	 */
	public static void showShortToast(Context context, int resId) {
		showShortToast(context, context.getString(resId));
	}
	/**
	 * 较长显示Toast消息
	 * 
	 * @param context
	 * @param message
	 */
	public static void showLongToast(Context context, String message) {
		if(TextUtils.isEmpty(message)){
			return;
		}
//		if (toast == null) {
//			toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
//		}
		Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
		toast.setText(message);
		toast.show();
	}
	
	public static void showLongToast(Context context, int resId) {
		showShortToast(context, context.getString(resId));
	}

	/**
	 * 随机字符串,可显示的随机字符串，无含义，目的是扰动签名结果
	 * 
	 * @return
	 */
	public static String genSecretStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
	}

	/**
	 * 设置listview高度
	 * 
	 * @param listView
	 */
	public static void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
//			listItem.measure(0, 0); // 计算子项View 的宽高
			listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
							View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}
//		DebugLog.e("totalHeight: "+totalHeight);
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//		DebugLog.e("totalHeight2:"+totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)));
		// listView.getDividerHeight()获取子项间分隔符占用的高度
		// params.height最后得到整个ListView完整显示需要的高度
		listView.setLayoutParams(params);
		listView.requestLayout();
	}

	//获取listview的高度
	public static int getListViewHeight(ListView listView) {
		if (listView == null)
			return 0;
		// 获取ListView对应的Adapter
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return 0;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) { // listAdapter.getCount()返回数据项的数目
			View listItem = listAdapter.getView(i, null, listView);
//			listItem.measure(0, 0); // 计算子项View 的宽高
			listItem.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
							View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
			totalHeight += listItem.getMeasuredHeight(); // 统计所有子项的总高度
		}
		return totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
	}




//	public static void doRestartApplication3(Context context) {
//		//停止使用重启功能
//		String message = "内存基础数据被系统杀掉，重启APP（3）";
//		TalkingDataUtils.onEvent(context, message);
//		DebugLog.e(message);
//		final Intent intent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        context.startActivity(intent);
//	}

//	/**
//	 * 获取粘贴板内容
//	 * @param context
//	 * @return
//	 */
//	public static String getPasteInfo(Context context) {
//		ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//		String text = "";
//		if (myClipboard != null) {
//			ClipData abc = myClipboard.getPrimaryClip();
//			if (abc != null) {
//				ClipData.Item item = abc.getItemAt(0);
//				if (item != null) {
//					CharSequence charSequence = item.getText();
//					if (charSequence != null) {
//						text = charSequence.toString();
//						return text;
//					}
//				}
//			}
//		}
//		return text;
//	}
//
//	/**
//	 * 清除粘贴板内容
//	 * @param context
//	 */
//	public static void cleanClipboard(Context context) {
//		ClipboardManager myClipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
//		ClipData myClip = ClipData.newPlainText("text", "");
//		if (myClipboard != null ) {
//			myClipboard.setPrimaryClip(myClip);
//		}
//	}
//
//	public static boolean isHaveCode(Context context) {
//		String pasteInfo = getPasteInfo(context);
//		return pasteInfo.length() == 5 && pasteInfo.matches("[a-zA-Z]+");
//	}
	
	/**
	 * 微信支付，将金额换算成分计算
	 * @param money
	 * @return
	 */
	public static int getYuanToFen(String money) {
		if (TextUtils.isEmpty(money)) {
			return 0;
		}
		BigDecimal bigDecimal = new BigDecimal(Double.parseDouble(money) * 100);
		return (int) bigDecimal.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 修正数字数据
	 * @return
     */
	public static double correntNumberData(String data){
		if (TextUtils.isEmpty(data)) {
			return 0;
		}
		BigDecimal bg = new BigDecimal(data);
		return bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * 使用BigDecimal，保留小数点后两位
	 */
	public static String formatMoney(double value) {
		BigDecimal bd = new BigDecimal(String.valueOf(value));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return bd.toString();
	}


	/**
//	 * 判断是否登录(如果未登录则去登录界面)
//	 * @param context
//	 * @return
//	 */
//	public static boolean getIsLogin(Context context) {
//		if (getIsLoginNotTo()) {
//			return true;
//		} else {
//			ActivityUtils.openActivity(context, V3_LoginActivity.class);
//			return false;
//		}
//	}
//	/**
//	 * 判断是否登录
//	 * @return true=登录；false=未登录
//	 */
//	public static boolean getIsLoginNotTo() {
//		String mSessionStr = Global.getSession();
//		return !TextUtils.isEmpty(mSessionStr);
//	}
	
//	/**
//	 * 调转至自带的应用市场或者浏览器腾讯应用宝
//	 */
//	public static void getAPPUpdate(Context context) {
//		//这里开始执行一个应用市场跳转逻辑，默认this为Context上下文对象
//		Intent intent = new Intent(Intent.ACTION_VIEW);
//		intent.setData(Uri.parse("market://details?id=" + context.getPackageName())); //跳转到应用市场，非Google Play市场一般情况也实现了这个接口
//		//存在手机里没安装应用市场的情况，跳转会包异常，做一个接收判断
//		if (intent.resolveActivity(context.getPackageManager()) != null) { //可以接收
//			context.startActivity(intent);
//		} else { //没有应用市场，我们通过浏览器跳转到Google Play
////		    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + getPackageName()));
//		    intent.setData(Uri.parse("http://a.app.qq.com/o/simple.jsp?pkgname=cn.urfresh.uboss&g_f=991653"));
//		    //这里存在一个极端情况就是有些用户浏览器也没有，再判断一次
//		    if (intent.resolveActivity(context.getPackageManager()) != null) { //有浏览器
//		    	context.startActivity(intent);
//		    } else { //天哪，这还是智能手机吗？
////		        Toast.makeText(this, "天啊，您没安装应用市场，连浏览器也没有，您买个手机干啥？", Toast.LENGTH_SHORT).show();
//		    	CommonUtils.showLongToast(context,"天啊，没安装应用市场，连浏览器也没有，你是外星人吗！");
////		    	UmengUpdateUtils.autoCheckUmengUpdate(context);//友盟检测更新
//
////				new BDUpdateUtil(context).uiUpdateActionBDpAPP();
//				Beta.checkUpgrade(false, false);//腾讯bugly更新
//		    }
//		}
//	}


	/**
	 * 获取屏幕密度
	 * @param context
	 * @return
	 */
	public static float getDisplayMetrics(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.density;
	}

	/**
	 * 获取屏幕宽度px
	 * @param context
	 * @return
	 */
	public static int getDisplayMetricsWidthPixels(Context context) {
		DisplayMetrics metrics = new DisplayMetrics();
		((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
		return metrics.widthPixels;
	}

//	public static String getTdChannelId(Context context) {
//		String channelId = "";
//		if (ConstantUrl.IS_PACKERNG) {
//			channelId = PackerNg.getMarket(context);
//		} else {
//			try {
//				channelId = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA).metaData.getString("TD_CHANNEL_ID");
//			} catch (PackageManager.NameNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		cn.urfresh.uboss.statistics.utils.Stat_Log.e("渠道标示：" + channelId);
//		if(TextUtils.isEmpty(channelId)){
//			CommonUtils.showLongToast(context,"AndroidManifest.xml中配置的渠道ID>TCAgent.init(this, \"您的 App ID\", \"渠道 ID\")方法配置的渠道ID。");
//		}
//		return channelId;
//	}
	/**
	 * 把String类型的颜色值转换成Rgb的颜色值返回
	 * @param StrColor
	 * @return
     */
	public static int toStrToColor(String StrColor) {
		int color = Color.BLACK;//默认返回黑色
		if (!TextUtils.isEmpty(StrColor)){
			try {
				if (StrColor.charAt(0) != '#') {
					StrColor = "#" + StrColor;
				}
				color = Color.parseColor(StrColor);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return color;
	}

//	public static int toStrKeyToColor(String strColorkey) {
//		int color = Color.BLACK;//默认返回黑色
//		if (Global.getSettingsData() != null && Global.getSettingsData().color_dict != null && Global.getSettingsData().color_dict.get(strColorkey) != null) {
//			color = toStrToColor(Global.getSettingsData().color_dict.get(strColorkey));
//		}
//		return color;
//	}
//	public static int toStrKeyToColor2(String strColorkey) {
//		int color = R.color.subject_color;//默认返回绿色
//		if (Global.getSettingsData() != null && Global.getSettingsData().color_dict != null && Global.getSettingsData().color_dict.get(strColorkey) != null) {
//			color = toStrToColor(Global.getSettingsData().color_dict.get(strColorkey));
//		}
//		return color;
//	}
//	public static String toStrKeyToColor3(String strColorkey) {
//		String color = "#ff0000";//默认返回红色
//		if (Global.getSettingsData() != null && Global.getSettingsData().color_dict != null && Global.getSettingsData().color_dict.get(strColorkey) != null) {
//			color = Global.getSettingsData().color_dict.get(strColorkey);
//			if (!TextUtils.isEmpty(color)){
//				if (color.charAt(0) != '#') {
//					color = "#" + color;
//				}
//			} else {
//				color = "#ff0000";//默认返回红色
//			}
//		}
//		return color;
//	}
//
//	public static String toStrKeyToImageUrl(String strImagekey) {
//		String imgeUrl = "";//默认返回黑色
//		if (Global.getSettingsData() != null && Global.getSettingsData().icons_dict != null && Global.getSettingsData().icons_dict.get(strImagekey) != null) {
//			imgeUrl = Global.getSettingsData().icons_dict.get(strImagekey);
//		}
//		return imgeUrl;
//	}
//
//	/**
//	 * （版本：V2.7.0；作者：lizhengbo95；时间：2017/1/20 11:22 ）说明：
//	 * @param backgroundImg
//	 * @param myView
//	 * @param failOrEmptyUri
//     */
//	public static void setBackgroundImg(String backgroundImg, final View myView, int failOrEmptyUri) {
//		if (myView == null) {
//			return;
//		}
//
//		Glide.with(MyApplication.getApplication()).asBitmap().load(backgroundImg).into(new SimpleTarget<Bitmap>() {
//			@Override
//			public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
//				myView.setBackgroundDrawable(new BitmapDrawable(resource));
//			}
//		});
//
//	}



	/**
	 * 清除价格后面的".0"和“.00”
	 * @return
	 */
	public static String wipeDueZero(String dueString){
		if(dueString==null){
			return "";
		}
		String dueStringTemp = "";
		if(dueString.endsWith(".00")||dueString.endsWith(".0")){
			int index = dueString.indexOf(".");
			dueStringTemp = dueString.substring(0,index);
		}else{
			dueStringTemp = dueString;
		}
		return dueStringTemp;
	}

//	/**
//	 * 获得高亮文字在指定文言中的位置信息
//	 * @param text
//	 * @param highLightTexts
//     * @return
//     */
//	public static List<HighLightIndexBean> getHighHightIndexBeanList(String text, String[] highLightTexts){
//		List<HighLightIndexBean> highLightIndexBeanList = new ArrayList<HighLightIndexBean>();
//		if(highLightTexts!=null){
//			for(int i =0;i<highLightTexts.length;i++){
//				int start = text.indexOf(highLightTexts[i]);
//				int end = start+highLightTexts[i].length();
//				if(start>=0){
//					HighLightIndexBean highLightIndexBean = new HighLightIndexBean();
//					highLightIndexBean.setStartIndex(start);
//					highLightIndexBean.setEndIndex(end);
//					highLightIndexBeanList.add(highLightIndexBean);
//				}
//				DebugLog.e("起始位置："+start+"  结束位置："+end);
//			}
//		}
//		return highLightIndexBeanList;
//	}
//

	private static String mAndroidId = "";
	public static String getAndroidId(Context context) {
		if (TextUtils.isEmpty(mAndroidId)) {
			mAndroidId = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);
		}
		return mAndroidId;
	}

}
