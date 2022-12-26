package app.strategies.strategy;

import app.App;
import app.features.FeatureCommand;
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
        if (request == null) {
            return;
        }

        if (App.getInstance().getCurrentPage().containsFeature(request.getFeature())) {
            FeatureCommand featureCommand = FeatureFactory.createFeature(request.getFeature());
            featureCommand.takeAction(request);
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
