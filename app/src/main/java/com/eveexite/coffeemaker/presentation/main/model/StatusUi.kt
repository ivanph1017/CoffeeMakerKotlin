package com.eveexite.coffeemaker.presentation.main.model

/**
 * Created by Ivan on 18/03/2018.
 *
 */
enum class StatusUi(val code: Int, val text: String) {

    COFFEE_MAKER_UNPLUGGED(0, "No hay conexión con la cafetera."),
    COFFEE_MAKER_READY(1, "Cafetera lista para encender"),
    NOT_ENOUGH_WATER(2, "No hay suficiente agua."),
    COFFEE_READY(3, "Café listo"),
    PREPARING_COFFEE(4, "Preparando café. Sé paciente por favor :)"),
    COFFEE_MAKER_RESTING_1(5, "Cafetera descansando. Espera menos de 1 minuto por favor :)"),
    COFFEE_MAKER_RESTING_2(5, "Cafetera descansando. Espera 2 minutos por favor :)"),
    COFFEE_MAKER_RESTING_3(5, "Cafetera descansando. Espera 3 minutos por favor :)"),
    COFFEE_MAKER_RESTING_4(5, "Cafetera descansando. Espera 4 minutos por favor :)"),
    COFFEE_MAKER_RESTING_5(5, "Cafetera descansando. Espera 5 minutos por favor :)")

}