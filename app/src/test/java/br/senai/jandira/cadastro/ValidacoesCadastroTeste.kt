package br.senai.jandira.cadastro

import br.senai.jandira.cadastro.ui.confirmEmail
import br.senai.jandira.cadastro.ui.minimoCaracter
import br.senai.jandira.cadastro.ui.sequenciaNumericaSucesso
import br.senai.jandira.cadastro.ui.textContemNumero
import org.junit.Assert
import org.junit.Test

class ValidacoesCadastroTeste {

    @Test
    fun validarCaracteresSucesso(){

        val input = "Ariele"
        val resultadoEsperado = true

        val resultado = minimoCaracter(input, 3)

        /* verificando se deu certou ou não */
        Assert.assertEquals(resultadoEsperado, resultado)

    }

    @Test
    fun validarCaracteresSucessoDuasLetras(){

        val input = "ze"
        val resultadoEsperado = false

        val resultado = minimoCaracter(input, 3)

        /* verificando se deu certou ou não */
        Assert.assertEquals(resultadoEsperado, resultado)

    }

    @Test
    fun validarEmailSucesso(){
        val email = "teste@teste"
        val resultadoEsperado = true

        val resultado = confirmEmail(email)

        Assert.assertEquals(resultadoEsperado, resultado)
    }

    @Test
    fun validarEmailFalha(){
        val email = "testeteste"
        val resultadoEsperado = false

        val resultado = confirmEmail(email)

        Assert.assertEquals(resultadoEsperado, resultado)
    }

    @Test
    fun senhaContemNumero(){
        val senha = "tes1teteste"
        val resultadoEsperado = true

        val resultado = textContemNumero(senha)

        Assert.assertEquals(resultadoEsperado, resultado)
    }

    @Test
    fun senhaSemNumero(){
        val senha = "testeteste"
        val resultadoEsperado = false

        val resultado = textContemNumero(senha)

        Assert.assertEquals(resultadoEsperado, resultado)
    }

    @Test
    fun sequenciaNumericaSucessoTeste() {
        val sequencia = "1234"
        val resultadoEsperado = true

        val resultado = sequenciaNumericaSucesso(sequencia)

        Assert.assertEquals(resultadoEsperado, resultado)
    }

    @Test
    fun sequenciaNumericaErroTeste() {
        val sequencia = "1234a"
        val resultadoEsperado = false

        val resultado = sequenciaNumericaSucesso(sequencia)

        Assert.assertEquals(resultadoEsperado, resultado)
    }
}