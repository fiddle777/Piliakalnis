package Core;

import Actions.BuildWallAction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private final Piliakalnis piliakalnis;
    private final Scanner scanner = new Scanner(System.in);
    private final List<GameAction> allActions = List.of(
            new BuildWallAction()
    );
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
        int oldGold = piliakalnis.gold;
        int oldMorale = piliakalnis.morale;
        int oldFood = piliakalnis.food;
        int oldPopulation = piliakalnis.population;
        int oldDefense = piliakalnis.defense;
        int oldFaith = piliakalnis.faith;
        System.out.println("\tKa norite daryti?");
        String input = scanner.nextLine();
        if(input.equals("0")) {
            return false;
        } else{
            try{
                int choice = Integer.parseInt(input);
                if(choice > 0 && choice <= allActions.size()) {
                    System.out.println("-----DEBUGPasirinkote: " + allActions.get(choice - 1));
                    GameAction actionChoice = allActions.get(choice - 1);
                    if(actionChoice.isAvailable(piliakalnis)) {
                        ActionResult actionResult = actionChoice.execute(piliakalnis);
                        storyLog.add(actionResult.storyText);
                        cls();
                        showTurnSummary(actionResult.storyText, oldGold, oldMorale, oldFood, oldPopulation, oldDefense, oldFaith);
                    } else{
                        System.out.println("NEGALIME ATLIKTI SIO VEIKSMO, VALDOVE!");
                    }
                } else{
                    System.out.println("NETEISINGAS PASIRINKIMAS, VALDOVE!");
                }
            } catch(NumberFormatException e){
                System.out.println("IVESK SKAICIU CIA AS DAUZTAS AR TU AS NESUPRANTU");
            }
        }
        return true;
    }

    private void showTurnSummary(String story, int oldGold, int oldMorale, int oldFood, int oldPopulation, int oldDefense, int oldFaith) {
        System.out.println("-----TURNOS SANTRAUKA KA BLET-----");
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

}
