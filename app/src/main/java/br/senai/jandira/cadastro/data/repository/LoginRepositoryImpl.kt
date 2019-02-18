package br.senai.jandira.cadastro.data.repository

import br.senai.jandira.cadastro.data.service.ApiService
import br.senai.jandira.cadastro.domain.repository.LoginRepository
import br.senai.jandira.cadastro.model.LoginResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call

class LoginRepositoryImpl(val apiService: ApiService): LoginRepository{
    override fun logar(email:String, senha:String): Call<LoginResult> {
        return apiService.logarUsuario(email, senha)
    }
}