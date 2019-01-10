package com.eveexite.coffeemaker.presentation.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.eveexite.coffeemaker.presentation.util.TitleUtils

/**
 * Created by Ivan on 9/07/2017.
 * 
 */

class WaterLevelView : SurfaceView, SurfaceHolder.Callback {

    private var recipientPaint: Paint? = null
    private var waterPaint: Paint? = null
    private var textPaint: Paint? = null

    private var rectContainer: RectF? = null

    private var textSize: Int = 0
    var waterLevel: Int = 0
        set(waterLevel) {
            field = waterLevel

            val c = surfaceHolder!!.lockCanvas()
            c?.let {
                draw(it)
                surfaceHolder!!.unlockCanvasAndPost(it)
            }
        }

    private var offsetStart: Float = 0.toFloat()

    private var surfaceHolder: SurfaceHolder? = null

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    fun init() {

        offsetStart = 150f
        textSize = 30

        recipientPaint = Paint()
        recipientPaint!!.color = Color.WHITE
        recipientPaint!!.style = Paint.Style.STROKE
        recipientPaint!!.isAntiAlias = true
        recipientPaint!!.strokeWidth = 5f

        textPaint = Paint()
        textPaint!!.color = Color.WHITE
        textPaint!!.isFakeBoldText = true
        textPaint!!.textAlign = Paint.Align.CENTER
        textPaint!!.textSize = textSize.toFloat()

        waterPaint = Paint()
        waterPaint!!.color = Color.parseColor("#9900ffff")
        waterPaint!!.style = Paint.Style.FILL
        waterPaint!!.isAntiAlias = true

        rectContainer = RectF()

        setZOrderOnTop(true)
        surfaceHolder = getHolder()
        surfaceHolder!!.setFormat(PixelFormat.TRANSLUCENT)
        surfaceHolder!!.addCallback(this)

    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        val c = holder.lockCanvas()
        c?.let {
            draw(it)
            holder.unlockCanvasAndPost(it)
        }
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //Para limpiar el canvas anterior
        canvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)

        rectContainer!!.set(0f, 0f, measuredWidth.toFloat(), measuredHeight.toFloat())

