#include <jni.h>
#include <string>

extern "C" JNIEXPORT jstring JNICALL
Java_com_mahmoudashraf_nativelib_NativeLib_baseUrl(
        JNIEnv* env,
        jobject) {
    std::string baseUrl = "https://rickandmortyapi.com/api/";
    return env->NewStringUTF(baseUrl.c_str());

}