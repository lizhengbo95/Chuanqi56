package com.chuanqi56.logistics.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.chuanqi56.logistics.R;

public class RadiusButton extends Button {
	private boolean isCircle;// 是否是 整个圆的弧度
	private int width, height;
	private float corner_radius;
	private float density;

	private int solid_color;
	private int stroke_color;
	private float stroke_width;


	private int pressed_solid_color;
	private int pressed_stroke_color;
	private float pressed_stroke_width;

	private int selected_solid_color;
	private int selected_stroke_color;
	private float selected_stroke_width;



	private StateListDrawable bgdrawable;

	public RadiusButton(Context context) {
		super(context);
	}

	public RadiusButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		density = getResources().getDisplayMetrics().density;
		init(context, attrs);
	}


	public void setStroke_color(int stroke_color) {
		this.stroke_color = stroke_color;
		initBg();
	}

	public void setSolid_color(int solid_color) {
		this.solid_color = solid_color;
		initBg();
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray typedArray = context.getTheme().obtainStyledAttributes(
				attrs, R.styleable.RadiusButton, 0, 0);

		isCircle = typedArray.getBoolean(R.styleable.RadiusButton_is_circle, false);
		corner_radius = typedArray.getDimension(
				R.styleable.RadiusButton_corner_radius, 0);


		solid_color = typedArray.getColor(R.styleable.RadiusButton_solid_color,
				Color.TRANSPARENT);
		stroke_color = typedArray.getColor(R.styleable.RadiusButton_stroke_color,
				Color.BLACK);
		stroke_width = typedArray.getDimension(R.styleable.RadiusButton_stroke_width,0 * density);
		stroke_width=dpToPx(stroke_width);


		pressed_solid_color = typedArray.getColor(R.styleable.RadiusButton_pressed_solid_color, solid_color);
		pressed_stroke_color = typedArray.getColor(R.styleable.RadiusButton_pressed_stroke_color, stroke_color);
		pressed_stroke_width = typedArray.getDimension(R.styleable.RadiusButton_pressed_stroke_width,stroke_width);

		selected_solid_color = typedArray.getColor(R.styleable.RadiusButton_selected_solid_color, solid_color);
		selected_stroke_color = typedArray.getColor(R.styleable.RadiusButton_selected_stroke_color, stroke_color);
		selected_stroke_width = typedArray.getDimension(R.styleable.RadiusButton_selected_stroke_width,stroke_width);


		typedArray.recycle();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		width = w;
		height = h;

		initBg();

	}

	private void initBg() {

		if(isCircle){
			corner_radius=height/2.0f;
		}


		GradientDrawable normaldrawable = new GradientDrawable();// 创建drawable
		normaldrawable.setColor(  solid_color );

		//normaldrawable.setCornerRadius(corner_radius);
		normaldrawable.setCornerRadii(new float[] { corner_radius, corner_radius, corner_radius, corner_radius, corner_radius, corner_radius, corner_radius, corner_radius }); // 以此是左上角，右上角，右下角，左下角
		normaldrawable.setStroke((int) stroke_width,  stroke_color );


		GradientDrawable selecteddrawable = new GradientDrawable();// 创建drawable
		selecteddrawable.setColor(  selected_solid_color );
		//normaldrawable.setCornerRadius(corner_radius);
		selecteddrawable.setCornerRadii(new float[] { corner_radius, corner_radius, corner_radius,corner_radius, corner_radius,corner_radius, corner_radius,corner_radius }); // 以此是左上角，右上角，右下角，左下角
		selecteddrawable.setStroke((int) selected_stroke_width,  selected_stroke_color);


		GradientDrawable presseddrawable = new GradientDrawable();// 创建drawable
		presseddrawable.setColor(  pressed_solid_color );
		//normaldrawable.setCornerRadius(corner_radius);
		presseddrawable.setCornerRadii(new float[] { corner_radius, corner_radius, corner_radius, corner_radius, corner_radius, corner_radius, corner_radius, corner_radius }); // 以此是左上角，右上角，右下角，左下角
		presseddrawable.setStroke((int) pressed_stroke_width,  pressed_stroke_color );




		bgdrawable = new StateListDrawable();

		//		bgdrawable.addState(new int[] { -android.R.attr.state_selected }, normaldrawable);
		bgdrawable.addState(new int[] { android.R.attr.state_selected }, selecteddrawable);


		bgdrawable.addState(new int[] { -android.R.attr.state_pressed }, normaldrawable);
		bgdrawable.addState(new int[] { android.R.attr.state_pressed }, presseddrawable);


		setBackgroundDrawable(bgdrawable);
	}

	/**
	 * dip 转换成px
	 *
	 * @param
	 * @return
	 */
	private float dpToPx(float dp) {
		float px= dp * density;

		if(dp>0 && (px>0&&px<1)){
			px=1;
		}


		return px;
	}

}