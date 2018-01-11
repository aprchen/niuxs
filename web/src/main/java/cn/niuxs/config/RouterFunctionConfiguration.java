package cn.niuxs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import cn.niuxs.domain.User;
import cn.niuxs.repository.UserRepository;
import reactor.core.publisher.Flux;

import java.util.Collection;

/**
 * 相当于java 版的 xml 文件
 * Reactive 的
 * Flex 是 0-n的对象集合 data
 * Mono 是 0-1的对象集合 item
 * 都是异步非阻塞的,都是 Publisher {@link org.reactivestreams.Publisher}
 *
 * 其他的集合基本都是同步处理(future 除外)
 */
@Configuration
public class RouterFunctionConfiguration {

    @Bean
    @Autowired
    public RouterFunction<ServerResponse> userFindAll(UserRepository userRepository){
        return RouterFunctions.route(RequestPredicates.GET("/users"),request -> {
            Collection<User> users = userRepository.getAll();
            Flux<User> userFlux = Flux.fromIterable(users);
            return ServerResponse.ok().body(userFlux,User.class);
        });
    }

}
