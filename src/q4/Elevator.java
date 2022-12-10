package q4;

/**
 * @author Selçuk Gençay 20050111008
 */
public class Elevator {

    private int totalUsers;

    /** Position of the elevator */
    private int currentFloor;

    /** Highest floor that the elevator can go */
    private int maxFloor;

    /** Lowest floor that the elevator can go */
    private int minFloor;

    /** Number of people that can use the elevator at the same time */
    private int capacity;

    /** People in the elevator */
    private MyStack people;

    /** Keeps track of how many floors are travelled */
    private static int travelMeter;

    /**
     * Generates an elevator with the capacity of 4 people
     * for a building with 11 floors (0-10), on the 0th floor.
     */
    public Elevator() {
        this(4, 0, 10);
    }

    /**
     * Generates an elevator with the capacity of specified people, on the 0th floor.
     *
     * @param capacity max number of people that elevator can hold
     * @param minFloor lowest floor that elevator can go
     * @param maxFloor highest floor that elevator can go
     */
    public Elevator(int capacity, int minFloor, int maxFloor) {
        this.maxFloor = maxFloor;
        this.minFloor = minFloor;
        this.capacity = capacity;
        this.currentFloor = 0;
        this.people = new MyStack();
    }

    /**
     * Tries to add given person to the elevator
     *
     * @param person person who wants to enter the elevator
     * @param target the floor which the person wants to arrive
     * @return true if person entered the elevator, otherwise false
     * @throws IllegalArgumentException when the elevator cannot go to the given floor
     */
    public boolean enter(Person person, int target) throws IllegalArgumentException {
        if (target > maxFloor || target < minFloor)
            throw new IllegalArgumentException("target " + target + " out of bounds");
        if (!isFull()) {
            people.push(new ElevatorPerson(person, currentFloor, target));
            System.out.println(person.getName() + " is in.");
            totalUsers++;
            return true;
        }
        return false;
    }

    public boolean enter(ElevatorPerson person) throws IllegalArgumentException {
        if (!isFull()) {
            people.push(person);
            System.out.println(person.getPerson().getName() + " is in.");
            person.entered();
            totalUsers++;
            return true;
        }
        return false;
    }

    /**
     * Sends the elevator to the target floor, updates the travel meter,
     * lets the people out.
     * <p>
     * Only the last n people who arrived their destination can leave.
     * (if the last person entered doesn't want to leave, then no one can leave)
     *
     * @param floor Target floor
     * @throws IllegalArgumentException when the elevator cannot go to the given floor
     */
    public void goToFloor(int floor) throws IllegalArgumentException {
        if (floor > maxFloor || floor < minFloor)
            throw new IllegalArgumentException("target " + floor + " out of bounds");

        // increment travelMeter by the distance
        Elevator.travelMeter += Math.abs(floor - currentFloor);
        // set position to the new floor
        this.currentFloor = floor;
        // while the elevator is not empty ...
        while (!people.isEmpty()) {
            // ... if the last person wants to leave ...
            if (((ElevatorPerson) people.peek()).getTarget() == floor) {
                // let them out.
                ElevatorPerson personLeft = (ElevatorPerson) people.pop();
                System.out.println(personLeft.getPerson().getName() + " is out.");
                personLeft.exited();
                personLeft.setWaiting(false);
                System.out.println(personLeft);
            } else {
                // if not, they can't leave no more
                break;
            }
        }

        System.out.println(this);
    }

    /**
     * Takes the last person to their destination, repeats until the elevator is empty
     */
    public void releaseEveryone() {
        while (!isEmpty())
            goToFloor(((ElevatorPerson) people.peek()).getTarget());
    }

    /**
     * @return True if the elevator is full
     */
    public boolean isFull() {
        return people.getSize() >= capacity;
    }

    /**
     * @return True if the elevator is empty
     */
    public boolean isEmpty() {
        return people.getSize() <= 0;
    }

    /**
     * @return Number of floors travelled
     */
    public static int getTravelMeter() {
        return travelMeter;
    }

    public int getTotalUsers() {
        return totalUsers;
    }

    public ElevatorPerson lastEntered() {
        return !people.isEmpty() ? (ElevatorPerson) people.peek(): null;
    }

    /**
     * @return Information about the elevator
     */
    @Override
    public String toString() {
        return "Elevator is on floor: " + currentFloor + "\nNumber of people: " + people.getSize() +
                "\nTravelled " + travelMeter + " floors.";
    }
}