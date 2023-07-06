package com.hello.hellolight;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import name.zzzzzzzzzzz.anzuowanju.R;

public class hellolight___ extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//必须之前
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.hellolight_main);
		Window win = getWindow();
		win.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
					 WindowManager.LayoutParams.FLAG_FULLSCREEN);
		win.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		sb__(false);

		if(false)
		findViewById(R.id.l).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				sb__(true);
			}
		});
	}

	private void sb__(boolean set) {
		WindowManager.LayoutParams lp;
		lp = getWindow().getAttributes();
		lp.screenBrightness = lp.screenBrightness < 1f ? 1f : 0f;
		if(set)
			getWindow().setAttributes(lp);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode) {
		case KeyEvent.KEYCODE_MENU:
			sb__(true);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}