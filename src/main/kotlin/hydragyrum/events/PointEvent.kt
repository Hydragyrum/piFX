package hydragyrum.events

import hydragyrum.model.shapes.Point
import tornadofx.FXEvent

data class PointEvent(val point: Point) : FXEvent()