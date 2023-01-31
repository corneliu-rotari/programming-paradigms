package app.strategies.strategy;

import app.App;
import components.pages.PageFactory;
import app.strategies.Strategy;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

public final class ChangePageStrategy extends Strategy {
    public ChangePageStrategy(final Request request) {
        super(request);
    }

    /**
     * Goes to the next allowed page or outputs an error
     */
    @Override
    public void execute() {
        if (request == null) {
            return;
        }

        App app = App.getInstance();

        if (app.getCurrentPage().containsNextPage(request.getPage())) {
            app.setCurrentPage(PageFactory.createPage(request));
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
