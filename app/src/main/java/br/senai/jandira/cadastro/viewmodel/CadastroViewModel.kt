package br.senai.jandira.cadastro.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.senai.jandira.cadastro.data.repository.UsuarioRepositoryImpl
import br.senai.jandira.cadastro.data.retrofit.RetrofitFactory
import br.senai.jandira.cadastro.domain.userCase.CadastrarUsuario
import br.senai.jandira.cadastro.model.ApiResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroViewModel : ViewModel() {
    // ":" na class significa extends

    // Criando a variável que vai controlar o cadastro de acordo com o insert no banco
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val sucesso = MutableLiveData<ApiResult>()

    // Criando API e Repository pois o cadastrar usuario depende deles
    private val apiService = RetrofitFactory().createApiService()
    private val repository = UsuarioRepositoryImpl(apiService)


    val cadastrarUsuario = CadastrarUsuario(repository, callback())

    inner class callback : Callback<ApiResult>{
        override fun onFailure(call: Call<ApiResult>?, t: Throwable?) {
            Log.e("Cadastro view model", t?.message)
            error.postValue(true)
        }
        override fun onResponse(call: Call<ApiResult>? , response: Response<ApiResult>?) {
           val retornoApi = response?.body()
            cadastroUsuarioSucesso(retornoApi)
        }

    }

    fun cadastrarUsuario(user:Usuario){
        loading.postValue(true)
        error.postValue(false)

        // Efetuar o cadastro
        cadastrarUsuario.execute(user)

    }

    fun cadastroUsuarioSucesso(result:ApiResult?){
        loading.postValue(false)
        result?.let {
            sucesso.postValue(result)

        }
    }

}