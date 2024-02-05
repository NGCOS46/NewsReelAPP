package pfc.entrega2.newsreel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import android.widget.Button
import android.widget.EditText

import okhttp3.Dispatcher

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




class RegisterActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etPassword: EditText
    private lateinit var etDate: EditText
    private lateinit var btnRegister: Button
    private lateinit var tvGoToLogin: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        etName = findViewById(R.id.et_nombre)
        etEmail = findViewById(R.id.et_email_registration)
        etPassword = findViewById(R.id.et_password_registration)
        etDate =findViewById(R.id.et_registration_date)
        btnRegister = findViewById(R.id.btn_register)
        tvGoToLogin = findViewById(R.id.tv_go_to_login)

        btnRegister.setOnClickListener {
            registerUser()
        }

        tvGoToLogin.setOnClickListener {
            goToLogin1()
        }


    }
    private fun goToLogin1 (){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


    private fun registerUser() {
        val name = etName.text.toString()
        val email = etEmail.text.toString()
        val password = etPassword.text.toString()
        val date = etDate.text.toString()

        // Create an instance of AuthenticationRequest
        val registrationRequest = AuthenticationRequest(
            id_usuario = null,
            nombre = name,
            correo = email,
            contrasena = password,
            fecha = date
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