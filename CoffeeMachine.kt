package machine


object CoffeeMachine {
    private var waterQuantity: Int = 400
    private var milkQuantity: Int = 540
    private var beansQuantity: Int = 120
    private var cupsQuantity: Int = 9
    private var money: Int = 550

    fun printInfo() {
        println("""
        The coffee machine has:
        $waterQuantity ml of water
        $milkQuantity ml of milk
        $beansQuantity g of coffee beans
        $cupsQuantity disposable cups
        $$money of money
        """.trimIndent() + "\n")
    }

    fun getInsufficientIngredient(coffeeType: CoffeeType): String? {
        val hasEnoughWater = checkWater(coffeeType.waterQuantity)
        val hasEnoughMilk = checkMilk(coffeeType.milkQuantity)
        val hasEnoughCoffee = checkCoffeeBeans(coffeeType.beansQuantity)

        return if (hasEnoughWater && hasEnoughMilk && hasEnoughCoffee) {
            null
        } else {
            var insufficientIngredient = ""
            val ingredients = listOf(hasEnoughWater, hasEnoughMilk, hasEnoughCoffee)
            for (i in ingredients.indices) {
                insufficientIngredient = if (!ingredients[0]) {
                    "water"
                } else if (!ingredients[1]) {
                    "milk"
                } else {
                    "coffee beans"
                }
            }
            insufficientIngredient
        }
    }

    fun buyCoffee(coffeeType: CoffeeType) {
        waterQuantity -= coffeeType.waterQuantity
        beansQuantity -= coffeeType.beansQuantity
        milkQuantity -= coffeeType.milkQuantity
        money += coffeeType.price
        cupsQuantity -= 1
    }

    fun fill(addedWater: Int, addedMilk: Int, addedBeans: Int, addedCups: Int) {
        waterQuantity += addedWater
        milkQuantity += addedMilk
        beansQuantity += addedBeans
        cupsQuantity += addedCups
    }

    fun takeMoney(): Int {
        val previousMoney = money
        money = 0
        return previousMoney
    }

    private fun checkWater(waterRequired: Int): Boolean = waterQuantity >= waterRequired
    private fun checkMilk(milkRequired: Int): Boolean = milkQuantity >= milkRequired
    private fun checkCoffeeBeans(beansRequired: Int): Boolean = beansQuantity >= beansRequired
}