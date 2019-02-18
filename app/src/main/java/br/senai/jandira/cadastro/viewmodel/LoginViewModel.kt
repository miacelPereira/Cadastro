package br.senai.jandira.cadastro.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.senai.jandira.cadastro.data.repository.LoginRepositoryImpl
import br.senai.jandira.cadastro.data.retrofit.RetrofitFactory
import br.senai.jandira.cadastro.domain.userCase.LogarUsuario
import br.senai.jandira.cadastro.model.LoginResult
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginViewModel : ViewModel() {

    //Variavel Boolean
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val sucesso = MutableLiveData<LoginResult>()

    private val apiService = RetrofitFactory().createApiService()
    private val repository = LoginRepositoryImpl(apiService)

    val logarUsuario = LogarUsuario(repository, callback())

    inner class callback : Callback<LoginResult> {
        override fun onFailure(call: Call<LoginResult>?, t: Throwable?) {
            Log.e("Login view model", t?.message)
            error.postValue(true)
        }

        override fun onResponse(call: Call<LoginResult>?, response: Response<LoginResult>?) {
            val retornoApi = response?.body()
            loginUsuarioSucesso(retornoApi)
        }
    }

    fun loginUsuario(email: String, senha: String) {
        loading.postValue(true)
        error.postValue(false)

        // Efetuar o cadastro
        logarUsuario.execute(email, senha)

    }

    fun loginUsuarioSucesso(result: LoginResult?) {
        loading.postValue(false)
        result?.let {
            sucesso.postValue(result)

        }
    }

}