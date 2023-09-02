package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("ваня", "петров", (byte)20);
        userService.saveUser("петя", "васильев", (byte)22);
        userService.saveUser("саша", "соловьев", (byte)21);
        userService.saveUser("маша", "иванова", (byte)24);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();



    }
}
