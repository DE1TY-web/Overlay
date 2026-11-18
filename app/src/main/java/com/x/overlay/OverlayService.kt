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
            val p = Paint().apply { color = Color.RED; textSize = 40f; isAntiAlias = true }
            override fun onDraw(c: Canvas) {
                super.onDraw(c)
                c.drawText("OK", 10f, 50f, p)
                invalidate()
            }
        }
        val lp = WindowManager.LayoutParams(
            300, // Ширина окна (пикселей)
            150, // Высота окна
            WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
            WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE or
            WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
            android.graphics.PixelFormat.TRANSLUCENT)
        
        lp.gravity = android.view.Gravity.TOP or android.view.Gravity.START
        lp.x = 50
        lp.y = 100
        
        wm.addView(v, lp)
        return START_STICKY
    }
}
