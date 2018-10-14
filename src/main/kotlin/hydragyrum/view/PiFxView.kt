package hydragyrum.view

import hydragyrum.controller.PiFxController
import hydragyrum.events.ClearCanvasEvent
import hydragyrum.events.PiEstimateEvent
import hydragyrum.events.PointEvent
import javafx.scene.paint.Color
import tornadofx.*

class PiFxView : View("PiFX View") {
    private val piFxController = PiFxController(600.0)

    override val root = vbox {
        canvas(piFxController.squareSize, piFxController.squareSize){
            subscribe<PointEvent> { event ->
                graphicsContext2D.fill = if(piFxController.circle.isPointInside(event.point)) Color.RED else Color.BLUE
                graphicsContext2D.fillOval(event.point.x, event.point.y, 1.0, 1.0)
            }

            subscribe<ClearCanvasEvent> {
                graphicsContext2D.clearRect(0.0, 0.0, piFxController.squareSize, piFxController.squareSize)
            }
        }
        label("0.0") {
            subscribe<PiEstimateEvent> { event ->
                text = event.estimate
            }
        }
        hbox {
            button("Start Estimate") {
                action {
                    runAsync {
                        piFxController.startCalculations()
                    }
                }
            }
            button("Stop Estimate") {
                action {
                    runAsync {
                        piFxController.stopCalculations()
                        fire(ClearCanvasEvent())
                    }
                }
            }
        }
    }

}
