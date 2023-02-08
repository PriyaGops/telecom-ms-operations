package au.com.telecom.domain;

public class ActivateRequest {

    private boolean activated;

    public ActivateRequest(){
        super();
    }

    public ActivateRequest(boolean activated) {
        this.activated = activated;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
