import org.easymock.EasyMockRunner;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/11 13:26
 **/
@DisplayName("EasyMock Demo")
@RunWith(EasyMockRunner.class)
public class ExampleTest {
    @TestSubject
    private ClassUnderTest classUnderTest = new ClassUnderTest();
    @Mock
    private Collaborator mock;

    @BeforeEach
    public void setUp() {
        classUnderTest.setListener(mock);
    }

    @Test
    public void testRemoveNonExistingDocument() {
        // This call should not lead to any notification
        // of the Mock Object:
//        EasyMock.replay(mock); // 3
        String actual = classUnderTest.removeDocument("Does not exist");
        Assertions.assertEquals("", "Does not exist", actual);
    }
}

class ClassUnderTest {

    private Collaborator listener;

    public void setListener(Collaborator listener) {
        this.listener = listener;
    }

    public String removeDocument(String title) {
        return listener.documentAdded(title);
    }
}
interface Collaborator {
    String documentAdded(String title);
}