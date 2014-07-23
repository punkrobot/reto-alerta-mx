package mx.bytecraft.apps.alertamx.backend;

public class Alert {

    private String id;
    private String sent;
    private String senderName;
    private String event;
    private String description;
    private String instruction;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSent() {
        return sent;
    }

    public void setSent(String sent) {
        this.sent = sent;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }
}
