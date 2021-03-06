package SmartphoneBasis;

import State.ISmartphoneState;
import State.Locked;

public class Smartphone {
    private final int pin;
    private final int superPin;
    private ISmartphoneState state;
    private int[][][] batteryStatus;

    public Smartphone() {
        setState(new Locked());
        this.batteryStatus = new int[10][10][10];
        this.pin = 7832;
        this.superPin = 93814;
    }

    public int[][][] getBatteryStatus() {
        return batteryStatus;
    }

    public int getPin() {
        return pin;
    }

    public int getSuperPin() {
        return superPin;
    }

    public ISmartphoneState getState() {
        return state;
    }

    public void setState(ISmartphoneState state) {
        this.state = state;
    }

    public void pinValidation(int enteredPin) {
        state.pinValidation(this, enteredPin);
    }

    public void twoPinConnectorLoading() {
        int currentBatteryStatus = 0;
        for (int[][] status : batteryStatus) {
            for (int[] ints : status) {
                for (int anInt : ints) {
                    if (anInt == 1) {
                        currentBatteryStatus++;
                    }
                }
            }
        }
        System.out.println("batteryStatus: " + currentBatteryStatus / 10 + "%");
        for (int i = 0; i < batteryStatus.length; i++) {
            for (int y = 0; y < batteryStatus[i].length; y++) {
                for (int z = 0; z < batteryStatus[i][y].length; z++) {
                    if (batteryStatus[i][y][z] == 0) {
                        batteryStatus[i][y][z] = 1;
                        currentBatteryStatus++;
                    }
                }
            }
            try {
                Thread.sleep(1000);
                System.out.println("battery is loading, battery level: " + currentBatteryStatus / 10 + "%");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Battery is fully charged: " + currentBatteryStatus / 10 + "%");
    }
}