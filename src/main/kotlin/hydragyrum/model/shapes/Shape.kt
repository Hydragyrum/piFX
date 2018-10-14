package hydragyrum.model.shapes

interface Shape {
    val centre : Point

    fun isPointInside(point: Point): Boolean
}