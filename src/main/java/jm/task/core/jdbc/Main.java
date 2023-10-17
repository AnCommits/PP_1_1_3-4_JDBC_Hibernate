package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {

    private static final UserService userService = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

//        Создание таблицы User(ов)
        userService.createUsersTable();

//        Добавление 4 User(ов) в таблицу с данными на свой выбор.
//        После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных).
        userService.saveUser("Petr", "Romanov", (byte) 50);
        userService.saveUser("Bill", "Gates", (byte) 55);
        userService.saveUser("Misha", "Lomonosov", (byte) 15);
        userService.saveUser("Casual", "Passerby", (byte) 100);

//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        List<User> allUsers = userService.getAllUsers();
        allUsers.forEach(System.out::println);

//        Очистка таблицы User(ов)
        userService.cleanUsersTable();

//        Удаление таблицы
        userService.dropUsersTable();

        Util.closeSessionFactory();
//        Util.closeConnection();
    }
}
