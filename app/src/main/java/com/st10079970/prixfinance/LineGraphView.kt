package com.st10079970.prixfinance

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

class LineGraphView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val incomeColor = Color.parseColor("#759AAB")
    private val expenseColor = Color.parseColor("#D9E3E8")
    private val textColor = Color.parseColor("#262B2B")

    private val incomeData = listOf(1000, 3000, 5000, 7000, 9000)
    private val expenseData = listOf(500, 1500, 4000, 6000, 8500)

    private val paintLine = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 5f
        isAntiAlias = true
    }
    private val paintText = Paint().apply {
        color = textColor
        textSize = 40f
        isAntiAlias = true
    }
    private val paintPoint = Paint().apply {
        style = Paint.Style.FILL
        color = textColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas ?: return

        // Define margins and graph area
        val padding = 100f
        val width = width.toFloat() - padding * 2
        val height = height.toFloat() - padding * 2

        drawAxes(canvas, padding, width, height)

        drawLineGraph(canvas, incomeData, incomeColor, padding, width, height)
        drawLineGraph(canvas, expenseData, expenseColor, padding, width, height)

        drawKey(canvas, padding)

        drawIntersection(canvas, padding, width, height)
    }

    private fun drawAxes(canvas: Canvas, padding: Float, width: Float, height: Float) {
        paintLine.color = textColor
        canvas.drawLine(padding, height + padding, width + padding, height + padding, paintLine) // x axis
        canvas.drawLine(padding, padding, padding, height + padding, paintLine) // y axis
    }

    private fun drawLineGraph(canvas: Canvas, data: List<Int>, color: Int, padding: Float, width: Float, height: Float) {
        paintLine.color = color

        val maxY = 10000f
        val scaleX = width / (data.size - 1).toFloat()
        val scaleY = height / maxY

        for (i in 0 until data.size - 1) {
            val startX = padding + i * scaleX
            val startY = padding + height - data[i] * scaleY
            val stopX = padding + (i + 1) * scaleX
            val stopY = padding + height - data[i + 1] * scaleY
            canvas.drawLine(startX, startY, stopX, stopY, paintLine)
        }
    }

    private fun drawKey(canvas: Canvas, padding: Float) {
        paintText.color = textColor
        canvas.drawText("Key:", padding + 20, padding - 50, paintText)

        paintLine.color = incomeColor
        canvas.drawLine(padding + 150, padding - 60, padding + 250, padding - 60, paintLine)
        canvas.drawText("Income", padding + 260, padding - 55, paintText)

        paintLine.color = expenseColor
        canvas.drawLine(padding + 150, padding - 30, padding + 250, padding - 30, paintLine)
        canvas.drawText("Expenses", padding + 260, padding - 25, paintText)
    }

    private fun drawIntersection(canvas: Canvas, padding: Float, width: Float, height: Float) {
        for (i in 0 until incomeData.size - 1) {
            val incomeDiff = incomeData[i + 1] - incomeData[i]
            val expenseDiff = expenseData[i + 1] - expenseData[i]

            if ((incomeDiff < 0 && expenseDiff > 0) || (incomeDiff > 0 && expenseDiff < 0)) {
                // Roughly mark intersection point (simple linear approximation)
                val intersectionX = padding + i * (width / (incomeData.size - 1))
                val intersectionY = padding + height - incomeData[i] * (height / 10000f)

                canvas.drawCircle(intersectionX, intersectionY, 10f, paintPoint)
                canvas.drawText("Intersection", intersectionX + 20, intersectionY, paintText)
                break
            }
        }
    }
}
