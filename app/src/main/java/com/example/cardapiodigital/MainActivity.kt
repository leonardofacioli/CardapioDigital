package com.example.cardapiodigital

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.graphics.Color

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // O tema é claro; os ícones das barras do sistema também são ajustados.
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE)
        )
        setContentView(R.layout.activity_main)

        // Evita que o conteúdo fique atrás do relógio, recortes e navegação.
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        // Os três blocos existem no XML; aqui preenchemos os dados de cada um.
        configurarPrato(CardapioMock.pratos[0], R.id.imagemBurger,
            R.id.nomeBurger, R.id.categoriaBurger, R.id.precoBurger, R.id.botaoBurger)
        configurarPrato(CardapioMock.pratos[1], R.id.imagemPizza,
            R.id.nomePizza, R.id.categoriaPizza, R.id.precoPizza, R.id.botaoPizza)
        configurarPrato(CardapioMock.pratos[2], R.id.imagemSalada,
            R.id.nomeSalada, R.id.categoriaSalada, R.id.precoSalada, R.id.botaoSalada)
    }

    private fun configurarPrato(
        prato: Prato,
        imagemId: Int,
        nomeId: Int,
        categoriaId: Int,
        precoId: Int,
        botaoId: Int
    ) {
        val imagem = findViewById<ImageView>(imagemId)
        imagem.setImageResource(prato.imagemResId)
        imagem.contentDescription = getString(R.string.imagem_prato, prato.nome)
        findViewById<TextView>(nomeId).text = prato.nome
        findViewById<TextView>(categoriaId).text = prato.categoria
        findViewById<TextView>(precoId).text = formatarMoeda(prato.precoCentavos)

        val botao = findViewById<Button>(botaoId)
        botao.contentDescription = getString(R.string.ver_detalhes_de, prato.nome)
        botao.setOnClickListener {
            // Intent explícita: informa exatamente qual Activity deve abrir.
            val intent = Intent(this, DetalhesActivity::class.java)
            intent.putExtra(DetalhesActivity.EXTRA_PRATO_ID, prato.id)
            startActivity(intent)
        }
    }
}
