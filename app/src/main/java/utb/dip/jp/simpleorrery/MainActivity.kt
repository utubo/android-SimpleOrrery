package utb.dip.jp.simpleorrery

import android.appwidget.AppWidgetManager
import android.content.ComponentName
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appWidgetManager = AppWidgetManager.getInstance(applicationContext)
        if (appWidgetManager.isRequestPinAppWidgetSupported) {
            appWidgetManager.requestPinAppWidget(
                ComponentName(
                    applicationContext,
                    MyWidgetProvider::class.java
                ), null, null
            )
        }
        finish()
    }
}