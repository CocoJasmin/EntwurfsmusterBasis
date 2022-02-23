import Adapter.IElectronicDevice;
import Adapter.SmartphoneAdapter;
import SmartphoneBasis.Smartphone;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class AdapterPatternTest {
    private IElectronicDevice smartphone;

    public AdapterPatternTest() {
    }

    @BeforeEach
    public void setup() {
        this.smartphone = new SmartphoneAdapter();
        Assertions.assertNotNull(smartphone);
    }

    @Test
    @Order(1)
    public void loadSmartphoneWithAdapter() {
        int[][][] expectedBatteryStatus = new int[10][10][10];
        for (int[][] row : expectedBatteryStatus) {
            for (int[] rowColumn : row) {
                Arrays.fill(rowColumn, 1);
            }
        }
        //load smartphone-battery
        this.smartphone.threePinConnectorLoading();
        //controls if battery is fully charged
        Assertions.assertArrayEquals(expectedBatteryStatus, ((Smartphone) smartphone).getBatteryStatus());
    }
}