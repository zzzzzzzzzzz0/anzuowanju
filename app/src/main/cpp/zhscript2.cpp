#include <jni.h>
#include <android/log.h>
#include <android/asset_manager.h>
#include <android/asset_manager_jni.h>
#include <sstream>
#include <fstream>
#include "zhscript2/lib.h"
#include "zhscript2/shell2.h"
#include "zhscript2/util.h"

extern std::string null_;

void print__(const std::string &s) {
	__android_log_print(ANDROID_LOG_INFO, "zhscript2", "%s", s.c_str());
}

static AAssetManager* aam_ = NULL;
static std::string path_;

std::string file_get__(const std::string &filename) {
	//print__(filename);
	{
		std::ifstream ifs(path_ + "/" + filename);
		if(ifs) {
			std::stringstream ss;
			ss << ifs.rdbuf();
			ifs.close();
			//print__("= " + ss.str());
			return ss.str();
		}
	}
	{
		AAsset *aa = nullptr;
		try {
			aa = AAssetManager_open(aam_, filename.c_str(), AASSET_MODE_UNKNOWN);
		} catch (...) {}
		if (aa != nullptr) {
			off_t siz = AAsset_getLength(aa);
			char *buf = new char[siz + 1];
			buf[siz] = 0;
			AAsset_read(aa, buf, siz);
			AAsset_close(aa);
			std::string ret = buf;
			delete[] buf;
			//print__("- " + ret);
			return ret;
		}
	}
	return "";
}
std::string file_abs__(const std::string &filename) {
	return filename;
}

bool exists__(const std::string &filename) {
	{
		std::ifstream ifs(path_ + "/" + filename);
		if(ifs) {
			ifs.close();
			return true;
		}
	}
	{
		AAsset* aa = nullptr;
		try {
			aa = AAssetManager_open(aam_, filename.c_str(), AASSET_MODE_UNKNOWN);
		} catch (...) {}
		if(aa != nullptr) {
			AAsset_close(aa);
			return true;
		}
	}
	return false;
}
bool is_symlink__(const std::string &filename) {return false;}
std::string read_symlink__(const std::string &filename) {return "";}

#include "s.h"

static Lib* lib_ = new Lib();

static std::string suidao_;
static void suidao__(Ret *ret, Qv *qv, JNIEnv *env, jobject thiz2, //jobject resource,
			  int argc, ...) {
	jclass jc_s = env->FindClass("java/lang/String");
	jobjectArray a = env->NewObjectArray(argc, jc_s, NULL);
	env->DeleteLocalRef(jc_s);
	{
		va_list argv;
		va_start(argv, argc);
		for (int i = 0; i < argc; ++i) {
			char *s = va_arg(argv, char*);
			if(!s || s == null_)
				env->SetObjectArrayElement(a, i, NULL);
			else {
				jstring js = env->NewStringUTF(s);
				env->SetObjectArrayElement(a, i, js);
				env->DeleteLocalRef(js);
			}
		}
		va_end(argv);
	}
	jclass jc = env->FindClass("org/zhscript/Shell_");
	jobjectArray a2 = (jobjectArray)env->CallObjectMethod(thiz2,
			env->GetMethodID(jc, "suidao__", "([Ljava/lang/String;J" //"Ljava/lang/Object;"
					")[Ljava/lang/String;"),
			a, qv//, resource
	);
	env->DeleteLocalRef(jc);
	env->DeleteLocalRef(a);
	if(a2) {
		for(jsize i = 0; i < env->GetArrayLength(a2); i++) {
			if(i > 0)
				ret->one__();
			S s((jstring)env->GetObjectArrayElement(a2, i), env);
			ret->push__(s.c__() ? s.c__() : null_);
		}
		env->DeleteLocalRef(a2);
	}
}

static void fill_result__ (JNIEnv *env, jobject result, const char*err,
		int val_val, const char*val_err, const char*val2, bool e2,
		bool is_exit, std::vector<std::string> *a) {
	jclass jc = env->FindClass("org/zhscript/Result_");

	if(err) {
		jstring js = env->NewStringUTF(err);
		env->SetObjectField(result, env->GetFieldID(jc, "err_", "Ljava/lang/String;"), js);
		env->DeleteLocalRef(js);
	}
	if(val_val)
		env->SetIntField(result, env->GetFieldID(jc, "val_val_", "I"), val_val);
	if(val_err) {
		jstring js = env->NewStringUTF(val_err);
		env->SetObjectField(result, env->GetFieldID(jc, "val_err_", "Ljava/lang/String;"), js);
		env->DeleteLocalRef(js);
	}
	if(val2) {
		jstring js = env->NewStringUTF(val2);
		env->SetObjectField(result, env->GetFieldID(jc, "val2_", "Ljava/lang/String;"), js);
		env->DeleteLocalRef(js);
	}
	if(e2) {
		jclass jc_e2 = env->FindClass("org/zhscript/Exception2_");
		env->ThrowNew(jc_e2, nullptr);
		env->DeleteLocalRef(jc_e2);
	}
	if(is_exit)
		env->SetBooleanField(result, env->GetFieldID(jc, "is_exit_", "Z"), is_exit);
	if(a) {
		jclass jc_s = env->FindClass("java/lang/String");
		jobjectArray oa = env->NewObjectArray(a->size(), jc_s, NULL);
		env->DeleteLocalRef(jc_s);
		for (size_t i = 0; i < a->size(); i++) {
			jstring js = env->NewStringUTF((*a)[i].c_str());
			env->SetObjectArrayElement(oa, i, js);
			env->DeleteLocalRef(js);
		}
		env->SetObjectField(result, env->GetFieldID(jc, "a_", "[Ljava/lang/String;"), oa);
		env->DeleteLocalRef(oa);
	}

	env->DeleteLocalRef(jc);
}

