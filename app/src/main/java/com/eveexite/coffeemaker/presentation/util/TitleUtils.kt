package com.eveexite.coffeemaker.presentation.util

import android.graphics.Paint

/**
 * Created by ivan on 6/13/17.
 */

object TitleUtils {

    fun textHeight(paint: Paint): Float {
        return (paint.ascent() + paint.descent()) / 2
    }

}
