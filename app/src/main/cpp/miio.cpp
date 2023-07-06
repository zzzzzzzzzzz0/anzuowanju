//
// Created by zzzzzzzzzzz on 2022/10/26.
//
#include <jni.h>
#include "s.h"
#include "miio/chazuo.h"

extern "C"
JNIEXPORT void JNICALL
Java_name_zzzzzzzzzzz_anzuowanju_Miio_1_chazuo_1_1(JNIEnv *env, jobject thiz, jstring ip1,
                                                   jstring key1, jint i) {
	chazuo___ hd;
	S ip(ip1, env);
	S key(key1, env);
	int ret = hd.Init(ip.c__(), key.c__());
	if(ret == 0)
		return;
	hd.c__(i);
}