package com.greatestsasha.training.config;

import com.greatestsasha.training.service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class MongoConfigRouter {

    @Autowired
    private ServiceHandler serviceHandler;

    /**
     * This method configures the RouterFunction to define the routes
     * for handling various HTTP requests related to student services.
     */
    @Bean
    RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route(RequestPredicates.POST("api/student/add"), serviceHandler::addStudent)
                .andRoute(RequestPredicates.POST("api/student/delete"), serviceHandler::deleteStudentById)
                .andRoute(RequestPredicates.POST("api/student/update"), serviceHandler::updateStudentById)
                .andRoute(RequestPredicates.POST("api/student/getall"), serviceHandler::getAllStudents);
    }
}