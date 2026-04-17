package com.example.simpleorrery

import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.Context
import android.widget.RemoteViews
import kotlin.math.sin

class MyWidgetProvider : AppWidgetProvider() {
    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        val now = System.currentTimeMillis()
        val d = (now - J2000_MILLIS) / (1000.0 * 60 * 60 * 24)
        for (appWidgetId in appWidgetIds) {
            val views = RemoteViews(context.packageName, R.layout.my_widget)
            planetElements.forEach { data ->
                var m = (data.n + data.w * d) % 360
                if (m < 0) m += 360
                val mRad = Math.toRadians(m)
                val v = m + Math.toDegrees(2 * data.e * sin(mRad))
                val l = (v + data.p - EARTH_JAN_1ST_OFFSET) % 360
                views.setFloat(data.id, "setRotation", -l.toFloat())
            }
            appWidgetManager.updateAppWidget(appWidgetId, views)
        }
    }

}

private data class PlanetData(
    val id: Int, val n: Double, val w: Double, val e: Double, val p: Double
)

private val planetElements = listOf(
    PlanetData(R.id.mercury, 252.25, 4.0923, 0.2056, 77.45),
    PlanetData(R.id.venus, 181.98, 1.6021, 0.0067, 131.57),
    PlanetData(R.id.earth, 100.46, 0.9856, 0.0167, 102.94),
    PlanetData(R.id.mars, 355.45, 0.5240, 0.0934, 336.04),
    PlanetData(R.id.jupiter, 34.40, 0.0830, 0.0484, 14.75),
    PlanetData(R.id.saturn, 49.94, 0.0334, 0.0541, 92.43),
    PlanetData(R.id.uranus, 313.23, 0.0117, 0.0472, 170.96),
    PlanetData(R.id.neptune, 304.88, 0.0059, 0.0086, 44.97),
)

// J2000.0 Epoch: Jan 1, 2000, 12:00 UTC (in milliseconds)
private const val J2000_MILLIS = 946728000000L

// Earth's longitude on Jan 1st used to align Earth to the top (0°) of the widget
private const val EARTH_JAN_1ST_OFFSET = 100.46
