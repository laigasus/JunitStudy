package ch05.transmission;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TransmissionTest {
    private Transmission transmission;
    private Car car;

    @BeforeEach
    public void create() {
        car = new Car();
        transmission = new Transmission(car);
    }

    @Test
    public void remainsInDriveAfterAcceleration() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(35);
        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    @Test
    public void ignoresShiftToParkWhileInDrive() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);

        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(), equalTo(Gear.DRIVE));
    }

    @Test
    public void allowsShiftToParkWhenNotMoving() {
        transmission.shift(Gear.DRIVE);
        car.accelerateTo(30);
        car.brakeToStop();

        transmission.shift(Gear.PARK);

        assertThat(transmission.getGear(), equalTo(Gear.PARK));
    }
}
