package Core;

import Core.GameAction;
import java.util.List;
public class ConsoleUI {
    public void showMainMenu(Piliakalnis piliakalnis) {
        System.out.println("==============================================================");
        System.out.println("                       PILIAKALNIS");
        System.out.println("==============================================================");
        System.out.printf("%dAD, metu valdzioje: %-4d%n",
                piliakalnis.year, piliakalnis.yearsOfRule);
        System.out.println("--------------------------------------------------------------");
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
        System.out.println("--------------------------------------------------------------");
        System.out.println("STATINIAI:");
        System.out.printf("Fortifikacijos: %-2d   Ukis/gardas: %-2d   Aukuras: %-2d   Turgus: %-2d%n",
                piliakalnis.fortLevel, piliakalnis.farmLevel,
                piliakalnis.altarLevel, piliakalnis.marketLevel);
        System.out.println("==============================================================");
    }
    public void showActionsMenu(List<GameAction> allActions, Piliakalnis piliakalnis) {
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
    public void printTurnHeader(Piliakalnis piliakalnis) {
        System.out.println("==================================================");
        System.out.println("            PILIAKALNIS - " + piliakalnis.year + " AD");
        System.out.println("==================================================");
        System.out.println("Metai Valdzioje: " + piliakalnis.yearsOfRule);
        System.out.println();
    }
    public void printResourceDiffs(Piliakalnis piliakalnis, int oldGold, int oldMorale, int oldFood,
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
    public void printStoryBlock(String story) {
        if (story == null || story.isEmpty()) {
            return;
        }
        System.out.println("NAUJIENOS:");
        System.out.println("--------------------------------------------------");
        System.out.println(story);
        System.out.println("--------------------------------------------------");
    }
    public void showGameOverScreen(Piliakalnis piliakalnis, String gameOverMessage) {
        GameController.cls();
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
    }
    public void showIntro() {
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
    }
}
