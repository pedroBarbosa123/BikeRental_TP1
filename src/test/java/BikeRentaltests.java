import Exceptions.UserAlreadyExists;
import Exceptions.UserDoesNotExists;
import Models.Bike;
import Models.Deposit;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class BikeRentaltests {

    /**
     * Testa o metodo com um IDUSER negativo. Não deve adicionar o USER. BVA min invalido
     * @throws UserAlreadyExists
     * @Expect No action
     */
    @Test
    public void registeredUserBVANegativetest_REG1() throws UserAlreadyExists {
        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(-1, "aaa", 1);
        boolean test = b.getUsers().isEmpty();
        assertTrue(test, "A lista de Users devia estar vazia mas não está");
    }

    /**
     * Testa o método a adicionar um utilizador com IDUSER: 0. BVA min
     * @throws UserAlreadyExists
     * @Expect O utilizador deverá ser adicionado a BD
     */
    @Test
    public void registeredUserBVAtest_REG2() throws UserAlreadyExists {
        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(0, "aaa", 1);
        boolean test = b.getUsers().isEmpty();
        assertFalse(test, "A lista de Users não devia estar vazia mas está");
    }

    /**
     * Testa o metodo com um utilizador válido. ECP valid
     * @throws UserAlreadyExists
     * Expect New User
     */
    @Test
    public void registeredUserValidtest_REG3() throws UserAlreadyExists {
        BikeRentalSystem b1 = new BikeRentalSystem(1);
        b1.registerUser(5, "aaa", 1);
        boolean test = b1.getUsers().isEmpty();
        assertFalse(test, "A lista de Users devia estar vazia mas não está");
    }

    /**
     * Testa o metodo com um IDUSER válido máx range, BVA max valid
     * @throws UserAlreadyExists
     * @Expect New User
     */
    @Test
    public void registeredUserBVANegativetest_REG4() throws UserAlreadyExists {
        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(Integer.MAX_VALUE, "aaa", 1);
        boolean test = b.getUsers().isEmpty();
        assertFalse(test, "A lista de Users devia estar vazia mas não está");
    }

    /**
     * Verifica o comportamento quando se adiciona um utilizador em duplicado no sistema. ECP
     * @throws UserAlreadyExists
     * @Expect UserAlreadyExists Exception
     */
    @Test
    public void registeredUserDuplicatetest_REG10() throws UserAlreadyExists {

        BikeRentalSystem b = new BikeRentalSystem(1);
        b.registerUser(5, "aaa", 1);
        assertThrows(UserAlreadyExists.class, () -> b.registerUser(5, "aaa", 1), "User duplicado");
    }

    /*
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3})
    public void registeredUserrentalProgramtest_REG6toREG9(int value) throws UserAlreadyExists {

        BikeRentalSystem b2 = new BikeRentalSystem(1);
        b2.registerUser(5, "aaa", value);
        boolean test = b2.getUsers().isEmpty();
        assertTrue(test, "Um dos BVA's rentalProgram falhou e foi o " + value);
    }
    */

    /**
     * Testa o input rentalProgram. BVA min invalid
     * @throws UserAlreadyExists
     * @Expect No add User
     */
    @Test
    public void registeredUserrentalProgramtest_REG6toREG9() throws UserAlreadyExists {

        BikeRentalSystem b2 = new BikeRentalSystem(1);
        b2.registerUser(5, "aaa", 0);
        boolean test = b2.getUsers().isEmpty();
        assertFalse(test, "Um dos BVA's rentalProgram falhou e foi o 0");
    }

    /**
     * Testa o input rentalProgram. BVA min valid.ECP
     * @throws UserAlreadyExists
     * @Expect Add User
     */
    @Test
    public void registeredUserrentalProgramtest_REG6toREG9_2() throws UserAlreadyExists {

        BikeRentalSystem b2 = new BikeRentalSystem(1);
        b2.registerUser(5, "aaa", 1);
        boolean test = b2.getUsers().isEmpty();
        assertTrue(test, "Um dos BVA's rentalProgram falhou e foi o 1");
    }

    /**
     * Testa o input rentalProgram. BVA max valid.
     * @throws UserAlreadyExists
     * @Expect Add User
     */
    @Test
    public void registeredUserrentalProgramtest_REG6toREG9_3() throws UserAlreadyExists {

        BikeRentalSystem b2 = new BikeRentalSystem(1);
        b2.registerUser(5, "aaa", 2);
        boolean test = b2.getUsers().isEmpty();
        assertTrue(test, "Um dos BVA's rentalProgram falhou e foi o 2");
    }


    /**
     * Testa o input rentalProgram. BVA max invalid
     * @throws UserAlreadyExists
     * @Expect No add User
     */
    @Test
    public void registeredUserrentalProgramtest_REG6toREG9_4() throws UserAlreadyExists {

        BikeRentalSystem b2 = new BikeRentalSystem(1);
        b2.registerUser(5, "aaa", 3);
        boolean test = b2.getUsers().isEmpty();
        assertFalse(test, "Um dos BVA's rentalProgram falhou e foi o 3");


    }

    /**
     * Testa a introdução de um nome vazio. ECP
     * @throws UserAlreadyExists
     * @Expect No add user
     */
    @Test
    public void registeredUserEmptyName_REG10() throws UserAlreadyExists {

        BikeRentalSystem b2 = new BikeRentalSystem(1);
        b2.registerUser(5, "", 1);
        boolean test = b2.getUsers().isEmpty();
        assertTrue(test, "O nome devia tar preenchido");
    }

    /**
     * Testa o metodo addCredit. Utilização de um IDUSER nao existente na BD. ECP
     * @Expect No action
     */
    @Test
    public void addCreditNonexistantUser_AdC1() {
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(1, "aaa", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.addCredit(100, 10);
        assertEquals(0, b.getUsers().get(0).getCredit(), "Não devia ter adicionado fundos");
    }

    /**
     * Testa o metodo addCredit com um amount negativo. ECP
     * @Expect No action
     */
    @Test
    public void addCreditNegativeCredit_AdC2() {
        BikeRentalSystem b3 = new BikeRentalSystem(1);

        try {
            b3.registerUser(2, "aaa", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b3.addCredit(2, -10);
        assertEquals(0, b3.getUsers().get(0).getCredit(), "Não devia ter removido fundos");
    }

    /**
     * Testa o metodo addCredit com um montante de 10.Crédto = 0  ECP
     * @Expect Credit = 10
     */
    @Test
    public void addCreditValid_AdC3() {
        BikeRentalSystem b4 = new BikeRentalSystem(1);

        try {
            b4.registerUser(2, "aaa", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b4.addCredit(2, 10);
        assertEquals(10, b4.getUsers().get(0).getCredit(), "Devia ter 10€");
    }

    /**
     * Testa o metodo addCredit com um IDUSER 0. Credit = 0. BVA
     * @Ecpect Add credit. credit = 33.
     */
    @Test
    public void addCreditNonexistantUser_AdC4() {
        BikeRentalSystem b4 = new BikeRentalSystem(1);

        try {
            b4.registerUser(0, "adc", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b4.addCredit(0, 33);
        assertEquals(33, b4.getUsers().get(0).getCredit(), "Devia ter 33€");
    }

    /**
     * Testa o metodo addCredit com um amount de 0. Credit = 100. ECP
     * @Expect Credit = 100. No action
     */
    @Test
    public void addCreditNonexistantUser_AdC5() {
        BikeRentalSystem b4 = new BikeRentalSystem(1);

        try {
            b4.registerUser(0, "adc", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b4.getUsers().get(0).setCredit(100);
        b4.addCredit(0, 0);
        assertEquals(100, b4.getUsers().get(0).getCredit(), "Devia ter 100€");
    }

    /**
     * Testa o metodo verifyCredit com um user não existente. ECP
     * @Expect False
     */
    @Test
    public void verifyCreditBVA_vC31() {
        BikeRentalSystem b5 = new BikeRentalSystem(1);

        boolean test = b5.verifyCredit(10);
        assertFalse(test, "Não foi possivel verificar o saldo do IDUSER: ");
    }

    /**
     * Testa o metodo verifyCredit com um utilizador com credito negativo. ECP
     * @Expect False
     */
    @Test
    public void verifyCreditNegativeBalanceUser_vC3() {
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(5, "aaa", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.getUsers().get(0).setCredit(-10f);
        boolean test = b.verifyCredit(5);
        assertFalse(test, "O User deveria ter saldo negativo e por isso dar falso");
    }

    /**
     * Testa o metodo verifyCredit com um utilizador com credito positivo. Credito = 10. ECP
     * @Expect True
     */
    @Test
    public void verifyCreditPositiveBalanceUser_vC2() {
        BikeRentalSystem b = new BikeRentalSystem(1);

        try {
            b.registerUser(3, "aaa", 1);
        } catch (UserAlreadyExists userAlreadyExists) {
            userAlreadyExists.printStackTrace();
        }
        b.getUsers().get(0).setCredit(10f);
        boolean test = b.verifyCredit(3);
        assertTrue(test, "O User deveria ter saldo posivito e por isso dar true");
    }

    /**
     * Testa o metodo getBicycle com um User nao existente na BD. ECP
     * @Expect UserDoesNotExist Exception
     */
    @Test
    public void getBicycleInvalidIDUSERtest_gB1(){
        BikeRentalSystem b6 = new BikeRentalSystem(1);
        assertThrows(UserDoesNotExists.class, () -> b6.getBicycle(1, 100, 0), "User deveria ser invalido");
    }

    /**
     * Testa o metodo getBicycle de um IDDeposit não existente. ECP
     * @throws UserDoesNotExists
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void getBicycleInvalidIDDeposittest_gb2() throws UserDoesNotExists, UserAlreadyExists {
        BikeRentalSystem b6 = new BikeRentalSystem(1);
        b6.registerUser(3, "asd", 1);
        assertEquals(-1, b6.getBicycle(1, 3, 0), "Deposito deveria dar inválido");
    }

    /**
     * Testa o metodo getBicycle com o credito utilizador negativo. ECP
     * @throws UserDoesNotExists
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void getBicycleInvalidCredittest_gB3() throws UserDoesNotExists, UserAlreadyExists {
        BikeRentalSystem b6 = new BikeRentalSystem(1);
        b6.registerUser(1, "dsa", 1);
        b6.getUsers().get(0).setCredit(-10);
        new Deposit(1);
        b6.addLock(1, 1);
        assertEquals(-1, b6.getBicycle(1, 1, 0), "O saldo deve ser menor que 1");
    }

    /**
     * Testa o metodo getBicycle quando não existe bicicletas no deposito para alugar. ECP
     * @throws UserDoesNotExists
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void getBicycleNoBikestest_gB5() throws UserDoesNotExists, UserAlreadyExists {
        BikeRentalSystem b7 = new BikeRentalSystem(1);
        b7.registerUser(1, "dsa", 1);
        b7.getUsers().get(0).setCredit(100);
        b7.addLock(1, 1);
        b7.addLock(1, 2);
        b7.addLock(1, 3);
        b7.getDeposits().get(0).getLocks().get(0).open();
        b7.getDeposits().get(0).getLocks().get(1).open();
        b7.getDeposits().get(0).getLocks().get(2).open();
        assertEquals(-1, b7.getBicycle(1, 1, 0), "Não deveria ter bicicletas disponiveis");
    }

    /**
     * Testa o metodo getBicycle de forma válida. ECP
     * @throws UserDoesNotExists
     * @throws UserAlreadyExists
     */
    @Test
    public void getBicycleValidBikestest_gB4() throws UserDoesNotExists, UserAlreadyExists {
        BikeRentalSystem b6 = new BikeRentalSystem(1);
        b6.registerUser(1, "dsa", 1);
        b6.getUsers().get(0).setCredit(100);
        b6.addLock(1, 1);
        b6.addLock(1, 2);
        b6.addLock(1, 3);
        Bike bike = new Bike(10);

        b6.getDeposits().get(0).getLocks().get(2).setBike(bike);
        b6.getDeposits().get(0).getLocks().get(2).close();
        assertEquals(10, b6.getBicycle(1, 1, 0), "Não deveria ter bicicletas disponiveis");
    }

    /**
     * Testa o metodo getBicycle quando o utilzador ja tem uma bicicleta alugada
     * @throws UserDoesNotExists
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void getBicycleAlreadyRented_gB6() throws UserDoesNotExists, UserAlreadyExists {
        BikeRentalSystem b6 = new BikeRentalSystem(1);
        b6.registerUser(1, "dsa", 1);
        b6.getUsers().get(0).setCredit(100);
        b6.addLock(1, 1);
        b6.addLock(1, 2);
        b6.addLock(1, 3);
        Bike bike = new Bike(10);
        Bike bike2 = new Bike(11);
        b6.getUsers().get(0).setBike(bike);
        b6.getDeposits().get(0).getLocks().get(2).setBike(bike2);
        b6.getDeposits().get(0).getLocks().get(2).close();
        assertEquals(-1, b6.getBicycle(1, 1, 0), "O user ja tem uma bike alugada. nao pode alugar outra");
    }

    /**
     * Testa o metodo returnBicycle com um IDUSER invaldio. ECP
     * @Expect -1
     */
    @Test
    public void returnBicycleInvalidIDUSERtest_rB1(){
        BikeRentalSystem b8 = new BikeRentalSystem(1);
        assertEquals(-1,b8.returnBicycle(1,-1,0),"O user nao pode ser encontrado");
    }

    /**
     * Testa o metodo returnBicycle com um IDDeposit não existente. ECP
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void returnBicycleInvalidDeposittest_rB2() throws UserAlreadyExists{
        BikeRentalSystem b8 = new BikeRentalSystem(1);
        b8.registerUser(3,"fsd",1);
        assertEquals(-1,b8.returnBicycle(1,3,0),"O deposito nao pode ser encontrado");
    }

    /**
     * Testa o metodo returnBicycle quando o utilizador nao tem uma bicicleta alugada e tenta devolver.
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void returnBicycleNotRentedtest_rB6() throws UserAlreadyExists{
        BikeRentalSystem b8 = new BikeRentalSystem(1);
        Bike bike = new Bike(7);
        bike.setInUSe(false);
        b8.registerUser(1,"lkj",1);
        b8.getUsers().get(0).setCredit(100);
        new Deposit(1);
        b8.addLock(1,1);
        b8.addLock(1,2);
        b8.addLock(1,3);
        b8.getDeposits().get(0).getLocks().get(0).open();
        b8.getDeposits().get(0).getLocks().get(1).open();
        b8.getDeposits().get(0).getLocks().get(2).open();
        assertEquals(-1,b8.returnBicycle(1,1,0),"A bicicleta nao tem dono");
    }

    /**
     * Tests o metodo returnBicycle quando o deposito se encontra cheio.
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void returnBicycleNoFreeSpacetest_rB3() throws UserAlreadyExists{
        BikeRentalSystem b8 = new BikeRentalSystem(1);
        Bike bike = new Bike(7);
        bike.setInUSe(true);
        b8.registerUser(1,"lkj",1);
        b8.getUsers().get(0).setCredit(100);
        b8.getUsers().get(0).setBike(bike);
        new Deposit(1);
        b8.addLock(1,1);
        b8.addLock(1,2);
        b8.addLock(1,3);
        b8.getDeposits().get(0).getLocks().get(0).close();
        b8.getDeposits().get(0).getLocks().get(1).close();
        b8.getDeposits().get(0).getLocks().get(2).close();
        assertEquals(-1,b8.returnBicycle(1,1,0),"A bicicleta nao tem dono");
    }

    /**
     * Testa o metodo returnBicycle com um endtime negativo
     * @throws UserAlreadyExists
     * @Expect -1
     */
    @Test
    public void returnBicycleInvalidEntimetest_rB5() throws UserAlreadyExists{
        BikeRentalSystem b8 = new BikeRentalSystem(1);
        Bike bike = new Bike(7);
        bike.setInUSe(true);
        b8.registerUser(1,"lkj",1);
        new Deposit(1);
        b8.addLock(1,1);
        b8.addLock(1,2);
        b8.addLock(1,3);
        b8.getDeposits().get(0).getLocks().get(0).open();
        b8.getDeposits().get(0).getLocks().get(1).open();
        b8.getDeposits().get(0).getLocks().get(2).open();
        assertEquals(-1,b8.returnBicycle(1,1,0),"Endtime errado");
    }

    /**
     * Testa o metodo returnBicycle valido. ECP
     * @throws UserAlreadyExists
     * @Expect 98. User.credit
     */
    @Test
    public void returnBicycleValidtest_rB7() throws UserAlreadyExists{
        BikeRentalSystem b8 = new BikeRentalSystem(1);
        Bike bike = new Bike(7);
        bike.setInUSe(true);
        b8.registerUser(1,"lkj",1);
        b8.getUsers().get(0).setCredit(100);
        b8.getUsers().get(0).setBike(bike);
        b8.getUsers().get(0).setStartRental(1);
        new Deposit(1);
        b8.addLock(1,1);
        b8.addLock(1,2);
        b8.addLock(1,3);
        b8.getDeposits().get(0).getLocks().get(0).open();
        b8.getDeposits().get(0).getLocks().get(1).close();
        b8.getDeposits().get(0).getLocks().get(2).close();
        assertEquals(98,b8.returnBicycle(1,1,3),"A bicicleta nao conseguiu ser devolvida");
    }

    /**
     * Testa o metodo bicycleRentalFee com inputs validos
     * @Expect 4
     */
    @Test
    public void rentalFeeValidrentalProgram1test_brF4(){
        BikeRentalSystem b9 = new BikeRentalSystem(1);
        assertEquals(4,b9.bicycleRentalFee(1,1,5,0),"fee mal calculada");
    }

    /**
     * Testa o metodo bicycleRentalFee com um starttime > endtime
     * @Expect 0
     */
    @Test
    public void rentalFeeInvalidtimestest_bRF3(){
        BikeRentalSystem b9 = new BikeRentalSystem(10);
        assertEquals(0,b9.bicycleRentalFee(1,5,1,3),"Endtime<Starttime");
    }

    /**
     * Testa o metodo bicycleRentFee com um rentalProgram inválido
     * @Expect 0
     */
    @Test
    public void rentalFeeInvalidrentalProgramtest_bRF1(){
        BikeRentalSystem b9 = new BikeRentalSystem(1);
        assertEquals(0,b9.bicycleRentalFee(0,1,2,3),"rentalProgram 0 devia dar 0");
    }

    /**
     * metodo que testa o metodo bicycleRentalFee com um rentalProgram inválido
     * @Expect 0
     */
    @Test
    public void rentalFeeInvalidrentalProgramtest_bRF2(){
        BikeRentalSystem b9 = new BikeRentalSystem(10);
        assertEquals(0,b9.bicycleRentalFee(3,1,5,10),"rentalProgram 3 devia dar 0");
    }

    /**
     * metodo de teste que valida o médtodo bicycleRentalFee com rentalProgram = 2 e resto da divisao = 0
     * @Expect 0
     */
    @Test
    public void rentalFeeInvalidrentalProgram2anddivionEquals0test_bRF5(){
        BikeRentalSystem b9 = new BikeRentalSystem(1);
        assertEquals(0,b9.bicycleRentalFee(2,1,5,10),"Não deu resto divisao = 0");
    }

    /**
     * metodo de teste que valida o médtodo bicycleRentalFee com rentalProgram = 2 e resto da divisao != 0
     * @Expect 4
     */
    @Test
    public void rentalFeeInvalidrentalProgram2anddivionNOTequal0test_bRF6(){
        BikeRentalSystem b9 = new BikeRentalSystem(1);
        assertEquals(4,b9.bicycleRentalFee(2,1,5,5),"fee mal calculada");
    }

    /**
     * metodo de teste que valida o médtodo bicycleRentalFee com rentalProgram = 2 e resto da divisao = 0 e endtime - starttime > 10
     * @Expect 12
     */
    @Test
    public void rentalFeeInvalidrentalProgram2anddivionNOTequal0test_bRF7(){
        BikeRentalSystem b9 = new BikeRentalSystem(1);
        assertEquals(12,b9.bicycleRentalFee(2,1,15,5),"fee mal calculada");
    }

    /**
     * metodo de teste que valida o médtodo bicycleRentalFee com rentalProgram = 2 e starttime > endtime
     * @Expect 0
     */
    @Test
    public void rentalFeeInvalidrentalProgram2invalidtimestest_bRF8(){
        BikeRentalSystem b9 = new BikeRentalSystem(1);
        assertEquals(0,b9.bicycleRentalFee(2,15,1,5),"startime>endtime");
    }
}
