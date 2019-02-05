package br.senai.jandira.cadastro.ui

fun  minimoCaracter(string:String, minimo:Int) = string.length >= minimo

fun confirmEmail(string:String) = string.contains("@")

fun textContemNumero(texto:String) = texto.filter{it.isDigit()}.length > 0

fun sequenciaNumericaSucesso(texto:String) = "0123456789".contains(texto)
