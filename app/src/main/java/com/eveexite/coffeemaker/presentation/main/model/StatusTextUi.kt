package com.eveexite.coffeemaker.presentation.main.model

/**
 * Created by Ivan on 18/03/2018.
 *
 */
enum class StatusTextUi(val text: String) {

    COFFEE_MAKER_UNPLUGGED("No hay conexión con la cafetera."),
    COFFEE_MAKER_READY("Cafetera lista para encender"),
    NOT_ENOUGH_WATER("No hay suficiente agua."),
    COFFEE_READY("Café listo"),
    PREPARING_COFFEE("Preparando café. Sé paciente por favor :)"),
    COFFEE_MAKER_RESTING("Cafetera descansando. Espera 5 minutos por favor :)")

}