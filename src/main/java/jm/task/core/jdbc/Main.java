package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    private static final UserService USER_SERVICE = new UserServiceImpl();

    public static void main(String[] args) {
        // реализуйте алгоритм здесь

//        Создание таблицы User(ов)
        USER_SERVICE.createUsersTable();

//        Добавление 4 User(ов) в таблицу с данными на свой выбор.
//        После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных).

//        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
//        Очистка таблицы User(ов)
//        Удаление таблицы
    }
}