static void xianguan__(segm::All *a, std::string &s, bool &bad) {
	for(auto i2 : a->a__()) {
		const keyword::Item& kw = i2->kw__();
		if(kw == keyword::Id::Dunhao)
			break;
		switch (kw) {
			case keyword::Id::TextBegin:
			case keyword::Id::No:
				i2->s__(s);
				break;
			default:
				bad = true;
				break;
		}
	}
	if(bad)
		print__("xianguan bad " + s);
}
static bool xianguan__(const std::string &code, segm::All *&a2) {
	return !false__(lib_->ls__()->parse__(code, false, &a2));
}
static void xianguan__(segm::All *a, std::vector<std::string> &v) {
	for(auto i : a->a__()) {
		switch (i->kw__()) {
			case keyword::Id::Load: {
				std::string name;
				bool bad = false;
				xianguan__(i->a__(), name, bad);
				if (!bad) {
					v.push_back(name);
					segm::All *a2;
					if(xianguan__(file_get__(name), a2))
						xianguan__(a2, v);
				}
				break;
			}
			case keyword::Id::Eval2: {
				std::string code;
				bool bad = false;
				xianguan__(i->a__(), code, bad);
				if (!bad) {
					segm::All *a2;
					if(xianguan__(code, a2))
						xianguan__(a2, v);
				}
				break;
			}
		}
	}
}

extern "C" {

void Java_org_zhscript_Shell_1_xianguan(JNIEnv *env, jobject, jstring code1, jobject result) {
	if(!code1)
		return;
	S code(code1, env);
	segm::All *a;
	if(!xianguan__(code.c__(), a))
		return;
	std::vector<std::string> v;
	xianguan__(a, v);
	fill_result__ (env, result, nullptr, 0, nullptr, nullptr, false, false, &v);
}

void
Java_org_zhscript_Shell_1_init(
	JNIEnv *env,
	jobject /* this */,
	jobject aam,
	jstring path1,
	jstring suidao1
) {
	aam_ = AAssetManager_fromJava(env, aam);
	{
		S s(path1, env);
		path_ = s.c__();
	}
	{
		S s(suidao1, env);
		suidao_ = s.c__();
	}
	segm::List *ls = lib_->ls__();
	ls->reset__();
}

void Java_org_zhscript_Shell_1_z(JNIEnv *env, jobject thiz,
                                 jstring src1, jboolean src_is_file, jstring src2, jobjectArray a2, jobjectArray a,
                                 jlong qv_up, //jobject resource,
								 jobject result) {
	if(!src1)
		return;
	Ret ret;
	segm::List *ls = lib_->ls__();
	Qv qv(qv_up ? (Qv*)qv_up : &ls->qv_top_);
	qv.mods_ = &ls->mods_;
	mk_suidao__(&qv, suidao_.c_str(), (unsigned long)suidao__, false,
			2
			//3
			, (unsigned long)env, (unsigned long)thiz//, (unsigned long)resource
	);
	arg::List args;
	{
		S s(src1, env);
		args.src_ = s.c__();
	}
	{
		S s(src2, env);
		if(s.c__())
			args.src2_ = s.c__();
	}
	args.src_is_file_ = src_is_file;
	if (a) {
		for (jsize i = 0; i < env->GetArrayLength(a);) {
			S s((jstring) env->GetObjectArrayElement(a, i++), env);
			args.push__(s.c__() ? s.c__() : null_);
		}
	}
	if (a2) {
		for (jsize i = 0; i < env->GetArrayLength(a2);) {
			S s((jstring) env->GetObjectArrayElement(a2, i++), env);
			if(i >= env->GetArrayLength(a2)) {
				qv.var_get2__(s.c__());
				break;
			}
			S s2((jstring) env->GetObjectArrayElement(a2, i++), env);
			qv.var_get2__(s.c__())->val_ = s2.c__();
		}
	}
	bool has_err = false;
	{
		Result2 r2 = ls->z2__(segm::KwBy {keyword::NO}, &args, &qv, nullptr, nullptr, ret);
		if (!ls->ok__(r2) && !ls->is_exit_) {
			has_err = true;
			if (result) {
				fill_result__ (env, result, r2->err_.c_str(), r2->val_.val_, r2->val_.err_.c_str(), r2->val2_.c_str(), true, false, nullptr);
			}
		}
	}
	ret.one__();
	if(!has_err) {
		if(ls->is_exit_) {
			if(result) {
				fill_result__ (env, result, nullptr, 0, nullptr, nullptr, false, ls->is_exit_, nullptr);
			}
		} else {
			if(result) {
				std::vector<std::string> v;
				for (size_t i = 0; i < ret.a_.size(); i++)
					v.push_back(ret.a_[i].val_);
				fill_result__ (env, result, nullptr, 0, nullptr, nullptr, false, false, &v);
			}
		}
	}
}

int Java_org_zhscript_Shell_1_len(JNIEnv *env, jobject, jstring code1) {
	if(!code1)
		return -10;
	S code(code1, env);
	segm::List *ls = lib_->ls__();
	segm::All *a;
	if(false__(ls->parse__(code.c__(), false, &a)))
		return -1;
	return a->a__().size();
}

long Java_org_zhscript_Shell_1_up(JNIEnv *env, jobject, jlong qv_up) {
	Qv* qv = (Qv*)qv_up;
	if(qv)
		return (long)qv->up_;
	return 0;
}

}
