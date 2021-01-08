package com.example.sandbox_tenant.domain

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification
import spock.lang.Subject

/**
 * Generating SQL queries into application logs
 */
@SpringBootTest
class TestServiceSpec extends Specification {

    @Autowired
    @Subject UserRepository sut

    // WITH tenantId from context
    @Transactional
    def "should save with tenantId"() {
        given:
            User newUser = new User()
            newUser.setFirstName("valid_fist_name")
        when:
            sut.save(newUser)
        then:
            User user = sut.findByFirstName("valid_fist_name")
            user.id != null
            user.tenantId != null
    }

    @Transactional
    def "should update with tenantId"() {
        given:
            User newUser = new User()
            newUser.setFirstName("valid_fist_name")
            User saverUser = sut.save(newUser)
            saverUser.setFirstName("updated_valid_fist_name")
        when:
            sut.save(saverUser)
        then:
            User user = sut.findByFirstName("updated_valid_fist_name")
            user.id != null
            user.tenantId != null
    }

    @Transactional
    def "should get with tenantId"() {
        expect:
            sut.findByFirstName("valid_fist_name")
    }

    @Transactional
    def "should deleteAll with tenantId"() {
        expect:
            sut.deleteAll()
    }

    @Transactional
    def "should add tenantId into Spring data field query"() {
        expect:
            sut.findByFirstNameAndLastNameAndComment("VALID_FIRST_NAME", "VALID_LAST_NAME", "VALID_COMMENT")
    }

    @Transactional
    def "should add tenantId into method Repository.findAll"() {
        expect:
            Optional<User> result = sut.findAll()
    }

    @Transactional
    def "should add tenantId into Spring data query"() {
        expect:
            sut.findWithFirstNameXyz()
    }

    @Transactional
    def "should get with tenantId 2"() {
        given:
            User newUser = new User()
            newUser.setFirstName("valid_fist_name")
            Role role = new Role()
            role.name = "VALID_ROLE_NAME"
            newUser.roles.add(new Role())
            sut.save(newUser)
            sut.findAll()
        expect:
            User u = sut.findByFirstName("valid_fist_name")
            u.getRoles().size() == 1
    }


    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    // WITHOUT tenantId from context
    // !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    @Transactional
    def "should run getOne without tenantId"() {
        given:
            User userReference = sut.getOne(1L)
        expect:
            userReference.getFirstName() == null
    }

    @Transactional
    def "should run findById without tenantId"() {
        expect:
            sut.findById(1L)
    }

    @Transactional
    def "should delete with tenantId"() {
        given:
            User newUser = new User()
            newUser.setFirstName("valid_fist_name")
            User saverUser = sut.save(newUser)
        expect:
            sut.delete(saverUser)
        and: "helper: to print last query in log"
            sut.findAll()
    }

    @Transactional
    def "should run deleteById without tenantId"() {
        expect:
            sut.deleteById(1L)
    }

    @Transactional
    def "should run nativeQuery without tenantId"() {
        expect:
            sut.findWithFirstNameXyzUsingNativeQuery()
    }

}
