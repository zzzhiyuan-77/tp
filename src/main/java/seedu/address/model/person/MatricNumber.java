package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a student's matriculation number.
 * Guarantees: immutable and contains no whitespace.
 */
public class MatricNumber {

    public static final String MESSAGE_CONSTRAINTS = "Matriculation numbers should not be blank or contain spaces";
    public static final String VALIDATION_REGEX = "\\S+";
    public final String value;

    /** Creates a matriculation number. */
    public MatricNumber(String matricNumber) {
        requireNonNull(matricNumber);
        checkArgument(matricNumber.isEmpty() || isValidMatricNumber(matricNumber), MESSAGE_CONSTRAINTS);
        value = matricNumber;
    }

    public static boolean isValidMatricNumber(String test) {
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
        if (!(other instanceof MatricNumber otherMatricNumber)) {
            return false;
        }
        return value.equalsIgnoreCase(otherMatricNumber.value);
    }

    @Override
    public int hashCode() {
        return value.toLowerCase().hashCode();
    }
}
