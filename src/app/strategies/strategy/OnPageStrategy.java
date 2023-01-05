package app.strategies.strategy;

import app.App;
import app.features.FeatureCommand;
import app.features.FeatureFactory;
import app.strategies.Strategy;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

public final class OnPageStrategy extends Strategy {

    public OnPageStrategy(final Request request) {
        super(request);
    }

    /**
     * Executes the features that are allowed on a specific page.
     * If the feature is not accessible outputs an error.
     * {@link FeatureCommand#doCommand(Request)}
     * {@link FeatureFactory#createFeature(String)}
     * {@link Output}
     */
    @Override
    public void execute() {
        if (request == null) {
            return;
        }

        if (App.getInstance().getCurrentPage().containsFeature(request.getFeature())) {
            FeatureCommand featureCommand = FeatureFactory.createFeature(request.getFeature());
            featureCommand.doCommand(request);
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
