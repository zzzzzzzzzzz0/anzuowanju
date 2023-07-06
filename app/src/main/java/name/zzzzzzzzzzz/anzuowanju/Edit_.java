package name.zzzzzzzzzzz.anzuowanju;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import org.zhscript.clas.*;

import name.zzzzzzzzzzz.anzuowanju.clas.All_;
import name.zzzzzzzzzzz.anzuowanju.clas.Menu_;

public class Edit_ extends AppCompatActivity {
    final static public String url_ = "url";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit);

		{
			Bundle b = getIntent().getExtras();
			Bundle b2 = b.getBundle("");
			et_ = (EditText) findViewById(R.id.editText);
			TextView tv = (TextView) findViewById(R.id.textView2);
			String[] a = b.getStringArray("a");
			switch (b.getString("typ")) {
				default: {
					set_zhuyi__(tv, b2, true);
					filename_ = a[0];
					try {
						et_.setText(text_ = FileOp_.read__(FileOp_.open__(filename_, true, this)));
					} catch (IOException e) {
						e.printStackTrace();
					}
					break;
				}
				case "文本":
					et_.setText(a[0]);
					set_zhuyi__(tv, b2, false);
					break;
				case UrlBig_.adblock_flag_:
					filename_ = "";
					et_.setText(text_ = a[0]);
					tv.setText(R.string.zhuyi_adblock);
					break;
                case url_:
                    filename_ = "";
                    et_.setText(text_ = a[0]);
                    break;
			}
		}
		{
			Button b = (Button) findViewById(R.id.button);
			b.setOnClickListener((l) -> {
				result__();
				finish();
			});
		}
	}

	void set_zhuyi__(TextView tv, Bundle b, boolean code) {
		String s = b.getString("zhuyi");
		if(s != null) {
			switch (s) {
				case "mod":
					tv.setText(R.string.zhuyi_mod);
					return;
				case "code":
					code = true;
					break;
				default:
					tv.setText(s);
					return;
			}
		}
		if(code)
			tv.setText(R.string.zhuyi_code);
	}

	String filename_, text_;
	EditText et_;

	void result__() {
		if(filename_ != null) {
			Intent i = getIntent();
			String text = et_.getText().toString();
			if(!text.equals(text_)) {
				i.putExtra("text", text);
				if(!filename_.isEmpty())
                    i.putExtra("filename", filename_);
				setResult(RESULT_OK, i);
			} else {
				setResult(RESULT_CANCELED, i);
			}
		}
	}

	@Override
	public void onBackPressed() {
		result__();
		super.onBackPressed();
	}
}
