package Adapter;

import SmartphoneBasis.Smartphone;

public class SmartphoneAdapter extends Smartphone implements IElectronicDevice {
    public void threePinConnectorLoading() {
        System.out.println("Smartphone-battery is loading!");
        twoPinConnectorLoading();
    }
}