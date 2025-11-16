package Core;

import Actions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private final Piliakalnis piliakalnis;
    private final Scanner scanner = new Scanner(System.in);
    private final List<GameAction> allActions = List.of(
            new Action_Action_Hunt(),
            new Action_Action_HoldFeast(),
            new Action_Action_InviteSettlers(),
            new Action_Action_LevyTaxes(),
            new Action_Action_PerformRitual(),
            new Action_Build_Fort(),
            new Action_Build_Farmstead(),
            new Action_Build_Altar(),
            new Action_Build_Market()
    );
    private final EventManager eventManager = new EventManager();
    private final List<String> storyLog = new ArrayList<>();
    public GameController(Piliakalnis piliakalnis) {
        this.piliakalnis = piliakalnis;
    }
    public void startGame() {
        showIntro();
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
        System.out.println("                     PILIAKALNIS");
        System.out.println("==================================================");
        System.out.printf("%dAD, metu valdzioje: %-4d%n",
                piliakalnis.year, piliakalnis.yearsOfRule);
        System.out.println("--------------------------------------------------");
        System.out.printf("%-12s %4d   %-12s %4d   %-12s %4d%n",
                "Gyventoju:", piliakalnis.population,
                "Auksas:",    piliakalnis.gold,
                "Maistas:",   piliakalnis.food
        );
        System.out.printf("%-12s %4d   %-12s %4d   %-12s %4d%n",
                "Gynyba:",    piliakalnis.defense,
                "Tikejimas:", piliakalnis.faith,
                "Morale:",    piliakalnis.morale
        );
        System.out.println("--------------------------------------------------");
        System.out.println("STATINIAI:");
        System.out.printf("Fortifikacijos: %-2d   Ukis/gardas: %-2d   Aukuras: %-2d   Turgus: %-2d%n",
                piliakalnis.fortLevel, piliakalnis.farmLevel,
                piliakalnis.altarLevel, piliakalnis.marketLevel);
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
        for (int i = 0; i < allActions.size(); i++) {
            GameAction action = allActions.get(i);
            boolean available = action.isAvailable(piliakalnis);
            String availabilityMark = available ? "" : " (NEGALIMA)";
            System.out.printf(
                    "%2d) %-28s [%s / %s]%s%n",
                    i + 1,
                    action.getName(),
                    action.getCategory1(),
                    action.getCategory2(),
                    availabilityMark
            );
            String cost = action.getCostDescription();
            String req  = action.getRequirementDescription();
            boolean hasCost = cost != null && !cost.isEmpty();
            boolean hasReq  = req != null && !req.isEmpty();
            if (hasCost && hasReq) {
                System.out.printf("    %-30s | R: %s%n", "K: " + cost, req);
            } else if (hasCost) {
                System.out.printf("    %-30s%n", "K: " + cost);
            } else if (hasReq) {
                System.out.printf("    %-30s%n", "R: " + req);
            }
            System.out.println();
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
                        stringBuilder.append("\n\n");
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
        System.out.printf("Auksas:     %4d - %4d(%d)%n", oldGold, piliakalnis.gold, piliakalnis.gold - oldGold);
        System.out.printf("Maistas:    %4d - %4d(%d)%n", oldFood, piliakalnis.food, piliakalnis.food - oldFood);
        System.out.printf("Gynyba:     %4d - %4d(%d)%n", oldDefense, piliakalnis.defense, piliakalnis.defense - oldDefense);
        System.out.printf("Tikejimas:  %4d - %4d(%d)%n", oldFaith, piliakalnis.faith, piliakalnis.faith - oldFaith);
        System.out.printf("Morale:     %4d - %4d(%d)%n", oldMorale, piliakalnis.morale, piliakalnis.morale - oldMorale);
        System.out.printf("Zmones:     %4d - %4d(%d)%n", oldPopulation, piliakalnis.population, piliakalnis.population - oldPopulation);
        System.out.println("--------------------------------------------------");
    }

    private void printStoryBlock(String story) {
        if (story == null || story.isEmpty()) return;
        System.out.println("NAUJIENOS:");
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

    private void showIntro() {
        System.out.println("==================================================");
        System.out.println("                 PILIAKALNIS");
        System.out.println("==================================================");
        System.out.println("Esate nedidelio baltu piliakalnio vadas.");
        System.out.println("Turite valdyti auksa, maista, gyventojus, gynyba, tikejima ir morale.");
        System.out.println();
        System.out.println("Jei auksas, maistas ar morale nukrenta iki 0 - jusu valdzia zlunga.");
        System.out.println("Jei islaikote piliakalni daug metu - jusu valdymas laikomas sekmingu.");
        System.out.println();
        System.out.println("Kiekviename ejime pasirenkate veiksma: statyti, rinkti maista,");
        System.out.println("kelti morale, stiprinti gynyba, kvieti naujakurius ir pan.");
        System.out.println("==================================================");
        System.out.println("Spauskite ENTER, jei norite pradeti...");
        scanner.nextLine();
    }
}