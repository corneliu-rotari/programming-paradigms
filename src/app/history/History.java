package app.history;

import app.App;
import app.pages.PageFactory;
import app.strategies.strategy.ChangePageStrategy;
import io.input.action.Request;
import io.output.Output;
import io.output.response.Response;

import java.util.LinkedList;

public class History {
    private LinkedList<Request> historyStack;

    public History() {
        this.historyStack = new LinkedList<>();
        Request home = new Request();
        home.setPage(PageFactory.PageType.HOME.toString());
        add(home);
    }

    public void undo() {
        if (this.historyStack.size() > 1) {
            printHistory(this.historyStack.pop(), " -> ");
            App.getInstance().setStrategy(new ChangePageStrategy(this.historyStack.peek()));
            App.getInstance().applyStrategy();
        } else {
            Output.getInstance().addToTree(new Response.Builder().fail().build());
        }
    }

    public void add(final Request request) {
        if (historyStack.size() != 0 && request.getPage().equals(historyStack.peek().getPage())) {
            return;
        }

        printHistory(request, " <- ");
        this.historyStack.push(request);
    }

    public void printHistory(Request requestToCp, String mes) {
        System.out.print("[ " +requestToCp.getPage() + " ]" + mes);
        this.historyStack.forEach(request -> System.out.print(request.getPage() + " <- "));
        System.out.println();
    }

    public int size() {
        return this.historyStack.size();
    }

    public Request peek() {
        return this.historyStack.peek();
    }
}
