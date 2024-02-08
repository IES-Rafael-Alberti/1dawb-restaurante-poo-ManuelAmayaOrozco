class Mesa(val numero: Int, val capacidad: Int) {

    var estado: String = "libre"

    var pedidos: MutableList<Pedido> = mutableListOf<Pedido>()

    init {
        require(capacidad in 1..6) { "La mesa soloo puede ser de 1 a 6 comensales." }
    }

    fun ocuparMesa() {
        if (estado == "libre") {
            estado = "ocupada"
        }
    }

    fun ocuparReserva() {
        if (estado == "reservada") {
            estado = "ocupada"
        }
    }

    fun liberarMesa() {
        estado = "libre"
    }

    fun agregarPedido(pedido: Pedido) {
        pedidos.add(pedido)
    }
}