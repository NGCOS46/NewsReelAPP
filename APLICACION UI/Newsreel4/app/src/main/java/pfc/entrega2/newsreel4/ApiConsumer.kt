package pfc.entrega2.newsreel4

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
interface ApiConsumer {
    @POST("/usuarios")
    suspend fun Register(@Body body: AuthenticationRequest): Response<ServerResponse>
    @GET("/usuarios")
    suspend fun getUser(@Query("correo") email: String): Response<ServerResponse>
}