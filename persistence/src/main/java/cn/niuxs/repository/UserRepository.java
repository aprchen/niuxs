package cn.niuxs.repository;

import org.springframework.stereotype.Repository;
import cn.niuxs.domain.User;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link User }{@link Repository}
 */

@Repository
public class UserRepository {
    /**
     * 采用内存型的存贮方式->Map
     */
    private final ConcurrentMap<Integer,User> repository = new ConcurrentHashMap<>();
    /**
     * 自增id
     */
    private final static AtomicInteger idGenerator = new AtomicInteger();

    /**
     *
     * @param user {@link User}
     *
     * @return 保存成功返回 <code>true</code>,失败返回<code>false</code>
     */
    public boolean save(User user){
        //id 从1开始
        Integer id = idGenerator.incrementAndGet();
        user.setId(id);
        return  repository.put(id,user) ==null ;
    }

    /**
     *
     * @param id int
     * @return 如果成功的,返回 <code>User</code>
     */
    public User get(int id){
        return repository.get(id);
    }

    /**
     *
     * @return Collection
     */
    public Collection<User> getAll(){
        return  repository.values();
    }

}
