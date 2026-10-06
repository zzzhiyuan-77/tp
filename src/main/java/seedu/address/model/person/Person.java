package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final MatricNumber matricNumber;
    private final TutorialGroup tutorialGroup;

    // Data fields
    private final Address address;
    private final Remark remark;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this(name, phone, email, address, new Remark(""), tags, new MatricNumber(""), new TutorialGroup(""));
    }

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags) {
        this(name, phone, email, address, remark, tags, new MatricNumber(""), new TutorialGroup(""));
    }

    /**
     * Creates a student record. Legacy contact fields are retained for compatibility with the existing model.
     */
    public Person(Name name, MatricNumber matricNumber, TutorialGroup tutorialGroup, Email email,
            Remark remark, Set<Tag> tags) {
        this(name, new Phone("000"), email, new Address("N/A"), remark, tags, matricNumber, tutorialGroup);
    }

    private Person(Name name, Phone phone, Email email, Address address, Remark remark, Set<Tag> tags,
            MatricNumber matricNumber, TutorialGroup tutorialGroup) {
        requireAllNonNull(name, phone, email, address, remark, tags, matricNumber, tutorialGroup);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.matricNumber = matricNumber;
        this.tutorialGroup = tutorialGroup;
        this.tags.addAll(tags);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public MatricNumber getMatricNumber() {
        return matricNumber;
    }

    public TutorialGroup getTutorialGroup() {
        return tutorialGroup;
    }

    public boolean isStudent() {
        return !matricNumber.value.isEmpty() && !tutorialGroup.value.isEmpty();
    }

    public Address getAddress() {
        return address;
    }

    public Remark getRemark() {
        return remark;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && (isStudent() && otherPerson.isStudent()
                    ? otherPerson.getMatricNumber().equals(getMatricNumber())
                    : otherPerson.getName().equals(getName()));
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person otherPerson)) {
            return false;
        }

        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && matricNumber.equals(otherPerson.matricNumber)
                && tutorialGroup.equals(otherPerson.tutorialGroup)
                && remark.equals(otherPerson.remark)
                && tags.equals(otherPerson.tags);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, matricNumber, tutorialGroup, remark, tags);
    }

    @Override
    public String toString() {
        if (isStudent()) {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("matricNumber", matricNumber)
                    .add("tutorialGroup", tutorialGroup)
                    .add("email", email)
                    .add("remark", remark)
                    .add("tags", tags)
                    .toString();
        }
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("remark", remark)
                .add("tags", tags)
                .toString();
    }

}
