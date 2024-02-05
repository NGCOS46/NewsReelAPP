package pfc.entrega2.newsreel4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val BtnGoLogin = findViewById<TextView>(R.id.Btn_logout)
        BtnGoLogin.setOnClickListener {
            BtntoLogin()
        }
    }

    private fun BtntoLogin (){
        val i = Intent(this, MainActivity::class.java)
        startActivity(i)
    }


}