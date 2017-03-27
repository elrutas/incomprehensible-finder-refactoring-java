package algorithm;
import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> people;

	public Finder(List<Person> p) {
		people = p;
	}

	public BirthdayOffset Find(AgeDifference ageDifference) {
		List<BirthdayOffset> birthdayOffsets = new ArrayList<BirthdayOffset>();

		for (int i = 0; i < people.size() - 1; i++) {
			for (int j = i + 1; j < people.size(); j++) {
				BirthdayOffset r = new BirthdayOffset();
				if (people.get(i).birthDate.getTime() < people.get(j).birthDate.getTime()) {
					r.person1 = people.get(i);
					r.person2 = people.get(j);
				} else {
					r.person1 = people.get(j);
					r.person2 = people.get(i);
				}
				r.offset = r.person2.birthDate.getTime() - r.person1.birthDate.getTime();
				birthdayOffsets.add(r);
			}
		}

		if (birthdayOffsets.size() < 1) {
			return new BirthdayOffset();
		}

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
}
