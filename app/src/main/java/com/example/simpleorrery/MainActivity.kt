package com.example.simpleorrery

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlin.jvm.java

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        if (appWidgetManager.isRequestPinAppWidgetSupported) {
            appWidgetManager.requestPinAppWidget(ComponentName(applicationContext,
                MyWidgetProvider::class.java), null, null)
        }
        finish()
    }
}