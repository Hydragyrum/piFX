package hydragyrum.controller

import hydragyrum.events.PiEstimateEvent
import hydragyrum.events.PointEvent
import hydragyrum.model.channels.calculatePi
import hydragyrum.model.channels.generateRandomPoints
import hydragyrum.model.channels.mapToProportions
import hydragyrum.model.shapes.Circle
import hydragyrum.model.shapes.Point
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import tornadofx.Controller
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

class PiFxController(val squareSize: Double) : Controller() {
    val circle = Circle(Point(squareSize / 2.0, squareSize / 2.0), squareSize / 2.0)
    private var context: CoroutineContext = EmptyCoroutineContext
    private var running = false

    fun startCalculations() = runBlocking(context) {
        running = true
        context = coroutineContext
        val points = generateRandomPoints(squareSize)
        val proportionsMapping = mapToProportions(points, circle)
        val piStream = calculatePi(proportionsMapping)
        while (running) {
            val point = points.receive()
            val estimate = piStream.receive()
            delay(1)
            fire(PointEvent(point))
            fire(PiEstimateEvent(estimate.toString()))
        }
    }

    fun stopCalculations() = runBlocking {
        running = false
        context.cancelChildren()
        context = EmptyCoroutineContext
    }
}
