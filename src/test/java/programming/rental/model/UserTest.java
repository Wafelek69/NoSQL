package programming.rental.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;

class UserTest {

    private static ObjectMapper mapper = new ObjectMapper();

    @Test
    void shouldSerializeUser() throws JsonProcessingException {
        User user = new User();
        user.setFirstName("Anna");
        user.setSecondName("Książek");
        user.setAge(118);
        user.setHobbies(new HashSet<>(Arrays.asList("photography", "travels", "programming", "...")));

        String json = mapper.writeValueAsString(user);

        System.out.println(json);
    }
}