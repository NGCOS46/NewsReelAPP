package pfc.entrega2.newsreel4

import okhttp3.OkHttpClient
import retrofit2.Retrofit import retrofit2.converter.gson.GsonConverterFactory import java.util.concurrent.TimeUnit
object APIService {
    private const val BASE_URL= "https://localhost:8082/api/newsreel/"
    fun getService(): ApiConsumer{

        val client: OkHttpClient= OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
        val builder: Retrofit.Builder = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
        val retrofit: Retrofit = builder.build()
        return retrofit.create(ApiConsumer::class.java)
    }
}