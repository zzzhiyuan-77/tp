package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/** Represents a student's tutorial group. */
public class TutorialGroup {

    public static final String MESSAGE_CONSTRAINTS = "Tutorial groups should not be blank or contain spaces";
    public static final String VALIDATION_REGEX = "\\S+";
    public final String value;

    /** Creates a tutorial group. */
    public TutorialGroup(String tutorialGroup) {
        requireNonNull(tutorialGroup);
        checkArgument(tutorialGroup.isEmpty() || isValidTutorialGroup(tutorialGroup), MESSAGE_CONSTRAINTS);
        value = tutorialGroup;
    }

    public static boolean isValidTutorialGroup(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof TutorialGroup otherTutorialGroup)) {
            return false;
        }
        return value.equalsIgnoreCase(otherTutorialGroup.value);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
