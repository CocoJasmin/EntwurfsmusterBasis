package State;

import SmartphoneBasis.Smartphone;

public interface ISmartphoneState {
    void pinValidation(Smartphone smartphone, int enteredPin);
}
