package br.senai.jandira.cadastro.domain.userCase

import br.senai.jandira.cadastro.domain.repository.UsuarioRepository
import br.senai.jandira.cadastro.model.ApiResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Callback

class CadastrarUsuario (val repository: UsuarioRepository, val callback: Callback<ApiResult>){

    fun execute(usuario: Usuario){

        repository.insert(usuario).enqueue(callback)
    }

}