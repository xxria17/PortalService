package kr.ac.jejunu;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.core.Is.is;

public class UserDaoTests {
    static UserDao userDao;

    @BeforeAll
    public static void setup() {
        //면접 단골 질문 : JVM에 해당되는 메모리 영역 구조 (static, heap, stack)
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext("kr.ac.jejunu");

        userDao = applicationContext.getBean("userDao", UserDao.class);
    }

    @Test
    public void get() throws SQLException {
        Integer id = 1;
        String name = "허윤호";
        String password = "1111";

        User user = userDao.findById(id);
        assertThat(user.getId(), is(id));
        assertThat(user.getName(), is(name));
        assertThat(user.getPassword(), is(password));

    }

    @Test
    public void insert() throws SQLException {
        String name = "허윤호";
        String password = "1111";

        User user = User.builder().name(name).password(password).build();

        userDao.insert(user);
        User insertedUser = userDao.findById(user.getId());
        assertThat(user.getId(), greaterThan(0));
        assertThat(insertedUser.getId(), is(user.getId()));
        assertThat(insertedUser.getName(), is(user.getName()));
        assertThat(insertedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void update() throws SQLException {
        String name = "허윤호";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        user.setName("hulk");
        user.setPassword("1234");

        userDao.update(user);

        User updatedUser = userDao.findById(user.getId());

        assertThat(updatedUser.getId(), is(user.getId()));
        assertThat(updatedUser.getName(), is(user.getName()));
        assertThat(updatedUser.getPassword(), is(user.getPassword()));
    }

    @Test
    public void delete() throws SQLException {
        String name = "허윤호";
        String password = "1111";

        User user = new User();
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);

        userDao.delete(user.getId());

        User deletedUser = userDao.findById(user.getId());
        assertThat(deletedUser, nullValue());
    }

}
