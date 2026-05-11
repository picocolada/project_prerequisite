package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Mazda", 5);
        Car car2 = new Car("Audi", 4);
        Car car3 = new Car("Tesla", 1);
        Car car4 = new Car("Toyota", 999);
        Car car5 = new Car("Hyundai", 123);


        User userWithCar1 = new User("John", "Doe", "test1@test.test");
        User userWithCar2 = new User("Jane", "Doe", "test2@test.test");
        User userWithCar3 = new User("Jenny", "Black", "test3@test.test");
        User userWithCar4 = new User("Elvis", "Thompson", "test4@test.test");
        User userWithCar5 = new User("Mark", "King", "test5@test.test");

        userWithCar1.setCar(car1);
        userWithCar2.setCar(car2);
        userWithCar3.setCar(car3);
        userWithCar4.setCar(car4);
        userWithCar5.setCar(car5);

        userService.add(userWithCar1);
        userService.add(userWithCar2);
        userService.add(userWithCar3);
        userService.add(userWithCar4);
        userService.add(userWithCar5);



        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = "+user.getId());
            System.out.println("First Name = "+user.getFirstName());
            System.out.println("Last Name = "+user.getLastName());
            System.out.println("Email = "+user.getEmail());
            System.out.println();
        }

        System.out.println(userService.findUserByCar("Tesla", 1));
        context.close();
    }
}
