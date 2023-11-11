package structurals.adapter;

interface Notification {
    void send(String message);
}

// Legacy code
class EmailNotification implements Notification {
    @Override
    public void send(String message) {
        System.out.println("Email notification: " + message);
    }
}

// SERVICE (MAYBE EXTERNAL)
class SlackApi {
    private String slackUser;
    private String slackToken;

    public SlackApi(String slackUser, String slackToken) {
        this.slackUser = slackUser;
        this.slackToken = slackToken;
    }

    public void login() {
        System.out.println("Logged into Slack with user: "+ slackUser + " and token: " + slackToken);
    }

    public void sendMessage(String message) {
        System.out.println("Slack notification: " + message);
    }
}

class SlackNotification implements Notification {
    private SlackApi slackApi;

    public SlackNotification(SlackApi slackApi) {
        this.slackApi = slackApi;
    }

    @Override
    public void send(String message) {
        slackApi.login();
        slackApi.sendMessage(message);
    }
}

public class NotificationAdapter {
    public static void main(String[] args) {
        Notification emailNotification = new EmailNotification();
        emailNotification.send("Hello world!");

        SlackApi slackApi = new SlackApi("user", "token");
        Notification slackNotification = new SlackNotification(slackApi);
        slackNotification.send("Hello world!");
    }
}
