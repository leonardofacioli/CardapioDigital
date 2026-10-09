package com.example.cardapiodigital

// Representa a simulação exibida nos detalhes, sem efetuar um pedido real.
data class ItemPedido(
    val prato: Prato,
    val quantidade: Int = 1
) {
    init {
        require(quantidade in 1..20) { "A quantidade deve estar entre 1 e 20." }
    }

    val totalCentavos: Int
        get() = prato.precoCentavos * quantidade
}
