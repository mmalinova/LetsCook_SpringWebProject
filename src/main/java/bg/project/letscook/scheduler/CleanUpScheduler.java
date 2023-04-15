package bg.project.letscook.scheduler;

import bg.project.letscook.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CleanUpScheduler {

    private final UserService userService;

    public CleanUpScheduler(UserService userService) {
        this.userService = userService;
    }

    @Scheduled(cron = "* 0 2 * * *")
    public void cleanUp() {
        userService.deleteInactiveAccounts();
    }
}
