package Lab02_addStudent;

import domain.Nota;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentFileRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }

    private StudentXMLRepository repository;
    private StudentValidator validator = new StudentValidator();
    private Service service;

    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    TemaXMLRepository temaRepository = new TemaXMLRepository(temaValidator, "teme.xml");
    NotaXMLRepository notaRepository = new NotaXMLRepository(notaValidator, "note.xml");

    private static final int SUCCESS = 1;
    private static final int FAILURE = 0;

    @Before
    public void setup() {
        repository = new StudentXMLRepository(validator, "students_test_repo.xml");
        repository.delete("testId");
    }

    @Test
    public void tc1_studentIdNotNull() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc2_studentIdNull() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent(null, "testName", 123);
        assertEquals(result, FAILURE);
    }

    @Test
    public void tc3_studentIdNotEmpty() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc4_studentIdEmpty() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("", "testName", 123);
        assertEquals(result, FAILURE);
    }

    @Test
    public void tc5_studentNameNotNull() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc6_studentNameNull() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", null, 123);
        assertEquals(result, FAILURE);
    }

    @Test
    public void tc7_studentNameNotEmpty() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc8_studentNameEmpty() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "", 123);
        assertEquals(result, FAILURE);
    }

    @Test
    public void tc9_studentNameString() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc11_studentGroupNotNull() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc13_studentGroupNaturalNumber() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc14_studentGroupNotNaturalNumber() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", -12);
        assertEquals(result, FAILURE);
    }

    @Test
    public void tc15_studentGroupBetween110And938() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc16_studentGroupNotBetween110And938() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 15);
        assertEquals(result, FAILURE);
    }

    @Test
    public void tc17_studentWithIdDoesNotExits() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 123);
        assertEquals(result, SUCCESS);

    }

    @Test
    public void tc178_studentWithIdDoesExits() {
        service = new Service(repository, temaRepository, notaRepository);
        service.saveStudent("testId", "testName", 334);

        int result = service.saveStudent("testId", "testName2", 666);
        assertEquals(result, FAILURE);
    }

    @Test
    public void bva1() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 110);
        assertEquals(result, FAILURE);
    }

    @Test
    public void bva2() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 109);
        assertEquals(result, FAILURE);
    }

    @Test
    public void bva3() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 111);
        assertEquals(result, SUCCESS);
    }

    @Test
    public void bva4() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 938);
        assertEquals(result, FAILURE);
    }

    @Test
    public void bva5() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 939);
        assertEquals(result, FAILURE);
    }

    @Test
    public void bva6() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", 937);
        assertEquals(result, SUCCESS);
    }

    @Test
    public void bva7() {
        service = new Service(repository, temaRepository, notaRepository);

        int result = service.saveStudent("testId", "testName", -1);
        assertEquals(result, FAILURE);
    }


}