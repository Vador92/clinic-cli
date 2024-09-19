import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpecialtyTest {

    @Test
    void specialityCreationTest(){
        Specialty testSpecialty = Specialty.PEDIATRICIAN;

        assertEquals(testSpecialty.toString(), "Pediatrician");

        assertEquals(testSpecialty, Specialty.PEDIATRICIAN);
    }

}