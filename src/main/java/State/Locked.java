package State;

public class Locked implements ISmartphoneState {
    private int inputAttempts = 0;

    public void pinValidation(Smartphone smartphone, int enteredPin) {
        inputAttempts++;
            if (enteredPin == smartphone.getPin()) {
                smartphone.setState(new Unlocked());
                System.out.println("Smartphone successfully unlocked!");
                return;
            }
            if(inputAttempts>=3) {
                System.out.println("Entered wrong pin 3 times: Smartphone disabled now!");
                smartphone.setState(new Disabled());
            }
        }
}