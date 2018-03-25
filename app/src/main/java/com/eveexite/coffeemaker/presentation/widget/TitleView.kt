package com.eveexite.coffeemaker.presentation.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.util.TypedValue
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.eveexite.coffeemaker.R
import com.eveexite.coffeemaker.presentation.util.TitleUtils
import io.reactivex.Observable
import java.util.concurrent.TimeUnit

/**
 * Created by ivan on 6/13/17.
 */

class TitleView : SurfaceView, SurfaceHolder.Callback {

    private var textPaint: Paint? = null

    private var rectContainer: RectF? = null

    private var textSize: Int = 0
    private var position: Int = 0

    private var counterTextSize: Float = 0.toFloat()

    var text: String? = null
        set(text) {
            field = text
            this.position = 0
            this.counterTextSize = 0.0f
        }

    private var surfaceHolder: SurfaceHolder? = null

    constructor(context: Context) : super(context) {
        textSize = 16
    }

    @JvmOverloads constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int = 0) : super(context, attrs, defStyleAttr) {

        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.TitleView, 0, 0)
        val displayMetrics = context.resources.displayMetrics

        try {

            textSize = a.getDimensionPixelSize(R.styleable.TitleView_textSize,
                    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, textSize.toFloat(), displayMetrics).toInt())

            text = a.getString(R.styleable.TitleView_text)

        } finally {
            a.recycle()
        }

    }

    init {
        textPaint = Paint()
        textPaint!!.color = Color.parseColor("#362416")
        textPaint!!.style = Paint.Style.FILL
        textPaint!!.isAntiAlias = true
        textPaint!!.isFakeBoldText = true
        textPaint!!.textAlign = Paint.Align.CENTER
        textPaint!!.textSize = textSize.toFloat()

        rectContainer = RectF()

        setZOrderOnTop(true)
        surfaceHolder = getHolder()
        surfaceHolder!!.setFormat(PixelFormat.TRANSLUCENT)
        surfaceHolder!!.addCallback(this)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        Observable.interval(TimeUnit.SECONDS.toNanos(1) / 300, TimeUnit.NANOSECONDS)
                .subscribe { _ ->
                    val c = holder.lockCanvas()
                    c?.let {
                        draw(it)
                        holder.unlockCanvasAndPost(it)
                    }
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

        if (text != null) {
            if (text!!.length < 28) {
                drawOnelineText(canvas)
            } else {
                drawTwolinesText(canvas)
            }
        }

    }

    fun drawOnelineText(canvas: Canvas) {
        val offsetStart = (measuredWidth / 2 - (textSize + 4) * (if (text!!.length < 0) 0 else text!!.length - 1) / 4).toFloat()

        for (i in 0 until text!!.length) {

            if (i < position - 1) {
                canvas.drawText(text!![i].toString(),
                        offsetStart + (textSize + 4) * i / 2,
                        rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) / 2,
                        textPaint!!)
            } else if (i > position) {
                canvas.drawText(text!![i].toString(),
                        offsetStart + (textSize + 4) * i / 2,
                        rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) / 2,
                        textPaint!!)
            } else if (i == position) {
                //Se achica el anterior sin considerar el primer carácter
                if (i > 0) {
                    textPaint!!.textSize = textSize + 20 - counterTextSize
                    canvas.drawText(text!![i - 1].toString(),
                            offsetStart + (textSize + 4) * (i - 1) / 2,
                            rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) / 2,
                            textPaint!!)
                }

                //Se agranda
                counterTextSize += 1.5f
                textPaint!!.textSize = textSize + counterTextSize
                textPaint!!.color = Color.parseColor("#6A2E18")
                canvas.drawText(text!![i].toString(),
                        offsetStart + (textSize + 4) * i / 2,
                        rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) / 2,
                        textPaint!!)
                textPaint!!.color = Color.parseColor("#362416")
                textPaint!!.textSize = textSize.toFloat()
            }

        }

        if (counterTextSize > 20) {
            counterTextSize = 0.0f
            position = if (position == text!!.length - 1) 0 else position + 1
        }

    }

    fun drawTwolinesText(canvas: Canvas) {
        val firstLineLength = text!!.substring(0, 26).lastIndexOf(" ")
        val secondLineLength = text!!.length - firstLineLength

        val offsetStart1 = (measuredWidth / 2 - (textSize + 4) * (if (firstLineLength < 0) 0 else firstLineLength - 1) / 4).toFloat()
        val offsetStart2 = (measuredWidth / 2 - (textSize + 4) * (if (secondLineLength < 0) 0 else secondLineLength - 1) / 4).toFloat()

        var i = 0
        while (i < firstLineLength) {
            if (i < position - 1) {
                canvas.drawText(text!![i].toString(),
                        offsetStart1 + (textSize + 4) * i / 2,
                        rectContainer!!.height() / 1.75f + TitleUtils.textHeight(textPaint!!) * 2,
                        textPaint!!)
            } else if (i > position) {
                canvas.drawText(text!![i].toString(),
                        offsetStart1 + (textSize + 4) * i / 2,
                        rectContainer!!.height() / 1.75f + TitleUtils.textHeight(textPaint!!) * 2,
                        textPaint!!)
            } else if (i == position) {
                //Se achica el anterior sin considerar el primer carácter
                if (i > 0) {
                    textPaint!!.textSize = textSize + 20 - counterTextSize
                    canvas.drawText(text!![i - 1].toString(),
                            offsetStart1 + (textSize + 4) * (i - 1) / 2,
                            rectContainer!!.height() / 1.75f + TitleUtils.textHeight(textPaint!!) * 2,
                            textPaint!!)
                }

                //Se agranda
                counterTextSize += 1.5f
                textPaint!!.textSize = textSize + counterTextSize
                textPaint!!.color = Color.parseColor("#6A2E18")
                canvas.drawText(text!![i].toString(),
                        offsetStart1 + (textSize + 4) * i / 2,
                        rectContainer!!.height() / 1.75f + TitleUtils.textHeight(textPaint!!) * 2,
                        textPaint!!)
                textPaint!!.color = Color.parseColor("#362416")
                textPaint!!.textSize = textSize.toFloat()
            }

            i++

        }

        while (i < text!!.length) {
            if (i < position - 1) {
                canvas.drawText(text!![i].toString(),
                        offsetStart2 + (textSize + 4) * (i - firstLineLength) / 2,
                        rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) * 2,
                        textPaint!!)
            } else if (i > position) {
                canvas.drawText(text!![i].toString(),
                        offsetStart2 + (textSize + 4) * (i - firstLineLength) / 2,
                        rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) * 2,
                        textPaint!!)
            } else if (i == position) {
                //Se achica el anterior si está en la segunda línea y sin considerar el primer carácter
                if (i > 0 && i > firstLineLength) {
                    textPaint!!.textSize = textSize + 20 - counterTextSize
                    canvas.drawText(text!![i - 1].toString(),
                            offsetStart2 + (textSize + 4) * (i - firstLineLength - 1) / 2,
                            rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) * 2,
                            textPaint!!)
                    //Se achica el anterior si está en la última posición de la primera línea y sin considerar el primer carácter
                } else if (i > 0) {
                    textPaint!!.textSize = textSize + 20 - counterTextSize
                    canvas.drawText(text!![i - 1].toString(),
                            offsetStart1 + (textSize + 4) * (i - 1) / 2,
                            rectContainer!!.height() / 1.75f + TitleUtils.textHeight(textPaint!!) * 2,
                            textPaint!!)
                }

                //Se agranda
                counterTextSize += 1.5f
                textPaint!!.textSize = textSize + counterTextSize
                textPaint!!.color = Color.parseColor("#6A2E18")
                canvas.drawText(text!![i].toString(),
                        offsetStart2 + (textSize + 4) * (i - firstLineLength) / 2,
                        rectContainer!!.height() / 1.75f - TitleUtils.textHeight(textPaint!!) * 2,
                        textPaint!!)
                textPaint!!.color = Color.parseColor("#362416")
                textPaint!!.textSize = textSize.toFloat()
            }

            i++

        }

        if (counterTextSize > 20) {
            counterTextSize = 0.0f
            position = if (position == text!!.length - 1) 0 else position + 1
        }

    }

}
