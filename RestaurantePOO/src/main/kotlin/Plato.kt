class Plato(nombre: String,
            precio: Double,
            tiempoPreparacion: Int,
            val ingredientes: MutableList<String>) {

    var nombre = nombre
        set(value) {
            require(nombre.isNotEmpty()) { "El nombre del plato no puede ser vacío." }
            field = value
        }

    var precio = precio
        set(value) {
            require(precio > 0) { "El precio ha de ser mayor que 0." }
            field = value
        }

    var tiempoPreparacion = tiempoPreparacion
        set(value) {
            require(tiempoPreparacion > 1) { "El tiempo de preparación ha de ser mayor a 1 minuto."}
            field = value
        }

    init {
        require(nombre.isNotEmpty()) { "El nombre del plato no puede ser vacío." }
        require(precio > 0) { "El precio ha de ser mayor que 0." }
        require(tiempoPreparacion > 1) { "El tiempo de preparación ha de ser mayor a 1 minuto."}
    }

    fun agregarIngrediente(ingrediente: String) {
        ingredientes.add(ingrediente)
    }

    override fun toString(): String {
        var ingList = ""
        var count = 0
        for (ingrediente in ingredientes) {
            if (count == ingredientes.size - 1) {
                ingList += ingrediente
            }
            else if (count == ingredientes.size - 2) {
                ingList += "$ingrediente y "
            }
            else {
                ingList += "$ingrediente, "
            }
            count++
        }
        return "${this.nombre} (${this.tiempoPreparacion} min.) -> ${"%.2f".format(this.precio)}€ (${ingList})"
    }
}