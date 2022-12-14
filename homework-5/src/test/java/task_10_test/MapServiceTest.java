package task_10_test;

import it.academy.task_10.Employee;
import it.academy.task_10.EmployeeDepartmentDto;
import it.academy.task_10.MapService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:task_10.beans.xml"})
public class MapServiceTest {
    @Autowired
    MapService mapService;
    List<EmployeeDepartmentDto> listDto = new ArrayList<>();

    @Test
    public void testCreateEmployee() {
        listDto.add(new EmployeeDepartmentDto("Sergey", "Sergeev",
                "IT", "Minsk"));
        listDto.add(new EmployeeDepartmentDto("Andrey", "Andreev",
                "DevOps", "Brest"));

        List<Employee> list = mapService.createEmployee(listDto);

        assertEquals(2, list.size());
        assertEquals("Sergey", list.get(0).getName());
        assertEquals("Sergeev", list.get(0).getSurname());
        assertEquals("IT", list.get(0).getDepartment().getDepName());
        assertEquals("Minsk", list.get(0).getDepartment().getLocation());
        assertEquals("Andrey", list.get(1).getName());
        assertEquals("Andreev", list.get(1).getSurname());
        assertEquals("DevOps", list.get(1).getDepartment().getDepName());
        assertEquals("Brest", list.get(1).getDepartment().getLocation());
    }
}
