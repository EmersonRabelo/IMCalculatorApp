package br.com.fiap.helloworld

import kotlin.math.pow

fun calcularIMC(weight: Double, height: Double): Double {
    return weight / (height / 100).pow(2);
}

fun statusIMC(imc: Double): Pair<Int, String> {
    return if (imc < 18.5) {
        Pair(0, "Abaixo do peso")
    } else if (imc >= 18.5 && imc < 25.0) {
        Pair(1, "Peso ideal")
    } else if (imc >= 25.0 && imc < 30.0) {
        Pair(2, "Acima do peso")
    } else if (imc >= 30.0 && imc < 35.0) {
        Pair(3, "Obsidade Grau I")
    } else if (imc >= 35.0 && imc < 40.0) {
        Pair(4, "Obsidade Grau II")
    } else {
        Pair(5, "Obsidade Grau III")
    }
}