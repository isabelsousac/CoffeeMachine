package machine


fun main() {
    do {
        print("Write action (buy, fill, take, remaining, exit): ")
        val chosenAction = readln()
        println()
        when (chosenAction) {
            "remaining" -> CoffeeMachine.printInfo()
            "buy" -> {
                print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
                val coffeeType = when (readln()) {
                    "1" -> CoffeeType.ESPRESSO
                    "2" -> CoffeeType.LATTE
                    "3" -> CoffeeType.CAPPUCCINO
                    "back" -> continue
                    else -> continue
                }
                val missingIngredient = CoffeeMachine.getInsufficientIngredient(coffeeType)
                if (missingIngredient == null) {
                    CoffeeMachine.buyCoffee(coffeeType)
                } else {
                    println("Sorry, not enough $missingIngredient!")
                }
            }
            "fill" -> {
                print("Write how many ml of water do you want to add: ")
                val addedWater = readln().toInt()
                print("Write how many ml of milk do you want to add: ")
                val addedMilk = readln().toInt()
                print("Write how many grams of coffee beans do you want to add: ")
                val addedBeans = readln().toInt()
                print("Write how many disposable cups of coffee do you want to add: ")
                val addedCups = readln().toInt()
                CoffeeMachine.fill(addedWater, addedMilk, addedBeans, addedCups)
            }
            "take" -> {
                val money = CoffeeMachine.takeMoney()
                println("I gave you $$money")
            }
            "exit" -> return
        }
        println()
    } while (true)
}