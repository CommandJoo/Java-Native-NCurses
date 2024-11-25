/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class de_curses_NativeCurses */

#ifndef _Included_de_curses_NativeCurses
#define _Included_de_curses_NativeCurses
#ifdef __cplusplus
extern "C" {
#endif
#undef de_curses_NativeCurses_BLACK
#define de_curses_NativeCurses_BLACK 1L
#undef de_curses_NativeCurses_DARK_GRAY
#define de_curses_NativeCurses_DARK_GRAY 2L
#undef de_curses_NativeCurses_LIGHT_GRAY
#define de_curses_NativeCurses_LIGHT_GRAY 3L
#undef de_curses_NativeCurses_WHITE
#define de_curses_NativeCurses_WHITE 4L
#undef de_curses_NativeCurses_DARK_RED
#define de_curses_NativeCurses_DARK_RED 5L
#undef de_curses_NativeCurses_LIGHT_RED
#define de_curses_NativeCurses_LIGHT_RED 6L
#undef de_curses_NativeCurses_DARK_GREEN
#define de_curses_NativeCurses_DARK_GREEN 7L
#undef de_curses_NativeCurses_LIGHT_GREEN
#define de_curses_NativeCurses_LIGHT_GREEN 8L
#undef de_curses_NativeCurses_DARK_BLUE
#define de_curses_NativeCurses_DARK_BLUE 9L
#undef de_curses_NativeCurses_LIGHT_BLUE
#define de_curses_NativeCurses_LIGHT_BLUE 10L
#undef de_curses_NativeCurses_DARK_CYAN
#define de_curses_NativeCurses_DARK_CYAN 11L
#undef de_curses_NativeCurses_LIGHT_CYAN
#define de_curses_NativeCurses_LIGHT_CYAN 12L
#undef de_curses_NativeCurses_DARK_MAGENTA
#define de_curses_NativeCurses_DARK_MAGENTA 13L
#undef de_curses_NativeCurses_LIGHT_MAGENTA
#define de_curses_NativeCurses_LIGHT_MAGENTA 14L
#undef de_curses_NativeCurses_DARK_YELLOW
#define de_curses_NativeCurses_DARK_YELLOW 15L
#undef de_curses_NativeCurses_LIGHT_YELLOW
#define de_curses_NativeCurses_LIGHT_YELLOW 16L
#undef de_curses_NativeCurses_CORNER_UPPER_LEFT
#define de_curses_NativeCurses_CORNER_UPPER_LEFT 9484L
#undef de_curses_NativeCurses_CORNER_UPPER_RIGHT
#define de_curses_NativeCurses_CORNER_UPPER_RIGHT 9488L
#undef de_curses_NativeCurses_CORNER_LOWER_LEFT
#define de_curses_NativeCurses_CORNER_LOWER_LEFT 9492L
#undef de_curses_NativeCurses_CORNER_LOWER_RIGHT
#define de_curses_NativeCurses_CORNER_LOWER_RIGHT 9496L
#undef de_curses_NativeCurses_TEE_DOWN_POINTING
#define de_curses_NativeCurses_TEE_DOWN_POINTING 9516L
#undef de_curses_NativeCurses_TEE_UP_POINTING
#define de_curses_NativeCurses_TEE_UP_POINTING 9524L
#undef de_curses_NativeCurses_TEE_RIGHT_POINTING
#define de_curses_NativeCurses_TEE_RIGHT_POINTING 9500L
#undef de_curses_NativeCurses_TEE_LEFT_POINTING
#define de_curses_NativeCurses_TEE_LEFT_POINTING 9508L
#undef de_curses_NativeCurses_CROSS
#define de_curses_NativeCurses_CROSS 9532L
#undef de_curses_NativeCurses_LINE_HORIZONTAL
#define de_curses_NativeCurses_LINE_HORIZONTAL 9472L
#undef de_curses_NativeCurses_LINE_VERTICAL
#define de_curses_NativeCurses_LINE_VERTICAL 9474L
/*
 * Class:     de_curses_NativeCurses
 * Method:    init
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_init
  (JNIEnv *, jobject);

/*
 * Class:     de_curses_NativeCurses
 * Method:    destroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_destroy
  (JNIEnv *, jobject);

/*
 * Class:     de_curses_NativeCurses
 * Method:    cls
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_cls
  (JNIEnv *, jobject);

/*
 * Class:     de_curses_NativeCurses
 * Method:    setCursor
 * Signature: (Z)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_setCursor
  (JNIEnv *, jobject, jboolean);

/*
 * Class:     de_curses_NativeCurses
 * Method:    moveCursor
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_moveCursor
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    setColor
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_setColor
  (JNIEnv *, jobject, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    defineColor
 * Signature: (IFFF)I
 */
JNIEXPORT jint JNICALL Java_de_curses_NativeCurses_defineColor
  (JNIEnv *, jobject, jint, jfloat, jfloat, jfloat);

/*
 * Class:     de_curses_NativeCurses
 * Method:    defineColorPair
 * Signature: (III)I
 */
JNIEXPORT jint JNICALL Java_de_curses_NativeCurses_defineColorPair
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    print
 * Signature: (C)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_print
  (JNIEnv *, jobject, jchar);

/*
 * Class:     de_curses_NativeCurses
 * Method:    printstr
 * Signature: (Ljava/lang/String;)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_printstr
  (JNIEnv *, jobject, jstring);

/*
 * Class:     de_curses_NativeCurses
 * Method:    getch
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_curses_NativeCurses_getch
  (JNIEnv *, jobject);

/*
 * Class:     de_curses_NativeCurses
 * Method:    getHeight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_curses_NativeCurses_getHeight
  (JNIEnv *, jobject);

/*
 * Class:     de_curses_NativeCurses
 * Method:    getWidth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_de_curses_NativeCurses_getWidth
  (JNIEnv *, jobject);

/*
 * Class:     de_curses_NativeCurses
 * Method:    drawBox
 * Signature: (IIII)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_drawBox
  (JNIEnv *, jobject, jint, jint, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    drawHorizontalLine
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_drawHorizontalLine
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    drawVerticalLine
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_drawVerticalLine
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    drawCorner
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_drawCorner
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    drawTee
 * Signature: (III)V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_drawTee
  (JNIEnv *, jobject, jint, jint, jint);

/*
 * Class:     de_curses_NativeCurses
 * Method:    refresh
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_de_curses_NativeCurses_refresh
  (JNIEnv *, jobject);

#ifdef __cplusplus
}
#endif
#endif
