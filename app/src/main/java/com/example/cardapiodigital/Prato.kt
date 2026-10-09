package com.example.cardapiodigital

import java.text.NumberFormat
import java.util.Locale

// As propriedades val não podem ser reatribuídas depois da criação do prato.
data class Prato(
    val id: Int,
    val nome: String,
    val categoria: String,
    val descricao: String,
    val precoCentavos: Int,
    val imagemResId: Int,
    val alergenicos: String?
)

// Valores monetários são guardados em centavos: 2590 significa R$ 25,90.
// A conversão é feita apenas ao apresentar o valor na interface.
fun formatarMoeda(centavos: Int): String {
    val formato = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
    return formato.format(centavos / 100.0)
}
