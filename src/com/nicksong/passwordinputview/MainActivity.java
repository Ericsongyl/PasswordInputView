package com.nicksong.passwordinputview;

import com.nicksong.passwordinputview.PasswordInputView.OnFinishListener;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity implements OnFinishListener{
	
	private PasswordInputView passwordView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
	}
	
	private void initView() {
		passwordView = (PasswordInputView)findViewById(R.id.password_view);
		initListener();
	}
	
	private void initListener() {
		passwordView.setOnFinishListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void setOnPasswordFinished() {
		// TODO Auto-generated method stub
		if (passwordView.getOriginText().length() == passwordView.getMaxPasswordLength()) {
			Toast.makeText(this, "密码为" + passwordView.getOriginText(), Toast.LENGTH_LONG).show();
		}
	}

}
