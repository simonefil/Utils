public abstract class BaseClass {

    public boolean HasError;
    public String ErrorDescription;

    public BaseClass() {
        this.HasError = false;
        this.ErrorDescription = "";
    }

    protected void pSetError(String parMessage) {
        this.HasError = true;
        this.ErrorDescription = parMessage;
    }
}
