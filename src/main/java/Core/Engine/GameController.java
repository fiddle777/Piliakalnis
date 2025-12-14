package Core.Engine;

import Actions.Action.*;
import Actions.Build.BuildAltar;
import Actions.Build.BuildFarmstead;
import Actions.Build.BuildFort;
import Actions.Build.BuildMarket;
import Core.*;
import Core.Results.ActionResult;
import Core.Results.EventResult;
import Core.UI.ConsoleUI;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private final Piliakalnis piliakalnis;
    private final Scanner scanner = new Scanner(System.in);
    private final List<GameAction> allActions;
    private final EventManager eventManager = new EventManager();
    private final List<String> storyLog = new ArrayList<>();
    private final ConsoleUI ui = new ConsoleUI();

    public GameController(Piliakalnis piliakalnis) {
        this.piliakalnis = piliakalnis;
        this.allActions = new ActionFactory().createDefaultActions();
    }

    public void startGame() {
        ui.showIntro();
        waitForEnter();
        boolean running = true;
        while (running) {
            cls();
            ui.showMainMenu(piliakalnis);
            ui.showActionsMenu(allActions, piliakalnis);
            running = handleUserInput();
            if (!running) {
                cls();
                System.out.println("YOU HAVE BEEN TERMINATED.");
                break;
            }
        }
    }
    public static void cls() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }
    private boolean handleUserInput() {
        System.out.println("\tKa norite daryti?");
        String input = scanner.nextLine();
        if ("0".equals(input)) {
            return false;
        }
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("IVESK SKAICIU");
            return true;
        }
        if (choice < 1 || choice > allActions.size()) {
            System.out.println("NETEISINGAS PASIRINKIMAS, VALDOVE!");
            return true;
        }
        GameAction action = allActions.get(choice - 1);
        if (!action.isAvailable(piliakalnis)) {
            System.out.println("NEGALIME ATLIKTI SIO VEIKSMO, VALDOVE!");
            return true;
        }
        processTurn(action);
        return true;
    }
    private void processTurn(GameAction action) {
        int oldGold = piliakalnis.getGold();
        int oldMorale = piliakalnis.getMorale();
        int oldFood = piliakalnis.getFood();
        int oldPopulation = piliakalnis.getPopulation();
        int oldDefense = piliakalnis.getDefense();
        int oldFaith = piliakalnis.getFaith();

        ActionResult actionResult = action.execute(piliakalnis);
        List<EventResult> eventResults = eventManager.rollEvents(piliakalnis);
        TurnOutcome outcome = buildTurnOutcome(actionResult, eventResults);

        cls();
        showTurnSummary(outcome.story, oldGold, oldMorale, oldFood,
                oldPopulation, oldDefense, oldFaith);

        if (outcome.gameOver) {
            ui.showGameOverScreen(piliakalnis, outcome.gameOverMessage);
            waitForEnter();
            System.exit(0);
        }
    }
    private TurnOutcome buildTurnOutcome(ActionResult actionResult, List<EventResult> eventResults) {
        boolean gameOver = false;
        String gameOverMessage = null;
        StringBuilder sb = new StringBuilder();
        if (actionResult != null && actionResult.storyText != null && !actionResult.storyText.isEmpty()) {
            sb.append(actionResult.storyText);
            storyLog.add(actionResult.storyText);
        }
        if (eventResults != null) {
            for (EventResult eventResult : eventResults) {
                String text = eventResult.getStoryText();
                if (eventResult.isGameOver()) {
                    gameOver = true;
                    if (text != null && !text.isEmpty()) {
                        gameOverMessage = text;
                    }
                    continue;
                }
                if (text != null && !text.isEmpty()) {
                    if (!sb.isEmpty()) {
                        sb.append("\n\n");
                    }
                    sb.append(text);
                    storyLog.add(text);
                }
            }
        }
        return new TurnOutcome(sb.toString(), gameOver, gameOverMessage);
    }
    private void showTurnSummary(String story, int oldGold, int oldMorale, int oldFood,
                                 int oldPopulation, int oldDefense, int oldFaith) {
        ui.printTurnHeader(piliakalnis);
        ui.printResourceDiffs(piliakalnis, oldGold, oldMorale, oldFood,
                oldPopulation, oldDefense, oldFaith);
        ui.printStoryBlock(story);
        waitForEnter();
    }
    private void waitForEnter() {
        System.out.println("Spauskite ENTER, kad testi...");
        scanner.nextLine();
    }
    private static class TurnOutcome {
        private final String story;
        private final boolean gameOver;
        private final String gameOverMessage;

        private TurnOutcome(String story, boolean gameOver, String gameOverMessage) {
            this.story = story;
            this.gameOver = gameOver;
            this.gameOverMessage = gameOverMessage;
        }
    }
}