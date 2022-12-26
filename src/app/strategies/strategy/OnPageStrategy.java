package app.strategies.strategy;

import app.App;
import app.features.ActionTacker;
import app.features.FeatureFactory;
import app.strategies.Strategy;
import io.input.action.Request;
import io.output.Output;
import io.output.response.Response;

public class OnPageStrategy extends Strategy {

    public OnPageStrategy(Request request) {
        super(request);
    }

    @Override
    public void execute() {
        if (App.getInstance().getCurrentPage().containsFeature(request.getFeature())) {
            ActionTacker actionTacker = FeatureFactory.createAction(request.getFeature());
            actionTacker.takeAction(request);
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
