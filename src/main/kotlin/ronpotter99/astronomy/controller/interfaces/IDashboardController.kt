package ronpotter99.astronomy.controller.interfaces

import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

interface IDashboardController {

    @GetMapping("", "/")
    fun rerouteToDashboard(): String

    @GetMapping("/dashboard")
    fun dashboard(
        model: Model
    ): String
}
