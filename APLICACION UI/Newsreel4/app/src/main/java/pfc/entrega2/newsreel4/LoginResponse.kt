package pfc.entrega2.newsreel4

data class LoginResponse(
    val success: Boolean,
    val user: User,
    val jwt: String
)
