package mx.bytecraft.apps.alertamx.backend;

import java.util.List;

public class Response {
    private List<Alert> alerts;

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
