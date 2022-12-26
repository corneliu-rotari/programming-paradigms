package app.strategies.strategy;

import app.App;
import app.pages.PageFactory;
import app.strategies.Strategy;
import io.input.action.Request;
import io.output.Output;
import io.output.response.Response;

public class ChangePageStrategy extends Strategy {
    public ChangePageStrategy(Request request) {
        super(request);
    }

    /**
     * Goes to the next allowed page or outputs an error
     */
    @Override
    public void execute() {
        App app = App.getInstance();
        if (app.getCurrentPage().containsNextPage(request.getPage())) {
            app.setCurrentPage(PageFactory.createPage(request));
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }
}
