package my.web;

import lombok.RequiredArgsConstructor;
import my.CarService;
import my.ImageService;
import my.model.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class CarListController {

    private final CarService carService;
    private final ImageService imageService;

    @GetMapping("/car-list")
    public ModelAndView showEmployeeList() {
        return new ModelAndView(
                "car_list",
                Map.of("cars", carService.getAll())
        );
    }

    @ResponseBody
    @GetMapping("/image/{imageId}/photo")
    public byte[] getImage(@PathVariable("imageId") long imageId) {
        return imageService.getImageById(imageId).getImage();
    }
}
