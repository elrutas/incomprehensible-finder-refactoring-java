package algorithm;
import java.util.ArrayList;
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
        BirthdayOffset answer = birthdayOffsets.get(0);
        for (BirthdayOffset birthdayOffset : birthdayOffsets) {
            switch (ageDifference) {
                case SMALLEST:
                    if (birthdayOffset.offset < answer.offset) {
                        answer = birthdayOffset;
                    }
                    break;

                case BIGGEST:
                    if (birthdayOffset.offset > answer.offset) {
                        answer = birthdayOffset;
                    }
                    break;
            }
        }

        return answer;
    }

    private List<BirthdayOffset> calculateBirthdayOffsets() {
        List<BirthdayOffset> birthdayOffsets = new ArrayList<BirthdayOffset>();

        for (int i = 0; i < people.size() - 1; i++) {
            for (int j = i + 1; j < people.size(); j++) {
                BirthdayOffset birthdayOffset = new BirthdayOffset();
                if (people.get(i).birthDate.getTime() < people.get(j).birthDate.getTime()) {
                    birthdayOffset.youngerPerson = people.get(i);
                    birthdayOffset.olderPerson = people.get(j);
                } else {
                    birthdayOffset.youngerPerson = people.get(j);
                    birthdayOffset.olderPerson = people.get(i);
                }
                birthdayOffset.offset = birthdayOffset.olderPerson.birthDate.getTime() - birthdayOffset.youngerPerson.birthDate.getTime();
                birthdayOffsets.add(birthdayOffset);
            }
        }
        return birthdayOffsets;
    }

//	private BirthdayOffset closestBirthdays(List<BirthdayOffset> birthdayOffsets) {
//		return birthdayOffsets.stream().min(Comparator.comparingLong(BirthdayOffset::getOffset));
//	}
}
