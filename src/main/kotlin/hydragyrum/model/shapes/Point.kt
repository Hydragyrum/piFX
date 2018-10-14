package hydragyrum.model.shapes

import kotlin.math.sqrt

data class Point(private val pair: Pair<Double, Double>) {
    val x: Double
    get() = pair.first

    val y: Double
    get() = pair.second

    constructor(x: Double, y: Double): this(x to y)

    fun getDistanceFrom(point: Point): Double {
        val diffX = point.x - x
        val diffY = point.y - y
        return sqrt(diffX*diffX + diffY*diffY)
    }

}