/* notes
cannot end prematurely
minimum 100 square feet
cannot do business in a state with no tax record
display order at end to confirm
edit checks for each field, displays current value, keeps current value if empty input
confirm on remove
use lambdas
 */

package org.WileyEdgeCorp.FlooringMastery;

import org.WileyEdgeCorp.FlooringMastery.controller.Controller;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        Controller controller = ctx.getBean("controller", Controller.class);

        controller.run();
    }
}
