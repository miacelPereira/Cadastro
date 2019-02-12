package br.senai.jandira.cadastro.data.service
import br.senai.jandira.cadastro.model.Usuario
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("/INF4M20191/inserir_usuario.php")
    fun cadastrarUsuario(@Body usuario: Usuario): Call<String>

}