package com.example.cardapiodigital

// Fonte de dados simulados: não faz requisições e não usa banco de dados.
object CardapioMock {
    val pratos: List<Prato> = listOf(
        Prato(
            id = 1,
            nome = "Burger da Casa",
            categoria = "Hambúrguer",
            descricao = "Pão macio, hambúrguer bovino, queijo, alface, tomate e molho da casa. Acompanha uma porção pequena de batatas.",
            precoCentavos = 2590,
            imagemResId = R.drawable.prato_burger,
            alergenicos = "Contém trigo, leite e ovos."
        ),
        Prato(
            id = 2,
            nome = "Pizza Margherita",
            categoria = "Pizza individual",
            descricao = "Massa artesanal com molho de tomate, muçarela e folhas de manjericão. Uma pizza individual preparada na hora.",
            precoCentavos = 3290,
            imagemResId = R.drawable.prato_pizza,
            alergenicos = "Contém trigo e leite."
        ),
        Prato(
            id = 3,
            nome = "Salada da Horta",
            categoria = "Salada",
            descricao = "Mix de folhas, tomate, cenoura e pepino, acompanhado de molho de limão servido separadamente.",
            precoCentavos = 1990,
            imagemResId = R.drawable.prato_salada,
            alergenicos = null
        )
    )

    // O resultado pode ser null quando o identificador não existe.
    fun buscarPorId(id: Int): Prato? {
        return pratos.find { it.id == id }
    }
}
