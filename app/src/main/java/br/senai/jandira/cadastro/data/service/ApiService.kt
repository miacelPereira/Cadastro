package br.senai.jandira.cadastro.data.service
import br.senai.jandira.cadastro.model.ApiResult
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {

    @FormUrlEncoded
    @POST("/INF4M20191/inserir_usuario.php")
    fun cadastrarUsuario(@Field ("nome") nome:String, @Field ("email") email:String,@Field ("senha") senha:String): Call<ApiResult> //Passando os valores para a API |||| Se for usar o Node js, pode trocar o field por body e enviar o objeto

}