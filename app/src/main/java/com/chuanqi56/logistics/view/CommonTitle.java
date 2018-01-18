package com.chuanqi56.logistics.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chuanqi56.logistics.R;


/**
 * 自定义的一个界面的title布局
 * 
 * @author yangchengwei
 */
public class CommonTitle extends LinearLayout implements
		android.view.View.OnClickListener {
	private static final String TAG = "UrfreshTitleView";

	private Context mContext;/* 语言环境 */
	private LinearLayout mContantView;/* 整体的布局 */

	private RelativeLayout llAll;// 整体布局
	private ImageButton ibBack;/* 返回按钮 */
	private ImageView ivgoHome;/* 回首页 */
	private ImageView right_second_iv;/*右侧第二个imageButton*/
	private TextView tvTitle;/* 标题的内容 */
	private RelativeLayout rlRightbtn;/* 右上角的按键 */

	private ImageButton ibRightbtn;/* 右上角的imagebutton，由于显示图片 */
	private TextView tvRightbtn;/* 右上角的textView，由于显示文字 */
	private View bottomLine;//背景是白色时的底部分割线

	private UrfreshTitleOnClickListener backOnClickListener;/* 返回键点击的监听事件 */
	private UrfreshTitleOnClickListener btnRightOnClickListener;/* 右侧imagebutton点击的监听事件 */
	private UrfreshTitleOnClickListener btnLeft2OnClickListener;/* 左侧第二个位置点击的监听事件 */
	private UrfreshTitleOnClickListener btnRight2OnClickListener;/* 右侧第二个位置点击的监听事件 */

	public CommonTitle(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		setListener();
	}

	/**
	 * 初始化界面
	 */
	private void initView() {
		mContantView = (LinearLayout) View.inflate(mContext,
				R.layout.commont_title, null);
		addView(mContantView, new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		llAll = (RelativeLayout) mContantView.findViewById(R.id.llTop);
		ibBack = (ImageButton) mContantView.findViewById(R.id.ibLeft);
		ivgoHome = (ImageView) mContantView.findViewById(R.id.iv_gohome);
		tvTitle = (TextView) mContantView.findViewById(R.id.tvTitle);
		rlRightbtn = (RelativeLayout) mContantView.findViewById(R.id.rlRightbtn);
		rlRightbtn.setVisibility(View.GONE);
		ibRightbtn = (ImageButton) mContantView.findViewById(R.id.ibRight);
		tvRightbtn = (TextView) mContantView.findViewById(R.id.tvRight);
		bottomLine = mContantView.findViewById(R.id.bottom_line);
		right_second_iv = (ImageView) mContantView.findViewById(R.id.iv_right_second);
	}

	/**
	 * 初始化数据
	 */
	private void initData() {
	}

	/**
	 * 设置监听事件
	 */
	private void setListener() {
		ibBack.setOnClickListener(this);
		ivgoHome.setOnClickListener(this);
		rlRightbtn.setOnClickListener(this);
		right_second_iv.setOnClickListener(this);

	}

	/**
	 * 设置title的信息
	 * 
	 * @param title
	 */
	public void setTitleMessage(String title) {
		tvTitle.setText(title);
	}

	/**
	 * 设置btnRight的图片资源 并且默认会设置其可见
	 * 
	 * @param resource
	 */
	public void setBtnRightImage(int resource) {
		rlRightbtn.setVisibility(View.VISIBLE);
		rlRightbtn.setClickable(true);
		ibRightbtn.setVisibility(View.VISIBLE);
		tvRightbtn.setVisibility(View.GONE);
		ibRightbtn.setImageResource(resource);
	}

	/**
	 * 设置btnRight的图片资源 并且默认会设置其可见
	 * 
	 * @param text
	 */
	public void setBtnRightText(String text) {
		rlRightbtn.setVisibility(View.VISIBLE);
		rlRightbtn.setClickable(true);
		ibRightbtn.setVisibility(View.GONE);
		tvRightbtn.setVisibility(View.VISIBLE);
		tvRightbtn.setText(text);
	}

	/**
	 * 设置背景颜色
	 * 
	 * @param color
	 */
	public void setUrfreshTitleBackground(int color) {
		llAll.setBackgroundColor(color);
	}

	/**
	 * 设置背景颜色
	 * 
	 * @param color
	 */
	public void setTitleColor(int color) {
		tvTitle.setTextColor(color);
	}

	/***
	 * 设置back键的image资源
	 */
	public void setBackImageSrc(Drawable drawable) {
		ibBack.setImageDrawable(drawable);
	}

	/***
	 * 设置回首页键的image资源
	 */
	public void setGoHomeImageSrc(Drawable drawable) {
		ivgoHome.setImageDrawable(drawable);
	}

	/***
	 * 设置回首页键的image显示
	 */
	public void setGoHomeImageVisible() {
		ivgoHome.setVisibility(View.VISIBLE);
	}

	/***
	 * 设置回首页键的image显示
	 */
	public void setGoHomeImageVisible(int visible) {
		ivgoHome.setVisibility(visible);
	}

	public void setBtnLeft2OnClickListener(UrfreshTitleOnClickListener btnLeft2OnClickListener) {
		this.btnLeft2OnClickListener = btnLeft2OnClickListener;
	}

	/**
	 * 设置rightbtn是否可见
	 * 
	 * @param visibility
	 */
	public void setBtnRightVisibility(int visibility) {
		if (View.GONE == visibility) {
			Log.i(TAG, "不可设置是否可见为GONE");
			rlRightbtn.setVisibility(visibility);
			rlRightbtn.setClickable(false);
		} else if (View.VISIBLE == visibility) {
			rlRightbtn.setVisibility(visibility);
			rlRightbtn.setClickable(true);
		} else {
			rlRightbtn.setVisibility(visibility);
			rlRightbtn.setClickable(false);
		}
	}

	/**
	 * 设置标题是否显示底部分割线
	 * @param visibility
	 */
	public void setBottomLineVisibility(int visibility){
		bottomLine.setVisibility(visibility);
	}


	/**
	 * 设置back键的点击事件
	 * 
	 * @param backOnClickListener
	 */
	public void setBackOnClickListener(
			UrfreshTitleOnClickListener backOnClickListener) {
		this.backOnClickListener = backOnClickListener;
	}

	/**
	 * 设置右侧imagebutton键的点击事件
	 */
	public void setBtnRightOnClickListener(
			UrfreshTitleOnClickListener btnRightOnClickListener) {
		this.btnRightOnClickListener = btnRightOnClickListener;
	}

	/**
	 * 设置
	 * @param btnRight2OnClickListener
	 */
	public void setBtnRight2OnClickListener(UrfreshTitleOnClickListener btnRight2OnClickListener) {
		this.btnRight2OnClickListener = btnRight2OnClickListener;
	}

	/**
	 * 设置右侧第二个控件的image图
	 * @param drawable
	 */
	public void setBtnRight2ImageDrawable(Drawable drawable){
		right_second_iv.setVisibility(View.VISIBLE);
		right_second_iv.setImageDrawable(drawable);
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibLeft:
			if (backOnClickListener != null) {
				backOnClickListener.onClick(this);
			} else {
				Log.i(TAG, "activity finish");
				Activity activity = (Activity) mContext;
				activity.finish();
			}
			break;
		case R.id.iv_gohome:
//			TCAgent.onEvent(mContext, "点击拼团详情回首页");
//
			if(btnLeft2OnClickListener!=null){
				btnLeft2OnClickListener.onClick(this);
			}
			break;
		case R.id.rlRightbtn:
			if (btnRightOnClickListener != null) {
				btnRightOnClickListener.onClick(this);
			}
			break;
		case R.id.iv_right_second:
			if (btnRight2OnClickListener != null) {
				btnRight2OnClickListener.onClick(this);
			}
			break;

		default:
			break;
		}
	}

	/**
	 * 点击事件的接口
	 * 
	 * @author yangchengwei
	 * 
	 */
	public interface UrfreshTitleOnClickListener {
		void onClick(CommonTitle ufreshTitleView);
	}

}
