package com.greatestsasha.training.service;

import com.greatestsasha.training.model.Student;
import com.greatestsasha.training.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Service
public class ServiceHandler {

    @Autowired
    private StudentRepository studentRepo;

    /*
     * This method adds a new student to the database.
     */
    @Transactional
    public Mono<ServerResponse> addStudent(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(data -> ServerResponse.ok()
                        .body(studentRepo.save(data), Student.class));
    }

    /*
     * This method deletes a student from the database by ID.
     */
    @Transactional
    public Mono<ServerResponse> deleteStudentById(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(data -> ServerResponse.ok()
                        .body(studentRepo.deleteById(data.getId()), Student.class))
                .switchIfEmpty(ServerResponse.ok().bodyValue("No Student Data Found"));
    }

    /*
     * This method updates a student's information in the database by ID.
     */
    @Transactional
    public Mono<ServerResponse> updateStudentById(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(data -> studentRepo.findById(data.getId()).flatMap(change -> {
                    change.setId(data.getId());
                    change.setName(data.getName());
                    change.setAge(data.getAge());
                    return ServerResponse.ok().body(studentRepo.save(change), Student.class);
                }).switchIfEmpty(ServerResponse.ok().bodyValue("No Student Data Found")));
    }

    /*
     * This method retrieves all students' data from the database.
     */
    @Transactional(readOnly = true)
    public Mono<ServerResponse> getAllStudents(ServerRequest request) {
        return request.bodyToMono(Student.class)
                .flatMap(s -> ServerResponse.ok().body(studentRepo.findAll(), Student.class))
                .switchIfEmpty(ServerResponse.ok().bodyValue("No Student Data Found"));
    }
}