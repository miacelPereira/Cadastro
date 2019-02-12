package br.senai.jandira.cadastro.data.repository

import br.senai.jandira.cadastro.data.service.ApiService
import br.senai.jandira.cadastro.domain.repository.UsuarioRepository
import br.senai.jandira.cadastro.model.ApiResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call


// Cadastrando de fato o usuario
class UsuarioRepositoryImpl (val apiService: ApiService): UsuarioRepository {
    override fun insert(usuario: Usuario): Call<ApiResult> {
        return apiService.cadastrarUsuario(usuario)
    }
}
