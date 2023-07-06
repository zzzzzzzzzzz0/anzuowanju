//
// Created by zzzzzzzzzzz on 2022/10/26.
//

#ifndef ANZUOWANJU_S_H
#define ANZUOWANJU_S_H

class S {
	const jstring *j_ = nullptr;
	const char *c_ = nullptr;
	JNIEnv *env_;
public:
	S(const jstring &j, JNIEnv *env) {
		if(j) {
			c_ = env->GetStringUTFChars(j, NULL);
			j_ = &j;
		}
		env_ = env;
	}
	~S() {
		if(c_)
			env_->ReleaseStringUTFChars(*j_, c_);
	}
	const char *c__() {return c_;}
};

#endif //ANZUOWANJU_S_H
