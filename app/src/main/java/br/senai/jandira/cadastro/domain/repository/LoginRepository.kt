package br.senai.jandira.cadastro.domain.repository

import br.senai.jandira.cadastro.model.LoginResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call

interface LoginRepository {
    fun logar(email:String, senha:String): Call<LoginResult>
}