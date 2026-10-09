package com.example.cardapiodigital

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetalhesActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_PRATO_ID = "prato_id"
        private const val ESTADO_QUANTIDADE = "quantidade"
    }

    // A referência muda, mas cada ItemPedido criado continua imutável.
    private lateinit var itemPedido: ItemPedido
    private lateinit var textoQuantidade: TextView
    private lateinit var textoTotal: TextView
    private lateinit var botaoDiminuir: Button
    private lateinit var botaoAumentar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.light(Color.TRANSPARENT, Color.TRANSPARENT),
            navigationBarStyle = SystemBarStyle.light(Color.WHITE, Color.WHITE)
        )
        setContentView(R.layout.activity_detalhes)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { view, insets ->
            val barras = insets.getInsets(
                WindowInsetsCompat.Type.systemBars() or WindowInsetsCompat.Type.displayCutout()
            )
            view.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }

        // -1 representa a ausência do extra; nenhum prato possui esse id.
        val pratoId = intent.getIntExtra(EXTRA_PRATO_ID, -1)
        val prato = CardapioMock.buscarPorId(pratoId)

        if (prato == null) {
            Toast.makeText(this, R.string.prato_nao_encontrado, Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Recupera a quantidade quando a Activity é recriada (ex.: rotação).
        val quantidade = (savedInstanceState?.getInt(ESTADO_QUANTIDADE) ?: 1)
            .coerceIn(1, 20)
        itemPedido = ItemPedido(prato, quantidade)

        val imagem = findViewById<ImageView>(R.id.imagemDetalhe)
        imagem.setImageResource(prato.imagemResId)
        imagem.contentDescription = getString(R.string.imagem_prato, prato.nome)
        findViewById<TextView>(R.id.nomeDetalhe).text = prato.nome
        findViewById<TextView>(R.id.categoriaDetalhe).text = prato.categoria
        findViewById<TextView>(R.id.precoDetalhe).text = formatarMoeda(prato.precoCentavos)
        findViewById<TextView>(R.id.descricaoDetalhe).text = prato.descricao

        // null significa informação desconhecida, e não ausência de alergênicos.
        findViewById<TextView>(R.id.alergenicosDetalhe).text =
            prato.alergenicos ?: getString(R.string.alergenicos_nao_informados)

        textoQuantidade = findViewById(R.id.textoQuantidade)
        textoTotal = findViewById(R.id.textoTotal)
        botaoDiminuir = findViewById(R.id.botaoDiminuir)
        botaoAumentar = findViewById(R.id.botaoAumentar)

        findViewById<Button>(R.id.botaoVoltar).setOnClickListener {
            finish() // Fecha os detalhes e retorna à tela anterior.
        }

        botaoDiminuir.setOnClickListener {
            if (itemPedido.quantidade > 1) {
                itemPedido = itemPedido.copy(quantidade = itemPedido.quantidade - 1)
                atualizarResumo()
            }
        }
        botaoAumentar.setOnClickListener {
            if (itemPedido.quantidade < 20) {
                itemPedido = itemPedido.copy(quantidade = itemPedido.quantidade + 1)
                atualizarResumo()
            }
        }
        atualizarResumo()
    }

    private fun atualizarResumo() {
        textoQuantidade.text = itemPedido.quantidade.toString()
        textoTotal.text = formatarMoeda(itemPedido.totalCentavos)
        botaoDiminuir.isEnabled = itemPedido.quantidade > 1
        botaoAumentar.isEnabled = itemPedido.quantidade < 20
    }

    override fun onSaveInstanceState(outState: Bundle) {
        if (::itemPedido.isInitialized) {
            outState.putInt(ESTADO_QUANTIDADE, itemPedido.quantidade)
        }
        super.onSaveInstanceState(outState)
    }
}
