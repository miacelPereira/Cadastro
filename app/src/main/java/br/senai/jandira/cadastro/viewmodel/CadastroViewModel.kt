package br.senai.jandira.cadastro.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.senai.jandira.cadastro.data.repository.UsuarioRepositoryImpl
import br.senai.jandira.cadastro.data.retrofit.RetrofitFactory
import br.senai.jandira.cadastro.domain.userCase.CadastrarUsuario
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroViewModel : ViewModel() {
    // ":" na class significa extends

    // Criando a variável que vai controlar o cadastro de acordo com o insert no banco
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()

    // Criando API e Repository pois o cadastrar usuario depende deles
    private val apiService = RetrofitFactory().createApiService()
    private val repository = UsuarioRepositoryImpl(apiService)


    val cadastrarUsuario = CadastrarUsuario(repository, callback())

    inner class callback : Callback<String>{
        override fun onFailure(call: Call<String>?, t: Throwable?) {
            error.postValue(true)
        }
        override fun onResponse(call: Call<String>? , response: Response<String>?) {
           cadastroUsuarioSucesso()
        }

    }

    fun cadastrarUsuario(user: Usuario){
        loading.postValue(true)
        error.postValue(false)

        // Efetuar o cadastro
        cadastrarUsuario.execute(user)

    }
    fun cadastroUsuarioSucesso(){
        loading.postValue(false)
    }

}