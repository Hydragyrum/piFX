package hydragyrum.model.channels

import hydragyrum.model.shapes.Circle
import hydragyrum.model.shapes.Point
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlin.random.Random

fun CoroutineScope.generateRandomPoints(max: Double) = produce {
    while(true) send(Point(Random.nextDouble(max), Random.nextDouble(max)))
}

fun CoroutineScope.mapToProportions( points: ReceiveChannel<Point>, circle: Circle) = produce {
    var inSquare = 0
    var inCircle = 0
    for(point in points) {
        if(circle.isPointInside(point)){
            inCircle++
        }
        send(++inSquare to inCircle)
    }
}

fun CoroutineScope.calculatePi(pairs: ReceiveChannel<Pair<Int, Int>>) = produce {
    for (pair in pairs){
        send((4.0 * pair.second) / pair.first)
    }
}