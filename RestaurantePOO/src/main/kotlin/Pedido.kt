class Pedido(val numero: Int = generarnumPedido(),
             val platos: MutableList<Plato>,
             var estado: String = "pendiente") {

    val precio = calcularPrecio()

    var tiempoTotal = calcularTiempo()

    fun agregarPlato(plato: Plato) {
        platos.add(plato)
    }

    fun eliminarPlato(nombrePlato: String) {
        val busca = platos.find { it.nombre == nombrePlato }
        if (busca != null) {
            platos.remove(busca)
        }
    }

    fun calcularPrecio(): Double {
        var prec = 0.0
        for (plato in platos) {
            prec += plato.precio
        }
        return prec
    }

    fun calcularTiempo(): Int {
        var tiemTot = 0
        for (plato in platos) {
            tiemTot += plato.tiempoPreparacion
        }
        return tiemTot
    }

    override fun toString(): String {
        var listring = ""
        for (plato in platos) {
            listring += "$plato\n"
        }
        return listring
    }

    companion object {
        var contPedidos = 0

        private fun generarnumPedido(): Int {
            contPedidos++
            return contPedidos
        }
    }
}