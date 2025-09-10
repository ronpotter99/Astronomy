package ronpotter99.astronomy.controller

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import ronpotter99.astronomy.controller.interfaces.IDashboardController

@Controller
class DashboardController() : IDashboardController {

    private val logger = KotlinLogging.logger {}

    override fun rerouteToDashboard(): String {
        return "redirect:/dashboard"
    }

    override fun dashboard(model: Model): String {
        return "dashboard"
    }
}