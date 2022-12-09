package q4;

/**
 * @author Selçuk Gençay 20050111008
 */
public class ElevatorPerson {

    /** The value of travel meter when the person entered the elevator */
    private int enterTime;

    /** The floor which the person entered the elevator */
    private int initialPosition;

    /** The floor which the person wants to arrive */
    private int target;

    /** The person who takes the elevator */
    private Person person;

    private boolean waiting = true;

    /**
     * Generates an ElevatorPerson object for the given person
     *
     * @param person          person using the elevator
     * @param initialPosition current floor
     * @param target          target floor
     */
    public ElevatorPerson(Person person, int initialPosition, int target) {
        this.initialPosition = initialPosition;
        this.target = target;
        this.person = person;
        this.enterTime = Elevator.getTravelMeter();
    }

    public int getTarget() {
        return target;
    }

    public Person getPerson() {
        return person;
    }

    public int getInitialPosition() {
        return initialPosition;
    }

    public boolean isWaiting() {
        return waiting;
    }

    /**
     * Person gets unhappy if they spend more time than necessary in the elevator.
     *
     * @return Information about person and their experience with the elevator
     */
    @Override
    public String toString() {
        // shortest distance = |target - initialPosition|
        // distance traveled = Elevator.getTravelMeter() - enterTime
        return Math.abs(target - initialPosition) >= (Elevator.getTravelMeter() - enterTime)
                ? "I am " + person.getName() + ". I traveled " + (Elevator.getTravelMeter() - enterTime) + " floors. I am happy"
                : "I am " + person.getName() + ". I traveled " + (Elevator.getTravelMeter() - enterTime) + " floors. I am unhappy";
    }
}
