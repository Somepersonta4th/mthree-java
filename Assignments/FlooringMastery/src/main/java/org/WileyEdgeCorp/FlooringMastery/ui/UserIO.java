package org.WileyEdgeCorp.FlooringMastery.ui;

import java.math.BigDecimal;
import java.util.Date;

public interface UserIO {

    void print(String message);

    int readInt(String s);

    int readInt(String s, int min);

    int readInt(String prompt, int min, boolean canBeEmpty);

    int readInt(String prompt, int min, int max);

    int readInt(String prompt, int min, int max, boolean canBeEmpty);

    String readString(String s);

    Date readDate(String s);

    BigDecimal readBigDecimal(String s);

    BigDecimal readBigDecimal(String s, boolean canBeNull);
}
