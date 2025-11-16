package Core;

import Actions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private final Piliakalnis piliakalnis;
    private final Scanner scanner = new Scanner(System.in);
    private final List<GameAction> allActions = List.of(
            new Action_Action_HarvestCrops(),
            new Action_Action_HoldFeast(),
            new Action_Action_InviteSettlers(),
            new Action_Action_LevyTaxes(),
            new Action_Action_PerformRitual(),
            new Action_Build_BuildWall()
    );
    private final EventManager eventManager = new EventManager();
    private final List<String> storyLog = new ArrayList<>();
    public GameController(Piliakalnis piliakalnis) {
        this.piliakalnis = piliakalnis;
    }
    public void startGame() {
        boolean running = true;
        while (running) {
            cls();
            showMainMenu();
            showActionsMenu();
            running = getUserInput();
            if(!running) {
                cls();
                System.out.println("YOU HAVE BEEN TERMINATED.");
                break;
            }
        }
    }
    private void showMainMenu() {
        System.out.println("==================================================");
        System.out.println("                 PILIAKALNIS");
        System.out.println("==================================================");
        System.out.println("Metai: " + piliakalnis.year + " AD");
        System.out.println("Metai Valdzioje: " + piliakalnis.yearsOfRule);
        System.out.println("--------------------------------------------------");

        System.out.printf("Gyventojai:     %4d%n", piliakalnis.population);
        System.out.printf("Auksas:         %4d%n", piliakalnis.gold);
        System.out.printf("Maistas:        %4d%n", piliakalnis.food);
        System.out.printf("Gynyba:         %4d%n", piliakalnis.defense);
        System.out.printf("Tikejimas:      %4d%n", piliakalnis.faith);
        System.out.printf("Morale:  %4d%n", piliakalnis.morale);

        System.out.println("==================================================");
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
            // fallback
            System.out.println("\n\n\n\n\n\n\n\n\n\n");
        }
    }
    private void showActionsMenu() {
        System.out.println("GALIMI VEIKSMAI:");
        System.out.println("--------------------------------------------------");
        for(int i = 0; i < allActions.size(); i++) {
            GameAction action = allActions.get(i);
            boolean available = action.isAvailable(piliakalnis);
            String availabilityMark = available ? "" : " (NEGALIMA)";
            System.out.printf("%d) %-30s [%s / %s]%s%n",
                    i + 1,
                    action.getName(),
                    action.getCategory1(),
                    action.getCategory2(),
                    availabilityMark
            );
            if(action.getCostDescription() != null && !action.getCostDescription().isEmpty()) {
                System.out.printf("    Kaina: %s%n", action.getCostDescription());
            }
            if(action.getRequirementDescription() != null && !action.getRequirementDescription().isEmpty()){
                System.out.printf("    Reikalavimai: %s%n", action.getRequirementDescription());
            }
            System.out.println("--------------------------------------------------");
        }
        System.out.println("0) Baigti zaidima");
    }
    private boolean getUserInput() {

        System.out.println("\tKa norite daryti?");
        String input = scanner.nextLine();

        if (input.equals("0")) return false;

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
    private void showTurnSummary(String story, int oldGold, int oldMorale, int oldFood,
                                 int oldPopulation, int oldDefense, int oldFaith) {

        printTurnHeader();
        printResourceDiffs(oldGold, oldMorale, oldFood, oldPopulation, oldDefense, oldFaith);
        printStoryBlock(story);

        System.out.println("Spauskite ENTER, kad testi...");
        scanner.nextLine();
    }
    private void processTurn(GameAction action) {
        int oldGold = piliakalnis.gold;
        int oldMorale = piliakalnis.morale;
        int oldFood = piliakalnis.food;
        int oldPopulation = piliakalnis.population;
        int oldDefense = piliakalnis.defense;
        int oldFaith = piliakalnis.faith;

        // execute main action of the turn
        ActionResult actionResult = action.execute(piliakalnis);

        // roll events
        List<EventResult> eventResults = eventManager.rollEvents(piliakalnis);

        boolean gameOver = false;
        String gameOverMessage = null;

        // build final combined turn story + detect gameover
        StringBuilder stringBuilder = new StringBuilder();
        if(actionResult != null && actionResult.storyText != null && !actionResult.storyText.isEmpty()) {
            stringBuilder.append(actionResult.storyText);
            storyLog.add(actionResult.storyText);
        }
        if(eventResults != null) {
            for(EventResult eventResult : eventResults) {
                String text = eventResult.getStoryText();
                if(eventResult.isGameOver()) {
                    gameOver = true;
                    if(text != null && !text.isEmpty()) {
                        gameOverMessage = text;
                    }
                    continue;
                }
                if(text != null && !text.isEmpty()) {
                    if(!stringBuilder.isEmpty()) {
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append(text);
                    storyLog.add(text);
                }
            }
        }
        String story = stringBuilder.toString();

        // print turn summary
        cls();
        showTurnSummary(story, oldGold, oldMorale, oldFood, oldPopulation, oldDefense, oldFaith);

        if(gameOver) {
            showGameOverScreen(gameOverMessage);
            System.exit(0);
        }
    }
    private String buildTurnStory(ActionResult actionResult, List<EventResult> eventResults) {
        StringBuilder sb = new StringBuilder();

        // main action story
        if (actionResult != null && actionResult.storyText != null) {
            sb.append(actionResult.storyText);
            storyLog.add(actionResult.storyText);
        }

        // events story
        if (eventResults != null) {
            for (EventResult er : eventResults) {
                sb.append("\n").append(er.getStoryText());
                storyLog.add(er.getStoryText());
            }
        }

        return sb.toString();
    }
    private void printTurnHeader() {
        System.out.println("==================================================");
        System.out.println("            PILIAKALNIS - " + piliakalnis.year + " AD");
        System.out.println("==================================================");
        System.out.println("Metai Valdzioje: " + piliakalnis.yearsOfRule);
        System.out.println();
    }
    private void printResourceDiffs(int oldGold, int oldMorale, int oldFood,
                                    int oldPopulation, int oldDefense, int oldFaith) {

        System.out.println("POKYCIAI:");
        System.out.println("--------------------------------------------------");
        System.out.printf("Auksas:     %4d - %4d%n", oldGold, piliakalnis.gold);
        System.out.printf("Maistas:    %4d - %4d%n", oldFood, piliakalnis.food);
        System.out.printf("Gynyba:     %4d - %4d%n", oldDefense, piliakalnis.defense);
        System.out.printf("Tikejimas:  %4d - %4d%n", oldFaith, piliakalnis.faith);
        System.out.printf("Morale:      %4d - %4d%n", oldMorale, piliakalnis.morale);
        System.out.printf("Zmones:     %4d - %4d%n", oldPopulation, piliakalnis.population);
        System.out.println("--------------------------------------------------");
    }

    private void printStoryBlock(String story) {
        if (story == null || story.isEmpty()) return;

        System.out.println("\nIVYKIAI:");
        System.out.println("--------------------------------------------------");
        System.out.println(story);
        System.out.println("--------------------------------------------------");
    }

    private void showGameOverScreen(String gameOverMessage) {
        cls();
        System.out.println("==================================================");
        System.out.println("            ZAIDIMO PABAIGA");
        System.out.println("==================================================");
        System.out.println("Metai: " + piliakalnis.year + " AD");
        System.out.println("Metai valdzioje: " + piliakalnis.yearsOfRule);
        System.out.println();
        if (gameOverMessage != null && !gameOverMessage.isEmpty()) {
            System.out.println(gameOverMessage);
        } else {
            System.out.println("Jusu valdzia baige savo egzistavima.");
        }
        System.out.println("==================================================");
        System.out.println("\nSpauskite ENTER, kad iseitumete...");
        scanner.nextLine();
    }
}