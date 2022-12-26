package app.features.auth;

import app.App;
import io.input.action.Request;
import app.features.FeatureCommand;
import components.user.User;

public final class BuyTokenFeature implements FeatureCommand {
    /**
     * Converts user amount of money into token currency.
     * @param request - input for the feature
     */
    @Override
    public void doCommand(final Request request) {
        User activeUser = App.getInstance().getCurrentUser();
        int balance = Integer.parseInt(activeUser.getCredentials().getBalance());
        activeUser.addTokens(request.getCount());
        activeUser.getCredentials().setBalance(Integer.toString(balance - request.getCount()));
    }
}
