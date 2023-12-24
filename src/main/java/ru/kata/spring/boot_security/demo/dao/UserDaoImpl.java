package ru.kata.spring.boot_security.demo.dao;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void createUser(User user) {
        entityManager.persist(user);
    }
    @Override
    public void updateUser(User user) {
        entityManager.merge(user);
    }
    @Override
    public List<User> getAllUsers() {
        List<User> userList = entityManager.createQuery("from User", User.class).getResultList();
        return userList;
    }
    @Override
    public User readUser(Long id) {
        return entityManager.find(User.class, id);
    }
    @Override
    public User deleteUser(Long id) {
        User user = readUser(id);
        entityManager.remove(user);
        return user;
    }
    @Override
    public User findByUsername(String username) {
        String query = "select distinct u from User AS u left join fetch u.roles where u.username = :username";
        try {
            return entityManager.createQuery(query, User.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }
    }
}