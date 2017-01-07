#include <jni.h>
#include <string>

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_Add_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a+b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_Subtract_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a-b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_Multiply_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a*b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_Divide_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a/b;
}