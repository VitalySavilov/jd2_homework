package my.web;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import my.CarService;
import my.model.Car;
import my.model.CarType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class AddCarController {
    private final CarService carService;

    @PostMapping("/add")
    @SneakyThrows
    public String addCar(@RequestParam("image") MultipartFile file, Car car) {
        carService.addCar(car, file.getBytes());
        return "redirect:/";
    }

    @GetMapping("/add-car")
    public ModelAndView showAddCarPage() {
        List<String> types = Arrays.stream(CarType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
        return new ModelAndView(
                "add_car",
                Map.of("types", types)
        );
    }
}
