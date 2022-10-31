package com.mahmoudashraf.core.androidextensions

import android.graphics.Color
import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showErrorSnackBar(
  errorMessage: String,
  actionName: String? = null,
  color: Int = Color.RED,
  actionClickListener: () -> Unit = {},
  length: Int = Snackbar.LENGTH_INDEFINITE,
  onDismiss: () -> Unit = {}
): Snackbar {
  val snackBar = Snackbar.make(this, errorMessage, length)
  snackBar
    .setBackgroundTint(color)
    .setTextColor(Color.WHITE)
    .setActionTextColor(Color.WHITE)
    .setAction(actionName) {
      snackBar.dismiss()
      actionClickListener()
    }
    .addCallback(object : Snackbar.Callback() {
      override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
        super.onDismissed(transientBottomBar, event)
        onDismiss()
      }
    })

  snackBar.show()
  return snackBar
}
