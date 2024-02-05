package pfc.entrega2.newsreel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.*;

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var tvGoToRegister: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnLogin = findViewById(R.id.btn_go_to_menu)
        tvGoToRegister = findViewById(R.id.tv_go_to_register)

        btnLogin.setOnClickListener {
            LoginUser()
            //una vez logeado que nos vaya a la pagina menu
        }

        tvGoToRegister.setOnClickListener {
            goToRegister()
        }
    }
    private  fun goToRegister(){
        val i = Intent(this, RegisterActivity::class.java)
        startActivity(i)
    }

    private fun LoginUser() {
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            // Create an instance of AuthenticationRequest
            val registrationRequest = AuthenticationRequest(
                id_usuario = null,
                nombre = "",  // Adjust this if needed
                correo = email,
                contrasena = password,
                fecha = ""
            )

            // Call the function to perform API service call
            performApiCall(registrationRequest)
        }
    private fun performApiCall(registrationRequest: AuthenticationRequest) {
        val APIService = APIService.getService()

        GlobalScope.launch(Dispatchers.Main) {
            try {
                // Call the Register function with the AuthenticationRequest object
                val response = APIService.Register(registrationRequest)

                if (response.isSuccessful) {
                    val serverResponse = response.body()
                    if (serverResponse?.success == true) {
                        // Registration successful
                        println("Registro exitoso")
                        // You can navigate to the login activity or perform other actions as needed
                    } else {
                        // Handle server response error
                        println("Error en el registro: ${serverResponse?.message}")
                    }
                } else {
                    // Handle HTTP error
                    println("Error en la solicitud: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle network error
                println("Error de red: ${e.message}")
            }

        }
    }


}