package com.nicksong.passwordinputview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;

@SuppressLint("DrawAllocation")
public class PasswordInputView extends EditText{
	
	private int passwordLength;
	private float borderWidth;
	private float borderRadius; //边框四个角的角度
	private int borderColor;
	private float passwordWidth;
	private int passwordColor;
	
	private Paint borderPaint;
	private Paint passwordPaint;
	
	private int textLength;
	private int defaultContentMargin = 2;
	private String originText;
	private OnFinishListener onFinishListener;

	public PasswordInputView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public PasswordInputView(Context context, AttributeSet attr) {
		super(context, attr);
		init(context, attr);
	}
	
	private void init(Context context, AttributeSet attr) {
		TypedArray ta = context.getTheme().obtainStyledAttributes(attr, R.styleable.Passwordinputview, 0, 0);
		try {
			passwordLength = ta.getInt(R.styleable.Passwordinputview_passwordLength, 6);
			borderWidth = ta.getFloat(R.styleable.Passwordinputview_borderWidth, getResources().getDimension(R.dimen.dimen_1));
			borderRadius = ta.getFloat(R.styleable.Passwordinputview_borderRadius, getResources().getDimension(R.dimen.dimen_3));
			borderColor = ta.getColor(R.styleable.Passwordinputview_borderColor, getResources().getColor(R.color.color_black2));
			passwordWidth = ta.getDimension(R.styleable.Passwordinputview_passwordWidth, getResources().getDimension(R.dimen.dimen_6));
			passwordColor = ta.getColor(R.styleable.Passwordinputview_passwordColor, getResources().getColor(R.color.color_black));
		} catch (Exception e) {
			
		}
		ta.recycle();
		
		borderPaint = new Paint();
		borderPaint.setAntiAlias(true);
		borderPaint.setColor(borderColor);
		borderPaint.setStrokeWidth(borderWidth);
		borderPaint.setStyle(Paint.Style.FILL); //以填充模式来画，防止原始输入内容显示出来
		
		passwordPaint = new Paint();
		passwordPaint.setAntiAlias(true);
		passwordPaint.setColor(passwordColor);
		passwordPaint.setStrokeWidth(passwordWidth);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		int width = getWidth();
		int height = getHeight();
		
		//画边框
		RectF rect = new RectF(0, 0, width, height);
		borderPaint.setColor(borderColor);
		canvas.drawRoundRect(rect, borderRadius, borderRadius, borderPaint);
		
		//画内容区域,与上面的borderPaint.setStyle(Paint.Style.FILL)对应, 防止原始输入内容显示出来
		RectF rectContent = new RectF(rect.left + defaultContentMargin, rect.top + defaultContentMargin, rect.right - defaultContentMargin, rect.bottom - defaultContentMargin);
		borderPaint.setColor(Color.WHITE);
		canvas.drawRoundRect(rectContent, borderRadius, borderRadius, borderPaint);
		
		//画分割线:分割线数量比密码数少1
		borderPaint.setColor(borderColor);
		for (int i = 1; i < passwordLength; i++) {
			float x = width * i / passwordLength;
			canvas.drawLine(x, 0, x, height, borderPaint);
		}
		
		//画密码内容
		float px, py = height / 2;
		float halfWidth = width / passwordLength / 2;
		for (int i = 0; i < textLength; i++) {
			px = width * i / passwordLength + halfWidth;
			canvas.drawCircle(px, py, passwordWidth, passwordPaint);
		}
	}
	
	@Override
	protected void onTextChanged(CharSequence text, int start,
			int lengthBefore, int lengthAfter) {
		// TODO Auto-generated method stub
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		textLength = text.length();
		invalidate();
		onInputFinished(text);
	}
	
	public void onInputFinished(CharSequence text) {
		if (text != null && text.length() == 6) {
			originText = text.toString();
			onFinishListener.setOnPasswordFinished();
		}
	}
	
	public String getOriginText() {
		return originText;
	}
	
	public void setOnFinishListener(OnFinishListener onFinishListener) {
		this.onFinishListener = onFinishListener;
	}
	
	public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
        invalidate();
    }
	
	public void setBorderWidth(float borderWidth) {
		this.borderWidth = borderWidth;
		borderPaint.setStrokeWidth(borderWidth);
		invalidate();
	}
	
	public float getBorderWidth() {
		return borderWidth;
	}
	
	public void setBorderRadius(float borderRadius) {
		this.borderRadius = borderRadius;
		invalidate();
	}
	
	public float getBorderRadius() {
		return borderRadius;
	}
	
	public void setBorderColor(int borderColor) {
		this.borderColor = borderColor;
		borderPaint.setColor(borderColor);
//		invalidate();
	}
	
	public int getBorderColor() {
		return borderColor;
	}
	
	public void setPasswordWidth(float passwordWidth) {
		this.passwordWidth = passwordWidth;
		passwordPaint.setStrokeWidth(passwordWidth);
//		invalidate();
	}
	
	public float getPasswordWidth() {
		return passwordWidth;
	}
	
	public void setPasswordColor(int passwordColor) {
		this.passwordColor = passwordColor;
		passwordPaint.setColor(passwordColor);
//		invalidate();
	}
	
	public int getPasswordColor() {
		return passwordColor;
	}
	
	public interface OnFinishListener {
		public void setOnPasswordFinished();
	}

}
