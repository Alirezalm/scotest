package com.scorest.api;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static List<User> users = new ArrayList<>();
    private static int userCount = 3;

    static {
        users.add(new User(1, "Alireza", LocalDate.now().minusYears(30)));
        users.add(new User(2, "Elham", LocalDate.now().minusYears(24)));
        users.add(new User(3, "Thiago", LocalDate.now().minusYears(18)));
    }

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findUser(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst();
    }

    public User save(User user) {
        user.setId(++userCount);
        users.add(user);
        return user;
    }
    public void removeUser(int id){
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        users.removeIf(predicate);
    }
}
