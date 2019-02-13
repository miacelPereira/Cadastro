package br.senai.jandira.cadastro.domain.userCase

import br.senai.jandira.cadastro.domain.repository.LoginRepository
import br.senai.jandira.cadastro.model.LoginResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Callback

class LogarUsuario (val repository: LoginRepository, val callback: Callback<LoginResult>){

    fun execute(email: String, senha: String){

        repository.logar(email, senha).enqueue(callback)
    }

}