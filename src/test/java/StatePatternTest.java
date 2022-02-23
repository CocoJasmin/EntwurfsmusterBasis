import SmartphoneBasis.Smartphone;
import State.Disabled;
import State.Inactive;
import State.Locked;
import State.Unlocked;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import java.util.Random;

@TestMethodOrder(OrderAnnotation.class)
public class StatePatternTest {
    private Smartphone smartphone;

    public StatePatternTest() {
    }

    @BeforeEach
    public void setup() {
        this.smartphone = new Smartphone();
        //smartphone initial state and Pins are correct
        Assertions.assertNotNull(smartphone);
        Assertions.assertEquals(smartphone.getState().getClass(), Locked.class);
        Assertions.assertEquals(7832, smartphone.getPin());
        Assertions.assertEquals(93814, smartphone.getSuperPin());
    }

    @Test
    @Order(1)
    public void enterValidPin() {
        //enter valid Pin
        this.smartphone.pinValidation(smartphone.getPin());
        Assertions.assertEquals(Unlocked.class, smartphone.getState().getClass());
    }

    @Test
    @Order(2)
    public void enterWrongPinAndValidSuperPin() {
        //enter Pin wrong three times
        for (int enterPinCount = 0; enterPinCount < 3; enterPinCount++) {
            this.smartphone.pinValidation(1234);
        }
        Assertions.assertEquals(Disabled.class, smartphone.getState().getClass());
        //enter valid Super-Pin
        this.smartphone.pinValidation(smartphone.getSuperPin());
        Assertions.assertEquals(Unlocked.class, smartphone.getState().getClass());
    }

    @Test
    @Order(3)
    public void enterPinAndSuperPinWrongThreeTimes() {
        //enter Pin wrong three times
        for (int enterPinCount = 0; enterPinCount < 3; enterPinCount++) {
            this.smartphone.pinValidation(1234);
        }
        Assertions.assertEquals(Disabled.class, smartphone.getState().getClass());
        //enter Super-Pin wrong three times
        for (int enterPinCount = 0; enterPinCount < 3; enterPinCount++) {
            this.smartphone.pinValidation(1234);
        }
        Assertions.assertEquals(Inactive.class, smartphone.getState().getClass());
    }

    @Test
    @Order(4)
    public void enterValidPinAfterEnteringWrong() {
        //enter Pin wrong several times, but not three times
        Random random = new Random();
        int enterPinCount = random.nextInt(2);
        while (enterPinCount < 2) {
            this.smartphone.pinValidation(1234);
            enterPinCount++;
        }
        Assertions.assertEquals(Locked.class, smartphone.getState().getClass());
        //enter valid Pin
        this.smartphone.pinValidation(smartphone.getPin());
        Assertions.assertEquals(Unlocked.class, smartphone.getState().getClass());
    }

    @Test
    @Order(5)
    public void enterValidSuperPinAfterEnteringWrong() {
        //enter Pin wrong three times
        for (int enterPinCount = 0; enterPinCount < 3; enterPinCount++) {
            this.smartphone.pinValidation(1234);
        }
        Assertions.assertEquals(Disabled.class, smartphone.getState().getClass());
        //enter super-Pin wrong several times, but not three times
        Random random = new Random();
        int enterPinCount = random.nextInt(2);
        while (enterPinCount < 2) {
            this.smartphone.pinValidation(1234);
            enterPinCount++;
        }
        Assertions.assertEquals(Disabled.class, smartphone.getState().getClass());
        //enter valid SuperPin
        this.smartphone.pinValidation(smartphone.getSuperPin());
        Assertions.assertEquals(Unlocked.class, smartphone.getState().getClass());
    }
}
