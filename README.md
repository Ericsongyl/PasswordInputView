# PasswordInputView
Android自定义密码输入框

# What's passwordInputView?
PasswordInputView is a custom view for inputing password like that aliPay or wechatPay view.

# Principle
1.The custom view named 'PasswordInputView' inherit EditText,because this custom view is editabled and is used for inputing password.

2.for custom propery in layout file(xml),you should achive the constructor method with the param of 'AttributeSet'.

```
public PasswordInputView(Context context, AttributeSet attr) {
		super(context, attr);
		init(context, attr);
	}
```


3.The property of passwordInputView is defined in 'value/attrs.xml',as follow,

```
<?xml version="1.0" encoding="utf-8"?>
<resources>
	<declare-styleable name="Passwordinputview">
	    <attr name="passwordLength" format="integer"/>
		<attr name="borderWidth" format="dimension"/>
		<attr name="borderRadius" format="dimension"/>
		<attr name="borderColor" format="color"/>
		<attr name="passwordWidth" format="dimension"/>
		<attr name="passwordColor" format="color"/>
	</declare-styleable>
</resources>
```


4.Override the OnDraw(Canvas canvas),include draw rectangle,draw content area,draw split line,draw circle.

```
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
		borderPaint.setStrokeWidth(defaultSplitLineWidth);
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
```


5.Define the method of set property,eg,

```
public void setBorderWidth(int borderWidth) {
	this.borderWidth = borderWidth;
	borderPaint.setStrokeWidth(borderWidth);
	postInvalidate();
}
```


# The effect of map
![effect map](https://github.com/Ericsongyl/PasswordInputView/blob/master/GIF.gif)
