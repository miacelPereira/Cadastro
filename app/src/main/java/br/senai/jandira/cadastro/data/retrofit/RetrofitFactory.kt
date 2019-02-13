package br.senai.jandira.cadastro.data.retrofit

import br.senai.jandira.cadastro.data.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    // URL base
    private fun retrofit() = Retrofit.Builder().baseUrl("http://10.0.2.2").addConverterFactory(GsonConverterFactory.create()).build()

    fun createApiService () = retrofit().create(ApiService::class.java)

}