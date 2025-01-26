package com.taylanozgurozdemir.vizesinavim

import java.io.Serializable

data class Yemek(
    val isim: String,
    val tarif: String,
    val malzemeler: List<String>
) : Serializable
