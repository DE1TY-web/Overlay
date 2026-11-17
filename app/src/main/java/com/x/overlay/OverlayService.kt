package com.x.overlay

import android.app.Service
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.IBinder
import android.view.View
import android.view.WindowManager

class OverlayService : Service() {
    override fun onBind(i: Intent?): IBinder? = null

    override fun onStartCommand(i: Intent?, f: Int, id: Int): Int {
        val wm = getSystemService(WINDOW_SERVICE) as WindowManager
        val v = object : View(this) {
            val p = Paint().apply { color = Color.RED; textSize = 60f }
            override fun onDraw(c: Canvas) {
                super.onDraw(c)
                c.drawText("OVERLAY OK", 100f, 300f, p)
                invalidate()
            }
        }
        val lp = WindowManager.LayoutParams(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            android.graphics.PixelFormat.TRANSLUCENT)
        wm.addView(v, lp)
        return START_STICKY
    }
}