        drawRecipient(canvas)
        drawWater(canvas)
    }

    private fun drawRecipient(canvas: Canvas) {

        //Horizontal arriba izq
        /*canvas.drawLine(offsetStart - 25, 0,
                offsetStart, 0, recipientPaint);*/

        //Superior

        //Fore

        canvas.drawPath(drawCurveLine(offsetStart,
                25f,
                rectContainer!!.width() - 5,
                25f,
                -45, Path()), recipientPaint!!)

        //Back

        canvas.drawPath(drawCurveLine(offsetStart,
                25f,
                rectContainer!!.width() - 5,
                25f,
                45,
                Path()),
                recipientPaint!!)

        //Vertical izq
        canvas.drawLine(offsetStart,
                25f,
                offsetStart,
                rectContainer!!.height() - 25,
                recipientPaint!!)

        //Vertical der
        canvas.drawLine(rectContainer!!.width() - 5,
                25f,
                rectContainer!!.width() - 5,
                rectContainer!!.height() - 25,
                recipientPaint!!)

        //Indicadores

        //Nivel 0 tazas = 0 mL

        canvas.drawText("0 tazas",
                offsetStart - 75,
                rectContainer!!.height() - 25,
                textPaint!!)

        //Fore

        canvas.drawPath(drawCurveLine(offsetStart,
                rectContainer!!.height() - 25,
                rectContainer!!.width() - 5,
                rectContainer!!.height() - 25,
                -45, Path()), recipientPaint!!)

        //Nivel 1 taza, no hay sensor

        canvas.drawText("1 taza",
                offsetStart - 75,
                25 + (rectContainer!!.height() - 50) / 5 * 4 - TitleUtils.textHeight(textPaint!!) / 2,
                textPaint!!)

        canvas.drawPath(drawHalfCurveLine(offsetStart,
                (rectContainer!!.height() - 50) / 5 * 4,
                rectContainer!!.width() - 5,
                (rectContainer!!.height() - 50) / 5 * 4,
                - 45,
                Path()),
                recipientPaint!!)

        //Nivel 2 tazas = 300 mL

        canvas.drawText("2-5 tazas",
                offsetStart - 75,
                25 + (rectContainer!!.height() - 50) / 5 * 3 - TitleUtils.textHeight(textPaint!!) / 2,
                textPaint!!)

        canvas.drawPath(drawHalfCurveLine(offsetStart,
                (rectContainer!!.height() - 50) / 5 * 3,
                rectContainer!!.width() - 5,
                (rectContainer!!.height() - 50) / 5 * 3,
                -45,
                Path()),
                recipientPaint!!)

    }

    private fun drawWater(canvas: Canvas) {
        var path = Path()
        when (this.waterLevel) {
            2 -> {
                //Base
                path = drawCurveLine(offsetStart + 1,
                        rectContainer!!.height() - 25,
                        rectContainer!!.width() - 6,
                        rectContainer!!.height() - 25,
                        -45, path)
                //Vertical der
                path.lineTo(rectContainer!!.width() - 6,
                        25 + (rectContainer!!.height() - 50) / 5 * 3)
                //Superior curva
                path = drawCurveLine(rectContainer!!.width() - 6,
                        25 + (rectContainer!!.height() - 50) / 5 * 3,
                        offsetStart + 1,
                        25 + (rectContainer!!.height() - 50) / 5 * 3,
                        -30, path)
                //Vertical izq
                path.lineTo(offsetStart + 1,
                        rectContainer!!.height() - 25)
                //Dibujar
                canvas.drawPath(path, waterPaint!!)
            }
            1 -> {
                //Base
                path = drawCurveLine(offsetStart + 1,
                        rectContainer!!.height() - 25,
                        rectContainer!!.width() - 6,
                        rectContainer!!.height() - 25,
                        - 45,
                        path)
                //Vertical der
                path.lineTo(rectContainer!!.width() - 6,
                        25 + (rectContainer!!.height() - 50) / 5 * 4)
                //Superior curva
                path = drawCurveLine(rectContainer!!.width() - 6,
                        25 + (rectContainer!!.height() - 50) / 5 * 4,
                        offsetStart + 1,
                        25 + (rectContainer!!.height() - 50) / 5 * 4,
                        - 30,
                        path)
                //Vertical izq
                path.lineTo(offsetStart + 1,
                        rectContainer!!.height() - 25)
                //Dibujar
                canvas.drawPath(path, waterPaint!!)
            }
            0 -> {
            }
        }
    }

    private fun drawCurveLine(x1: Float, y1: Float, x2: Float, y2: Float, curveRadius: Int, path: Path): Path {

        val midX = x1 + (x2 - x1) / 2
        val midY = y1 + (y2 - y1) / 2
        val xDiff = midX - x1
        val yDiff = midY - y1
        val angle = Math.atan2(yDiff.toDouble(), xDiff.toDouble()) * (180 / Math.PI) - 90
        val angleRadians = Math.toRadians(angle)
        val pointX = (midX + curveRadius * Math.cos(angleRadians)).toFloat()
        val pointY = (midY + curveRadius * Math.sin(angleRadians)).toFloat()

        path.moveTo(x1, y1)
        path.cubicTo(x1, y1, pointX, pointY, x2, y2)

        return path

    }

    private fun drawHalfCurveLine(x1: Float, y1: Float, x2: Float, y2: Float, curveRadius: Int, path: Path): Path {

        val midX = x1 + (x2 - x1) / 2
        val midY = y1 + (y2 - y1) / 2
        val xDiff = midX - x1
        val yDiff = midY - y1
        val angle = Math.atan2(yDiff.toDouble(), xDiff.toDouble()) * (180 / Math.PI) - 90
        val angleRadians = Math.toRadians(angle)
        //float pointX        = (float) (midX + curveRadius * Math.cos(angleRadians));
        val pointY = (midY + curveRadius * Math.sin(angleRadians)).toFloat()

        val oval = RectF()
        oval.set(x1, y1, x2, pointY)
        path.arcTo(oval, 180f, -90f)

        return path

    }

}
