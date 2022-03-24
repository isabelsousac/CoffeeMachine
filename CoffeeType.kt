package machine

enum class CoffeeType(
    val waterQuantity: Int,
    val milkQuantity: Int,
    val beansQuantity: Int,
    val price: Int
) {
    ESPRESSO(
        waterQuantity = 250,
        milkQuantity = 0,
        beansQuantity = 16,
        price = 4
    ),
    LATTE(
        waterQuantity = 350,
        milkQuantity = 75,
        beansQuantity = 20,
        price = 7
    ),
    CAPPUCCINO(
        waterQuantity = 200,
        milkQuantity = 100,
        beansQuantity = 12,
        price = 6
    )
}