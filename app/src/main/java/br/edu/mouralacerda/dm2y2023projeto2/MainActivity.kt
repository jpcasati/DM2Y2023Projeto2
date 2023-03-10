package br.edu.mouralacerda.dm2y2023projeto2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.edu.mouralacerda.dm2y2023projeto2.databinding.ActivityMainBinding
import com.google.zxing.integration.android.IntentIntegrator

class MainActivity : AppCompatActivity() {

    private val b by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(b.root)

        b.btnCamera.setOnClickListener {
            val scanner = IntentIntegrator(this)
            scanner.initiateScan()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK) {
            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if(result != null) {
                if(result.getContents() == null) {
                    b.txtCodigo.text = "QR Code não encontrado"
                } else {
                    var texto = result.contents.trim()
                    b.txtCodigo.text = texto
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}