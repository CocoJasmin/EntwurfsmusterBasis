package State;

import SmartphoneBasis.Smartphone;

public class Disabled implements ISmartphoneState {
    private int inputAttempts = 0;

    public void pinValidation(Smartphone smartphone, int enteredPin) {
        inputAttempts++;
        if (enteredPin == smartphone.getSuperPin()) {
            smartphone.setState(new Unlocked());
            System.out.println("Smartphone successfully unlocked!");
            return;
        }
        if (inputAttempts >= 3) {
            smartphone.setState(new Inactive());
            System.out.println("Entered wrong super-pin 3 times: Smartphone inactive now!");
        }
    }
}
