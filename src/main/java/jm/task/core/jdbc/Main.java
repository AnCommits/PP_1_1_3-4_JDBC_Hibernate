package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.util.List;

public class Main {

    private static final UserService USER_SERVICE = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

//        Создание таблицы User(ов)
        USER_SERVICE.createUsersTable();

//        Добавление 4 User(ов) в таблицу с данными на свой выбор.
//        После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных).
        USER_SERVICE.saveUser("Petr", "Romanov", (byte) 50);
        USER_SERVICE.saveUser("Bill", "Gates", (byte) 55);
        USER_SERVICE.saveUser("Misha", "Lomonosov", (byte) 15);
        USER_SERVICE.saveUser("Casual", "Passerby", (byte) 100);

//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        List<User> allUsers = USER_SERVICE.getAllUsers();
        allUsers.forEach(System.out::println);

//        Очистка таблицы User(ов)
        USER_SERVICE.cleanUsersTable();

//        Удаление таблицы
        USER_SERVICE.dropUsersTable();

        Util.closeConnection();
    }
}
