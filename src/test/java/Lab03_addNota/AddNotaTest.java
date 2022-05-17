package Lab03_addNota;

import domain.Nota;
import domain.Pair;
import domain.Student;
import domain.Tema;
import org.junit.Before;
import org.junit.Test;
import repository.NotaXMLRepository;
import repository.StudentXMLRepository;
import repository.TemaXMLRepository;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.Validator;

public class AddNotaTest {

    StudentXMLRepository studentRepository;
    TemaXMLRepository temaRepository;
    NotaXMLRepository notaRepository;

    Validator<Tema> temaValidator = new TemaValidator();
    Validator<Nota> notaValidator = new NotaValidator();
    Validator<Student> studentValidator = new StudentValidator();

    @Before
    public void setup() {
        studentRepository = new StudentXMLRepository(studentValidator, "students_test_repo.xml");
        temaRepository = new TemaXMLRepository(temaValidator, "tema_test_repo.xml");
        temaRepository.save(new Tema("idTema", "desc", 10, 5));
        notaRepository = new NotaXMLRepository(notaValidator, "nota_test_repo.xml");
        notaRepository.delete(new Pair<>("idNota", "idTema"));
    }

    @Test
    public void test1(){
        
    }
}
