package cn.itcast.t4.proxy;

import cn.itcast.t4.User;

public class UserServiceProxy extends UserService {



    @Override
    public void save(User user) {
        super.save(user);
    }

    @Override
    public void update(User user) {
        super.update(user);
    }
}
