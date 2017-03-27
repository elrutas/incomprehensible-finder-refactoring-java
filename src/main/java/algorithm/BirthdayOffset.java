package algorithm;
public class BirthdayOffset {

	public Person youngerPerson;
	public Person olderPerson;
	public long offset;

	public BirthdayOffset() {}

	public BirthdayOffset(Person firstPerson, Person secondPerson) {
		if (firstPerson.birthDate.getTime() < secondPerson.birthDate.getTime()) {
			youngerPerson = firstPerson;
			olderPerson = secondPerson;
		} else {
			youngerPerson = secondPerson;
			olderPerson = firstPerson;
		}

		calculateOffset();
	}

	private void calculateOffset() {
		offset = olderPerson.birthDate.getTime() - youngerPerson.birthDate.getTime();
	}

	public long getOffset(){
		return offset;
	}
}
