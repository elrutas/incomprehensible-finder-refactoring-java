package algorithm;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Finder {
	private final List<Person> people;

	public Finder(List<Person> people) {
		this.people = people;
	}

	public BirthdayOffset Find(AgeDifference ageDifference) {
	    if (people.size() <= 1) {
            return new BirthdayOffset();
        }

        List<BirthdayOffset> birthdayOffsets = calculateBirthdayOffsets();

        return findBirthdayOffset(ageDifference, birthdayOffsets);
	}

    private BirthdayOffset findBirthdayOffset(AgeDifference ageDifference, List<BirthdayOffset> birthdayOffsets) {
        if (ageDifference == AgeDifference.BIGGEST) {
            return Collections.max(birthdayOffsets, Comparator.comparing(BirthdayOffset::getOffset));
        } else {
            return Collections.min(birthdayOffsets, Comparator.comparing(BirthdayOffset::getOffset));
        }
    }

    private List<BirthdayOffset> calculateBirthdayOffsets() {
        List<BirthdayOffset> birthdayOffsets = new ArrayList<BirthdayOffset>();

        for (Person firstPerson : people) {
            for (Person secondPerson : people) {
                if (firstPerson != secondPerson) {
                    birthdayOffsets.add(new BirthdayOffset(firstPerson, secondPerson));
                }
            }
        }

        return birthdayOffsets;
    }
}
