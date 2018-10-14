package hydragyrum.model.channels

import hydragyrum.model.shapes.Circle
import hydragyrum.model.shapes.Point
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.random.Random

fun CoroutineScope.generateRandomPoints(max: Double) = produce(EmptyCoroutineContext, 10) {
    while(true) send(Point(Random.nextDouble(max), Random.nextDouble(max)))
}

fun CoroutineScope.mapToProportions( points: ReceiveChannel<Point>, circle: Circle) = produce(EmptyCoroutineContext, 10) {
    var inSquare = 0
    var inCircle = 0
    for(point in points) {
        if(circle.isPointInside(point)){
            inCircle++
        }
        send(++inSquare to inCircle)
    }
}

fun CoroutineScope.calculatePi(pairs: ReceiveChannel<Pair<Int, Int>>) = produce(EmptyCoroutineContext, 10) {
    for (pair in pairs){
        send((4.0 * pair.second) / pair.first)
    }
}