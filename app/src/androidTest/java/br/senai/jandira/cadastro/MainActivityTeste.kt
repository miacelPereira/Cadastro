package br.senai.jandira.cadastro

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.widget.Button
import android.widget.EditText
import br.senai.jandira.cadastro.ui.MainActivity
import org.hamcrest.CoreMatchers.equalTo
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith (AndroidJUnit4::class)
class MainActivityTeste {

    /* Precisa realizar a estância da mainactivity */
    @get:Rule
    val rule = ActivityTestRule(MainActivity::class.java)

    /* lateinit diz para o kotlin que a variavel vai ser inicializada com o valor null */
    lateinit var activity: MainActivity

    /* @Before serve para setar valores antes de qualquer teste */
    @Before
    fun setUp(){
        activity = rule.activity

    }

    /* Testes */
    /* Teste para saber se a activity carregou corretamente */
    @Test
    fun activityCarregaHintCorretamente(){

        /* Acessando os campos */
        val txtNome = activity.findViewById<EditText>(R.id.txtNome)
        val txtEmail = activity.findViewById<EditText>(R.id.txtEmail)
        val txtPass = activity.findViewById<EditText>(R.id.txtPass)

        Assert.assertThat(txtNome.hint.toString(), equalTo("Nome"))
        Assert.assertThat(txtEmail.hint.toString(), equalTo("E-mail"))
        Assert.assertThat(txtPass.hint.toString(), equalTo("Senha"))
    }

    /* Teste para verificar se o botão carregou e para confirmar o texto dele */
    @Test
    fun botaoCarregaCorretamente(){
        val btnSave = activity.findViewById<Button>(R.id.btnSave)


        Assert.assertThat(btnSave.text.toString(), equalTo("Cadastrar"))


    }

    /* Teste de mensagens de erro */
    @Test
    fun c_testeNomeComDoisCaracteres(){

        /* acessar o componente pelo hint, sendo que desse modo é possível acessar o objeto com text e id */
        val txtNome = onView(withHint("Nome"))

        /* Realizar ação com o objeto */
        txtNome.perform(typeText("al")).perform(closeSoftKeyboard())
        /* Clicando no botão */
        onView(withText(R.string.sSave)).perform(click())

        /* Verificando se o erro ocorreu no editText */
        txtNome.check(matches(hasErrorText(R.string.sMinimoCaracterNome.toString())))


    }
    @Test
    fun d_testeEmailIncorreta(){

        /* acessar o componente pelo hint, sendo que desse modo é possível acessar o objeto com text e id */
        val txtEmail = onView(withHint(R.string.sEmail))

        /* Realizar ação com o objeto */
        txtEmail.perform(typeText("al")).perform(closeSoftKeyboard())
        /* Clicando no botão */
        onView(withText(R.string.sSave)).perform(click())

        /* Verificando se o erro ocorreu no editText */
        txtEmail.check(matches(hasErrorText(R.string.sEmailArroba.toString())))


    }
    @Test
    fun e_testeSenhaIncorretaSequencia(){

        /* acessar o componente pelo hint, sendo que desse modo é possível acessar o objeto com text e id */
        val txtSenha = onView(withHint(R.string.sPassword))

        /* Realizar ação com o objeto */
        txtSenha.perform(typeText("1234")).perform(closeSoftKeyboard())
        /* Clicando no botão */
        onView(withText(R.string.sSave)).perform(click())

        /* Verificando se o erro ocorreu no editText */
        txtSenha.check(matches(hasErrorText(R.string.sSenhaComSequencia.toString())))


    }
    @Test
    fun f_testeSenhaIncorretaQuantidade(){

        /* acessar o componente pelo hint, sendo que desse modo é possível acessar o objeto com text e id */
        val txtSenha = onView(withHint(R.string.sPassword))

        /* Realizar ação com o objeto */
        txtSenha.perform(typeText("as1")).perform(closeSoftKeyboard())
        /* Clicando no botão */
        onView(withText(R.string.sSave)).perform(click())

        /* Verificando se o erro ocorreu no editText */
        txtSenha.check(matches(hasErrorText(R.string.sSenhaComQuatroCaracter.toString())))


    }
    @Test
    fun g_testeSenhaIncorretaSemNumero(){

        /* acessar o componente pelo hint, sendo que desse modo é possível acessar o objeto com text e id */
        val txtSenha = onView(withHint(R.string.sPassword))

        /* Realizar ação com o objeto */
        txtSenha.perform(typeText("mario")).perform(closeSoftKeyboard())
        /* Clicando no botão */
        onView(withText(R.string.sSave)).perform(click())

        /* Verificando se o erro ocorreu no editText */
        txtSenha.check(matches(hasErrorText(R.string.sSenhaComNumeros.toString())))


    }
}