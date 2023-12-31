package pattern

import java.util.Observable
import java.util.Observer

class Coffee(val name: String)

// Subject
class Barista : Observable() {
    private lateinit var coffeeName: String

    fun orderCoffee(name: String) {
        this.coffeeName = name
    }

    fun makeCoffee() {
        setChanged()
        notifyObservers(Coffee(this.coffeeName))
    }
}

// Observer
class Customer(val name: String) : Observer {
    override fun update(o: Observable?, arg: Any?) {
        val coffee = arg as Coffee
        println("Observable $o : ${name}이 ${coffee.name}을 받았습니다")
    }
}

fun main() {
    val barista = Barista()
    barista.orderCoffee("Ice Americano")

    val customer = Customer("Customer1")
    val customer2 = Customer("Customer2")
    val customer3 = Customer("Customer3")

    barista.addObserver(customer)
    barista.addObserver(customer2)
    barista.addObserver(customer3)

    barista.makeCoffee()
}