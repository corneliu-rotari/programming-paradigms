package app.history;

import app.App;
import components.pages.PageFactory;
import app.strategies.strategy.ChangePageStrategy;
import io.input.request.Request;
import io.output.Output;
import io.output.response.Response;

import java.util.LinkedList;

/**
 * History of the visited pages.
 * Extends LinkedList and is used as a Stack.
 * {@link LinkedList}
 */
public final class History extends LinkedList<Request> {

    public History() {
        super();
        Request home = new Request();
        home.setPage(PageFactory.PageType.HOME.toString());
        add(home);
    }

    /**
     * Pops the last visited page and recreates it in the application.
     * If it trys to undo after it reached the first page (Home) it outputs an error.
     * {@link Output}
     * {@link App#applyStrategy()}
     */
    public void undo() {
        if (this.size() > 1) {
            this.pop();
            App.getInstance().setStrategy(new ChangePageStrategy(this.peek()));
            App.getInstance().applyStrategy();
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }

    /**
     * Adds a new ChangePage request to the history stack.
     * It checks not to store the exact the same attempts.
     * {@link Request}
     * {@link LinkedList#push(Object)}
     * @param request Request object
     */
    public void addToHistory(final Request request) {
        if (size() != 0 && request.getPage().equals(this.peek().getPage())) {
            return;
        }
        this.push(request);
    }
}
