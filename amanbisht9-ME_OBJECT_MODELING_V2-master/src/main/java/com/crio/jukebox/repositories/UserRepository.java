package com.crio.jukebox.repositories;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.crio.jukebox.entities.User;

public class UserRepository implements IUserRepository{

    private final Map<Integer,User> userMap;
    private Integer autoIncrement = 0;

    public UserRepository(){
        userMap = new HashMap<Integer,User>();
    }

    public UserRepository(Map<Integer,User> userMap) {
        this.userMap = userMap;
        this.autoIncrement = userMap.size();
    }

    @Override
    public User save(User entity) {
        if( entity.getUserId() == 0 ){
            autoIncrement++;
            User u = new User(autoIncrement,entity.getUserName());
            userMap.put(u.getUserId(),u);
            return u;
        }
        userMap.put(entity.getUserId(),entity);
        return entity;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void delete(User entity) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void deleteById(Integer id) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean existsById(Integer id) {
        // TODO Auto-generated method stub
        if (userMap.containsKey(id)) {
            return true;
        }

        return false;
    }

    @Override
    public List<User> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User findById(Integer id) {
        // TODO Auto-generated method stub
        if (userMap.containsKey(id)) {
            return userMap.get(id);
        }

        return null;
    }

}
