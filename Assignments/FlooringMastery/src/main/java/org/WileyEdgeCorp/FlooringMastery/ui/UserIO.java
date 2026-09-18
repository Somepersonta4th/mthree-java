package org.WileyEdgeCorp.FlooringMastery.ui;

import java.math.BigDecimal;
import java.util.Date;

public interface UserIO {

    void print(String message);

    int readInt(String s);

    int readInt(String s, int min);

    int readInt(String prompt, int min, int max);

    String readString(String s);

    Date readDate(String s);

    BigDecimal readBigDecimal(String s);
}
