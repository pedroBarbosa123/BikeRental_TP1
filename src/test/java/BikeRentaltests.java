import Exceptions.UserAlreadyExists;
import Models.Deposit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BikeRentaltests {

    @BeforeEach
    public static void setUp() {
    }


    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 5, (int) Integer.MAX_VALUE, ((int) Integer.MAX_VALUE + 1)})
    public void registeredUserBVAtest_REG1toREG9(int value) throws UserAlreadyExists {

        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(value, "aaa", 1);
        boolean test = b.getUsers().isEmpty();
        assertTrue(test, "Um dos BVA's RegiterUser falhou e foi o"+value);
    }


    @Test
    public void registeredUserDuplicatetest_REG10() throws UserAlreadyExists {

        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(5, "aaa", 1);
        assertThrows(UserAlreadyExists.class, () -> b.registerUser(5, "aaa", 1),"User duplicado");
    }


    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void registeredUserrentalProgramtest_REG11toREG14(int value) throws UserAlreadyExists {

        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(5, "aaa", value);
        boolean test = b.getUsers().isEmpty();
        assertTrue(test,"Um dos BVA's rentalProgram falhou e foi o "+value);
    }

    @Test
    public void addCreditNonexistantUser_AdC1() {
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(100,10);
        assertEquals(0,b.getUsers().get(100).getCredit());
    }

    @Test
    public void addCreditExisantUser_AdC3(){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(100,10);
        assertEquals(10,b.getUsers().get(1).getCredit());
    }

    @Test
    public void addCreditNegative_AdC2(){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(100,-10);
        assertEquals(0,b.getUsers().get(1).getCredit());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,0,1,(int)Integer.MAX_VALUE,(int)Integer.MAX_VALUE+1})
    public void addCreditBVAtest_AdC4toAdC9(int value){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(value,10);
        assertEquals(0,b.getUsers().get(value).getCredit(),"Falhou um BVA e foi o "+value);
    }

    @Test
    public void addCreditAmountOverflowtest_AdC10(){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(1,(int)Integer.MAX_VALUE+1);
        assertEquals(0,b.getUsers().get(1).getCredit());
    }

    @Disabled
    @Test
    public void addCreditNullUser_AdC11(){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(null,10);
        assertEquals(0,b.getUsers().get(1).getCredit());
    }

    @Test
    @ParameterizedTest
    @ValueSource(ints = {-1,0,5,(int)Integer.MAX_VALUE-1,(int)Integer.MAX_VALUE,(int)Integer.MAX_VALUE+1})
    public void verifyCreditBVA_vC1tovC7(int value){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(0,"aaa",1);
            b.registerUser(5,"abc",1);
            b.registerUser((int)Integer.MAX_VALUE-1,"add",1);
            b.registerUser((int)Integer.MAX_VALUE,"aab",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        boolean test = b.verifyCredit(value);
        assertTrue(test,"Não foi possivel verificar o saldo do IDUSER: "+value);
    }

    @Test
    public void verifyCreditNegativeBalanceUser(){
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(0,"aaa",1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.getUsers().get(1).setCredit(-10f);
        boolean test = b.verifyCredit(0);
        assertFalse(test);
    }

    @Disabled
    @Test
    public void verifyCreditnullID(){
        BikeRentalSystem b = new BikeRentalSystem(1);
        boolean test = b.verifyCredit();
        assertFalse(test);
    }
    @Disabled
    @Test
    public void gerBicycleBVANegativetest(){
        BikeRentalSystem b = new BikeRentalSystem(1);
        Deposit d = new Deposit(1);

    }
}
