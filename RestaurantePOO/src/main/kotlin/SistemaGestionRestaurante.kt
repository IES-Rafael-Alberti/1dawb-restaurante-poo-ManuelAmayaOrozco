class SistemaGestionRestaurante(private val mesas: List<Mesa>) {

    fun realizarPedido(numeroMesa: Int, pedido: Pedido) {
        
    }

    fun cerrarPedido(numeroMesa: Int, numeroPedido: Int? = null) {
        //TODO desarrollar este método...
    }

    fun cerrarMesa(numeroMesa: Int) {
        //TODO desarrollar este método...
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