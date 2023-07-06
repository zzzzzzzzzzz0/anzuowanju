#include <jni.h>
#include <string>

extern "C"
jstring
Java_name_zzzzzzzzzzz_anzuowanju_Main_stringFromJNI(
        JNIEnv *env,
        jobject /* this */) {
    std::string hello = "Hello from C++";
    return env->NewStringUTF(hello.c_str());
}
