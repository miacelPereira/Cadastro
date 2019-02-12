package br.senai.jandira.cadastro.domain.repository

import br.senai.jandira.cadastro.model.ApiResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call

interface UsuarioRepository {

    fun insert(usuario: Usuario): Call<ApiResult>

}