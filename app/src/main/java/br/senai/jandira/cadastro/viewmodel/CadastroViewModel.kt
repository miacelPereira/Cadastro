package br.senai.jandira.cadastro.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.SystemClock
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class CadastroViewModel : ViewModel() {
    // ":" na class significa extends

    // Criando a variável que vai controlar o cadastro de acordo com o insert no banco
    val loading = MutableLiveData<Boolean>()

    fun cadastrarUsuario(){
        loading.postValue(true)

        // Efetuar o cadastro

        //doAsync abre uma nova thread para realizar uma outra função
        doAsync {
            //Colocando o sistema para dormir
            SystemClock.sleep(2000)
            //Voltando para a thread inicial e acessando a função de usuario carregado
            uiThread { cadastroUsuarioSucesso() }
        }

    }
    fun cadastroUsuarioSucesso(){
        loading.postValue(false)
    }

}