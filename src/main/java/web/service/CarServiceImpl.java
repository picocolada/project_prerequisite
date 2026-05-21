package web.service;

import org.springframework.stereotype.Service;
import web.model.Car;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarServiceImpl implements CarService{

    private List<Car> cars;

    public CarServiceImpl() {
        cars = new ArrayList<>();
        cars.add(new Car("Toyota", "Camry", 2020));
        cars.add(new Car("Honda", "Civic", 2021));
        cars.add(new Car("BMW", "X5", 2019));
        cars.add(new Car("Mercedes", "E-Class", 2022));
        cars.add(new Car("Audi", "A4", 2021));
    }

    public List<Car> getCars(Integer count){
        if (count == null || count >= cars.size()){
            return cars;
        } else {
            return cars.subList(0, count);
        }
    }
}
