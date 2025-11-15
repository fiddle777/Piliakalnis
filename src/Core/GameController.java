package Core;

import Actions.Action_Build_BuildWall;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private final Piliakalnis piliakalnis;
    private final Scanner scanner = new Scanner(System.in);
    private final List<GameAction> allActions = List.of(
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
        System.out.println("Piliakalnis_Name"); // Replace with actual name if available
        System.out.println(piliakalnis.year + "AD, " + piliakalnis.yearsOfRule + " metu valdzioje\n" +
                "Gyventojai: " + piliakalnis.population + "|| " +
                "Turtas: " + piliakalnis.gold + " || " +
                "Maistas: " + piliakalnis.food + " || " +
                "Gynyba: " + piliakalnis.defense + " || " +
                "Tikejimas: " + piliakalnis.faith + " || " +
                "Pavaldiniu valia: " + piliakalnis.morale);
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
        System.out.println("\tGalimi veiksmai:");
        for (int i = 0; i < allActions.size(); i++) {
            GameAction gameAction = allActions.get(i);
            if(gameAction.isAvailable(piliakalnis)) {
                System.out.println((i + 1) + "] " + gameAction.getName());
            }
        }
        System.out.println("0] Baigti zaidima");
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
    private void showTurnSummary(String story, int oldGold, int oldMorale, int oldFood, int oldPopulation, int oldDefense, int oldFaith) {
        System.out.println("-----IVYKIAI OR SOME ISH-----");
        System.out.println("Metai: " + piliakalnis.year);
        System.out.println("Auksas: " + oldGold + " -> " + piliakalnis.gold);
        System.out.println("Valia:  " + oldMorale + " -> " + piliakalnis.morale);
        System.out.println("Gynyba: " + oldDefense + " -> " + piliakalnis.defense);
        System.out.println("Tikėjimas: " + oldFaith + " -> " + piliakalnis.faith);
        System.out.println("Maistas: " + oldFood + " -> " + piliakalnis.food);
        System.out.println("--------------------------");
        if(story != null && !story.isEmpty()) {
            System.out.println("\nISTORIJA: ");
            System.out.println(story);
        }
        System.out.println("PRESSANYKEYTOCONTINUE...");
        scanner.nextLine();
    }

    private void processTurn(GameAction action) {

        // snapshot old stats
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

        // build final combined turn story
        String story = buildTurnStory(actionResult, eventResults);

        // print turn summary
        cls();
        showTurnSummary(story, oldGold, oldMorale, oldFood, oldPopulation, oldDefense, oldFaith);
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
}
