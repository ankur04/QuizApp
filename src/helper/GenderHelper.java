package helper;

import model.Gender;

public class GenderHelper {

    public static Gender getGender(String gender) {
        if (gender.equals("Male"))
            return Gender.MALE;
        else if (gender.equals("Female"))
            return Gender.FEMALE;
        else
            return Gender.DONT_WANT_TO_SPECIFY;
    }

}
