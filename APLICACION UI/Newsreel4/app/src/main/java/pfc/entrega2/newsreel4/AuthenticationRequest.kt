package pfc.entrega2.newsreel4

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class AuthenticationRequest(

    val id_usuario: Int?, // This can be nullable if you're not providing it during registration
    val nombre: String,
    val correo: String,
    val contrasena: String,
    val fecha: String
)
