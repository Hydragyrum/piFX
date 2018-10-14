package hydragyrum.events

import tornadofx.FXEvent

data class PiEstimateEvent( val estimate:String ) : FXEvent()