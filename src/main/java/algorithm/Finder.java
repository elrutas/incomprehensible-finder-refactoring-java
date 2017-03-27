package algorithm;
import java.util.ArrayList;
import java.util.List;

public class Finder {
	private final List<Person> people;

	public Finder(List<Person> p) {
		people = p;
	}

	public Result Find(FT ft) {
		List<Result> results = new ArrayList<Result>();

		for (int i = 0; i < people.size() - 1; i++) {
			for (int j = i + 1; j < people.size(); j++) {
				Result r = new Result();
				if (people.get(i).birthDate.getTime() < people.get(j).birthDate.getTime()) {
					r.person1 = people.get(i);
					r.person2 = people.get(j);
				} else {
					r.person1 = people.get(j);
					r.person2 = people.get(i);
				}
				r.birthdayOffset = r.person2.birthDate.getTime() - r.person1.birthDate.getTime();
				results.add(r);
			}
		}

		if (results.size() < 1) {
			return new Result();
		}

		Result answer = results.get(0);
		for (Result result : results) {
			switch (ft) {
				case One :
					if (result.birthdayOffset < answer.birthdayOffset) {
						answer = result;
					}
					break;

				case Two :
					if (result.birthdayOffset > answer.birthdayOffset) {
						answer = result;
					}
					break;
			}
		}

		return answer;
	}
}
