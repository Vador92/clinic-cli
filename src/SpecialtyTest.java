import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SpecialtyTest {

    @Test
    void specialityCreationTest(){
        Specialty testSpecialty = Specialty.PEDIATRICIAN;

        assertEquals(testSpecialty.toString(), "Pediatrician");

        assertEquals(testSpecialty, Specialty.PEDIATRICIAN);
    }

    @Test
    void specialityCreationTest2(){
        Specialty testSpecialty = Specialty.FAMILY;

        assertEquals(testSpecialty.toString(), "Family");

        assertEquals(testSpecialty, Specialty.FAMILY);
    }

    @Test
    void specialityCreationTest3(){
        Specialty testSpecialty = Specialty.ALLERGIST;

        assertEquals(testSpecialty.toString(), "Allergist");

        assertEquals(testSpecialty, Specialty.ALLERGIST);
    }

}