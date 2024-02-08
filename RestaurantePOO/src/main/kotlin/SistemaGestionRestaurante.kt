class SistemaGestionRestaurante(private val mesas: MutableList<Mesa>) {

    fun realizarPedido(numeroMesa: Int, pedido: Pedido) {
        for (mesa in mesas) {
            if (mesa.estado == "ocupada") {
                mesa.pedidos.add(pedido)
                break
            }
        }
    }

    fun cerrarPedido(numeroMesa: Int, numeroPedido: Int? = null) {
        if (numeroPedido == null) {
            val mesa = mesas[numeroMesa]
            val pedidos = mesa.pedidos
            val pedido = pedidos.lastOrNull()
            if (pedido != null) {
                pedido.estado = "servido"
                pedidos[pedidos.size - 1] = pedido
                mesa.pedidos = pedidos
                mesas[numeroMesa] = mesa
            }
        }
        else {
            val mesa = mesas[numeroMesa]
            val pedidos = mesa.pedidos
            val pedido = pedidos.find { it.numero == numeroPedido }
            if (pedido != null) {
                pedido.estado = "servido"
                pedidos[numeroPedido] = pedido
                mesa.pedidos = pedidos
                mesas[numeroMesa] = mesa
            }
        }
    }

    fun cerrarMesa(numeroMesa: Int) {
        val mesa = mesas[numeroMesa]
        val pedidos = mesa.pedidos
        val cerrar = pedidos.all { it.estado == "servido" }
        if (cerrar) {
            mesa.liberarMesa()
            mesas[numeroMesa] = mesa
        }
    }

    fun buscarPlatos(): List<String>? {
        val platos = mesas.flatMap { it.pedidos }.flatMap { it.platos }.map { it.nombre }
        return platos.ifEmpty { null }
    }

    fun contarPlato(nombre: String): Int? {
        val count = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .count { it.nombre == nombre }
        return if (count > 0) count else null
    }

    fun buscarPlatoMasPedido(): List<String>? {
        val platoCounts = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .groupingBy { it.nombre }
            .eachCount()

        val maxCount = platoCounts.maxByOrNull { it.value }?.value
        return maxCount?.let { max -> platoCounts.filter { it.value == max }.keys.toList() }
    }
}