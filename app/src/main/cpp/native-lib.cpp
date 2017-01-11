#include <jni.h>

//demo.paykey.paykeyassignment.evaluator.impl

extern "C" {

void throwDivisionByZeroException(JNIEnv *env) {
    jclass c = env->FindClass("java/lang/ArithmeticException");
    env->ThrowNew(c, "Division by zero");
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Add_result(JNIEnv *env, jobject instance, jint a,
                                                            jint b) {
    return a + b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Subtract_result(JNIEnv *env, jobject instance,
                                                                 jint a,
                                                                 jint b) {
    return a - b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Multiply_result(JNIEnv *env, jobject instance,
                                                                 jint a,
                                                                 jint b) {
    return a * b;
}

JNIEXPORT jint JNICALL
Java_demo_paykey_paykeyassignment_evaluator_impl_Divide_result(JNIEnv *env, jobject instance,
                                                               jint a,
                                                               jint b) {
    if (b == 0) {
        throwDivisionByZeroException(env);
        return 0; //must return something
    }
    return a / b;
}

}