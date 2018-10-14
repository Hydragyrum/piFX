package hydragyrum.model.shapes

data class Circle (override val centre: Point, val radius: Double): Shape {
    override fun isPointInside(point: Point): Boolean = point.getDistanceFrom(centre) < radius
}