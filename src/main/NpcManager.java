package main;

import q4.ElevatorPerson;
import q4.Person;

import static q4.PersonName.*;

public class NpcManager {
    public static ElevatorPerson[] people = new ElevatorPerson[8];
    static {
        people[0] = new ElevatorPerson(new Person("Paul"),1,3, PAUL);
        people[1] = new ElevatorPerson(new Person("Maia"),2,3, MAIA);
        people[2] = new ElevatorPerson(new Person("Frankie"),1,2, FRANKIE);
        people[3] = new ElevatorPerson(new Person("Colt"),2,1, COLT);
        people[4] = new ElevatorPerson(new Person("Justine"),2,0, JUSTINE);
        people[5] = new ElevatorPerson(new Person("Jewel"),1,2, JEWEL);
        people[6] = new ElevatorPerson(new Person("Ryan"),3,1, RYAN);
        people[7] = new ElevatorPerson(new Person("Chance"),3,0, CHANCE);
    }

    public static ElevatorPerson[] getPeopleOnFloor(int floor) {
        ElevatorPerson[] list = new ElevatorPerson[8];
        int iter = 0;
        for (int i = 0; i < 8; i++) {
            if (people[i].getInitialPosition() == floor)
                list[iter++] = people[i];
        }
        return list;
    }
}
