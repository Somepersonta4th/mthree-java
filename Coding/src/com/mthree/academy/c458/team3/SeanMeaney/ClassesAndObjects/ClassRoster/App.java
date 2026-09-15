import controller.ClassRosterController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public void run() {
        /*
        UserIO io = new UserIOConsoleImpl();
        ClassRosterView consoleView = new ClassRosterView(io);
        ClassRosterDao fileDao = new ClassRosterDaoFileImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoFileImpl();
        ClassRosterServiceLayer service = new ClassRosterServiceLayerImpl(fileDao,auditDao);

        ClassRosterController controller = new ClassRosterController(consoleView,service);
        controller.run();
         */
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller =
                ctx.getBean("controller", ClassRosterController.class);
        controller.run();

    }

    public static void main(String[] args) {
        App application = new App();
        application.run();
    }

}
