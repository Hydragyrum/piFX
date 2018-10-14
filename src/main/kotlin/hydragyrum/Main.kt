package hydragyrum

import hydragyrum.model.channels.calculatePi
import hydragyrum.model.channels.generateRandomPoints
import hydragyrum.model.channels.mapToProportions
import hydragyrum.model.shapes.Circle
import hydragyrum.model.shapes.Point
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) = runBlocking{
    val circle = Circle(Point(100.0, 100.0), 100.0)
    val point = generateRandomPoints(200.0)
    val proportionsMapping = mapToProportions(point, circle)
    val pi = calculatePi(proportionsMapping)
    for(i in 1..10000) {
        println("$i - ${pi.receive()}")
    }
    println("Done")
    coroutineContext.cancelChildren()
}