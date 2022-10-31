package com.mahmoudashraf.nativelib

object NativeLib {
  init {
    System.loadLibrary("nativelib")
  }
  external fun baseUrl(): String
}
