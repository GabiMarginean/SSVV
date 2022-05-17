package Lab03_Integration;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {


    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    Validator<Student> studentValidator = new StudentValidator();

    StudentXMLRepository studentRepository;
    TemaXMLRepository temaRepository;
    NotaXMLRepository notaRepository;

    Service service;

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;
    private static final int FAILURE_NONEXISTENT = -1;

    private static final String STUDENTS_TEST_REPO = "students_test_repo.xml";
    private static final String TEMA_TEST_REPO = "tema_test_repo.xml";
    private static final String NOTA_TEST_REPO = "nota_test_repo.xml";

    private static final String ID_STUDENT = "idStudent";
    private static final String TEST_STUDENT = "Test Student";
    private static final int GRUPA = 934;
    private static final String ID_ASG = "idAsg";
    private static final String TEST_ASSIGNEMNT = "test assignemnt";
    private static final double VAL_NOTA = 5d;
    private static final int DEADLINE = 14;
    private static final int STARTLINE = 5;
    private static final int PREDATA = 13;
    private static final String FEEDBACK = "feedback";

    @Before
    public void setup() {
        studentRepository = new StudentXMLRepository(studentValidator, STUDENTS_TEST_REPO);
        temaRepository = new TemaXMLRepository(temaValidator, TEMA_TEST_REPO);
        notaRepository = new NotaXMLRepository(notaValidator, NOTA_TEST_REPO);

        service = new Service(studentRepository, temaRepository, notaRepository);
    }

    @After
    public void after() {
        clearXML(STUDENTS_TEST_REPO);
        clearXML(TEMA_TEST_REPO);
        clearXML(NOTA_TEST_REPO);
    }

    private void clearXML(String filename) {
        try {
            PrintWriter writer = new PrintWriter(filename);
            writeNewXML(writer);
            writer.close();
        } catch (Exception ignored) {
        }
    }

    private void writeNewXML(PrintWriter writer) {
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>");
        writer.println("</Entitati>");
    }

    @Test
    public void testAddStudent_HappyFlow() {
        int result = service.saveStudent(ID_STUDENT, TEST_STUDENT, GRUPA);
        assertEquals(SUCCESS, result);
    }

    @Test
    public void testAddStudent_InvalidId() {
        int result = service.saveStudent("", TEST_STUDENT, GRUPA);
        assertEquals(FAILURE, result);
    }

    @Test
    public void testAddStudent_InvalidGroup() {
        int result = service.saveStudent(ID_STUDENT, TEST_STUDENT, 999);
        assertEquals(FAILURE, result);
    }

    @Test
    public void integration_testAddAssignment_HappyFlow() {
        service.saveStudent(ID_STUDENT, TEST_STUDENT, GRUPA);
        int result = service.saveTema(ID_ASG, TEST_ASSIGNEMNT, DEADLINE, STARTLINE);
        assertEquals(SUCCESS, result);
    }

    @Test
    public void integration_testAddAssignment_InvalidInput() {
        service.saveStudent(ID_STUDENT, TEST_STUDENT, GRUPA);
        int result = service.saveTema(ID_ASG, TEST_ASSIGNEMNT, DEADLINE, 15);
        assertEquals(FAILURE, result);
    }

    @Test
    public void TestAddStudentAddHomework_InvalidStudentValidHomework_ShouldPass() {
        service.saveStudent("", TEST_STUDENT, GRUPA);
        int result = service.saveTema(ID_ASG, TEST_ASSIGNEMNT, DEADLINE, STARTLINE);
        assertEquals(SUCCESS, result);
    }

    @Test
    public void integration_testAddGrade_HappyFlow() {
        service.saveStudent(ID_STUDENT, TEST_STUDENT, GRUPA);
        service.saveTema(ID_ASG, TEST_ASSIGNEMNT, DEADLINE, STARTLINE);

        int result = service.saveNota(ID_STUDENT, ID_ASG, VAL_NOTA, PREDATA, FEEDBACK);
        assertEquals(SUCCESS, result);
    }

    @Test
    public void integration_testAddGrade_InvalidStudent() {
        service.saveStudent("", TEST_STUDENT, GRUPA);
        service.saveTema(ID_ASG, TEST_ASSIGNEMNT, DEADLINE, STARTLINE);

        int result = service.saveNota(ID_STUDENT, ID_ASG, VAL_NOTA, PREDATA, FEEDBACK);
        assertEquals(FAILURE_NONEXISTENT, result);
    }

    @Test
    public void integration_testAddGrade_InvalidAssignment() {
        service.saveStudent(ID_STUDENT, TEST_STUDENT, GRUPA);
        service.saveTema(ID_ASG, TEST_ASSIGNEMNT, STARTLINE, DEADLINE);

        int result = service.saveNota(ID_STUDENT, ID_ASG, VAL_NOTA, PREDATA, FEEDBACK);
        assertEquals(FAILURE_NONEXISTENT, result);
    }
}
