package my.web;

import lombok.RequiredArgsConstructor;
import my.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final CarService carService;

    @GetMapping(value = "/")
    public ModelAndView homePage() {
        return new ModelAndView(
                "home",
                "count",
                carService.getCarCount());
    }
}
