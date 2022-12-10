package q4;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

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
    private PersonName personName;
    private Image idleSprire, walkingSprite, phoneSprite;

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

    public ElevatorPerson(Person person, int initialPosition, int target, PersonName personName) {
        this.initialPosition = initialPosition;
        this.target = target;
        this.person = person;
        this.enterTime = Elevator.getTravelMeter();
        this.personName = personName;
        try {
            loadSprite();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
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

    private void loadSprite() throws FileNotFoundException {
        switch (personName) {
            case PAUL -> {
                this.phoneSprite = new Image(new FileInputStream("res/paul/paul_phone.png"));
                this.idleSprire = new Image(new FileInputStream("res/paul/paul_idle.png"));
                this.walkingSprite = new Image(new FileInputStream("res/paul/paul_walk.png"));
            }
            case COLT -> {
                this.phoneSprite = new Image(new FileInputStream("res/colt/colt_phone.png"));
                this.idleSprire = new Image(new FileInputStream("res/colt/colt_idle.png"));
                this.walkingSprite = new Image(new FileInputStream("res/colt/colt_walk.png"));
            }
            case FRANKIE -> {
                this.phoneSprite = new Image(new FileInputStream("res/frankie/frankie_phone.png"));
                this.idleSprire = new Image(new FileInputStream("res/frankie/frankie_idle.png"));
                this.walkingSprite = new Image(new FileInputStream("res/frankie/frankie_walk.png"));
            }
            case MAIA -> {
                this.phoneSprite = new Image(new FileInputStream("res/maia/maia_phone.png"));
                this.idleSprire = new Image(new FileInputStream("res/maia/maia_idle.png"));
                this.walkingSprite = new Image(new FileInputStream("res/maia/maia_walk.png"));
            }
        }
    }

    public Image getIdleSprire() {
        return idleSprire;
    }

    public Image getWalkingSprite() {
        return walkingSprite;
    }

    public Image getPhoneSprite() {
        return phoneSprite;
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

    public void setWaiting(boolean waiting) {
        this.waiting = waiting;
    }
}
