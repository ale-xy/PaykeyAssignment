#include <jni.h>
#include <string>

//demo.paykey.paykeyassignment.evaluator.impl

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Add_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a+b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Subtract_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a-b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Multiply_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a*b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Divide_result(JNIEnv *env, jobject instance, jint a,
                                                        jint b) {
    return a/b;
}