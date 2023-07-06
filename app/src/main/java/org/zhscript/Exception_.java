package org.zhscript;

/**
 * Created by zzzzzzzzzzz on 17-7-14.
 */

public class Exception_ extends RuntimeException {
	public static final int
			IS_BREAK = -32,
			IS_CONTINUE = -33;
	Result_ r_;
	public Exception_(Result_ r) {r_ = r;}
	public String getLocalizedMessage() {return r_.val_err_ + r_.err_;}

	public static int for__(Exception_ e) {
		switch (e.r_.val_val_) {
			case Exception_.IS_BREAK:
				if(e.r_.val2_.isEmpty())
					return e.r_.val_val_;
				break;
			case Exception_.IS_CONTINUE:
				if(e.r_.val2_.isEmpty())
					return e.r_.val_val_;
				break;
		}
		throw e;
	}
}
