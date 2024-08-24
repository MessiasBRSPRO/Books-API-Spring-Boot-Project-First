package com.projeto.APIBooks.Services;

import com.projeto.APIBooks.DataTransferObjects.DTOUser;
import com.projeto.APIBooks.Entities.User;
import com.projeto.APIBooks.Exceptions.ExistentUserException;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")

class UserServiceTest {

    @Autowired
    EntityManager entityManager;

    @Mock // Mock é um objeto que simula o comportamento de um objeto real da aplicação.
    UserService userService;

    @BeforeEach
    void setUpAllMocks(){
        //Toda vez que um teste unitário for feito, sera feito com os objetos mocks.
        //Isso funcionaria como um loop, para cada teste unitário os mocks serão reinicializados e estarão prontos para uso
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("if not exists the user")
    void registerUserCase1() {
        DTOUser user = new DTOUser("Messiask1", "Meddisme@gmail.com", "Afton3444$");
        User created = createUser(user);

        User inserted = userService.registerUser(created);

        assertThat(inserted);
    }

    @Test
    @DisplayName("A user with this username already Exists")
    void registerUserCase2() throws Exception {
        DTOUser user = new DTOUser("Messiask1", "Meddisme1@gmail.com", "Afton3444$");
        User created = createUser(user);

        Exception thrown  =Assertions.assertThrows(ExistentUserException.class, ()-> userService.registerUser(created));

        Assertions.assertEquals("this Username already used for other User", thrown.getMessage());

    }

    private User createUser(DTOUser user){
        User user1 = new User(user);
        entityManager.persist(user1);
        return user1;
    }
}