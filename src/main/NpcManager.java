package main;

import q4.Elevator;
import q4.ElevatorPerson;
import q4.Person;

public class NpcManager {
    public static ElevatorPerson[] people = new ElevatorPerson[8];
    static {
        people[0] = new ElevatorPerson(new Person("Paul"),0,3);
        people[1] = new ElevatorPerson(new Person("Maia"),1,3);
        people[2] = new ElevatorPerson(new Person("Frankie"),1,3);
        people[3] = new ElevatorPerson(new Person("Colt"),2,3);
        people[4] = new ElevatorPerson(new Person("Justine"),2,3);
        people[5] = new ElevatorPerson(new Person("Jewel"),2,3);
        people[6] = new ElevatorPerson(new Person("Ryan"),3,1);
        people[7] = new ElevatorPerson(new Person("Chance"),3,0);
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
