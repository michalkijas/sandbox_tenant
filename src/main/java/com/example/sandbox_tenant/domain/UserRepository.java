package com.example.sandbox_tenant.domain;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByFirstNameAndLastNameAndComment(String firstName, String lastName, String comment);

    User findByFirstName(String firstName);

    @Query("SELECT u FROM User u WHERE u.firstName = 'xyz'")
    User findWithFirstNameXyz();

    @Query(value = "SELECT u.* FROM users u WHERE u.first_name = 'xyz'", nativeQuery = true)
    User findWithFirstNameXyzUsingNativeQuery(String firstName);

}
