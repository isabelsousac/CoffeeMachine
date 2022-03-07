package machine

const val WATER_ESPRESSO = 250
const val MILK_ESPRESSO = 0
const val BEANS_ESPRESSO = 16
const val VALUE_ESPRESSO = 4

const val WATER_LATTE = 350
const val MILK_LATTE = 75
const val BEANS_LATTE = 20
const val VALUE_LATTE = 7

const val WATER_CAPPUCCINO = 200
const val MILK_CAPPUCCINO = 100
const val BEANS_CAPPUCCINO = 12
const val VALUE_CAPPUCCINO = 6

fun main() {
    var waterQt = 400
    var milkQt = 540
    var beansQt = 120
    var cupsQt = 9
    var money = 550

    do {
        println()
        print("Write action (buy, fill, take, remaining, exit): ")
        val chosenAction = readln()
        println()
        when (chosenAction) {
            "remaining" -> printInfo(waterQt, milkQt, beansQt, cupsQt, money)

            "buy" -> {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                when (readln()) {
                    "1" -> {
                        val missingIngredient = getInsufficientIngredient(waterQt, milkQt, beansQt, WATER_ESPRESSO, BEANS_ESPRESSO, MILK_ESPRESSO)
                        if (missingIngredient == null) {
                            println("I have enough resources, making you a coffee!")
                            waterQt -= WATER_ESPRESSO
                            beansQt -= BEANS_ESPRESSO
                            money += VALUE_ESPRESSO
                            cupsQt--
                        } else {
                            println("Sorry, not enough $missingIngredient!")
                        }
                    }
                    "2" -> {
                        val missingIngredient = getInsufficientIngredient(waterQt, milkQt, beansQt, WATER_LATTE, BEANS_LATTE, MILK_LATTE)
                        if (missingIngredient == null) {
                            println("I have enough resources, making you a coffee!")
                            waterQt -= WATER_LATTE
                            beansQt -= BEANS_LATTE
                            milkQt -= MILK_LATTE
                            money += VALUE_LATTE
                            cupsQt--
                        } else {
                            println("Sorry, not enough $missingIngredient!")
                        }
                    }
                    "3" -> {
                        val missingIngredient = getInsufficientIngredient(waterQt, milkQt, beansQt, WATER_CAPPUCCINO, BEANS_CAPPUCCINO, MILK_CAPPUCCINO)
                        if (missingIngredient == null) {
                            waterQt -= WATER_CAPPUCCINO
                            beansQt -= BEANS_CAPPUCCINO
                            milkQt -= MILK_CAPPUCCINO
                            money += VALUE_CAPPUCCINO
                            cupsQt--
                        } else {
                            println("Sorry, not enough $missingIngredient!")
                        }
                    }
                    "back" -> continue
                }
            }
            "fill" -> {
                val refreshedItemValues = fillMachine()
                waterQt += refreshedItemValues[0]
                milkQt += refreshedItemValues[1]
                beansQt += refreshedItemValues[2]
                cupsQt += refreshedItemValues[3]
            }
            "take" -> {
                println("I gave you $$money")
                money = 0
            }
            "exit" -> return
        }
    } while (true)
}

private fun printInfo(waterQt: Int, milkQt: Int, beansQt: Int, cupsQt: Int, money: Int) {
    println("""
        The coffee machine has:
        $waterQt ml of water
        $milkQt ml of milk
        $beansQt g of coffee beans
        $cupsQt disposable cups
        $$money of money
        
        """.trimIndent())
}

fun getInsufficientIngredient(
    waterQt: Int,
    milkQt: Int,
    beansQt: Int,
    waterRequired: Int,
    beansRequired: Int,
    milkRequired: Int,
): String? {
    val hasEnoughWater = checkWater(waterQt, waterRequired)
    val hasEnoughMilk = checkMilk(milkQt, milkRequired)
    val hasEnoughCoffee = checkCoffeeBeans(beansQt, beansRequired)

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

private fun checkWater(waterQt: Int, waterRequired: Int): Boolean = waterQt >= waterRequired
private fun checkMilk(milkQt: Int, milkRequired: Int): Boolean = milkQt >= milkRequired
private fun checkCoffeeBeans(beansQt: Int, beansRequired: Int): Boolean = beansQt >= beansRequired

fun fillMachine(): List<Int> {
    print("Write how many ml of water do you want to add: ")
    val addedWater = readln().toInt()
    print("Write how many ml of milk do you want to add: ")
    val addedMilk = readln().toInt()
    print("Write how many grams of coffee beans do you want to add: ")
    val addedBeans = readln().toInt()
    print("Write how many disposable cups of coffee do you want to add: ")
    val addedCups = readln().toInt()

    return listOf(addedWater, addedMilk, addedBeans, addedCups)
}




