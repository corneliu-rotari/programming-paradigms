package app.action;

public class DefaultAction implements ActionTacker {
    @Override
    public void takeAction(Action action) {
        System.out.println("Not yet implemented " + action.getFeature());
    }
}
