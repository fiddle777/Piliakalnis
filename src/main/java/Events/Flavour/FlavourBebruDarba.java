package Events.Flavour;

import Core.Results.EventResult;
import Core.Piliakalnis;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FlavourBebruDarba extends BaseFlavourEvent {
    private static final int MIN_POPULATION = 20;
    private static final int MORALE_LOSS_SMALL = 1;
    private static final int MORALE_LOSS_BIG = 5;
    private static final int CHANCE_PERCENT = 20;
    private static final int VARIANT_COUNT = 2;

    private final Random rnd = new Random();

    public FlavourBebruDarba() {
        super("Bebrai nugvelbia statybines medziagas", CHANCE_PERCENT);
    }

    @Override
    public boolean canTrigger(Piliakalnis p) {
        return p.getPopulation() >= MIN_POPULATION;
    }

    @Override
    public EventResult execute(Piliakalnis p) {
        List<Target> candidates = collectCandidates(p);
        if (candidates.isEmpty()) {
            return new EventResult(noBuildText());
        }

        Target target = candidates.get(rnd.nextInt(candidates.size()));
        int variant = rnd.nextInt(VARIANT_COUNT);

        if (isSmallVariant(variant)) {
            applySmallVariant(p, target);
            return new EventResult(smallText(target));
        } else {
            applyBigVariant(p);
            return new EventResult(bigText(target));
        }
    }

    private List<Target> collectCandidates(Piliakalnis p) {
        List<Target> candidates = new ArrayList<>();
        if (p.getFortLevel() > 0) candidates.add(Target.FORT);
        if (p.getFarmLevel() > 0) candidates.add(Target.FARM);
        if (p.getAltarLevel() > 0) candidates.add(Target.ALTAR);
        if (p.getMarketLevel() > 0) candidates.add(Target.MARKET);
        return candidates;
    }

    private boolean isSmallVariant(int variant) {
        return variant == 0;
    }

    private void applySmallVariant(Piliakalnis p, Target target) {
        downgradeOneLevel(p, target);
        changeMorale(p, -MORALE_LOSS_SMALL);
    }

    private void applyBigVariant(Piliakalnis p) {
        changeMorale(p, -MORALE_LOSS_BIG);
    }

    private void downgradeOneLevel(Piliakalnis p, Target target) {
        switch (target) {
            case FORT -> p.setFortLevel(p.getFortLevel() - 1);
            case FARM -> p.setFarmLevel(p.getFarmLevel() - 1);
            case ALTAR -> p.setAltarLevel(p.getAltarLevel() - 1);
            case MARKET -> p.setMarketLevel(p.getMarketLevel() - 1);
        }
    }

    private String noBuildText() {
        return "Bebru burys pastebejo sukrauta mediena, bet rimtesniu statybu dar nematyti. \n" +
                "Jie kiek pasikapsto po krumus ir nuplaukia toliau.";
    }

    private String smallText(Target target) {
        return "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                "Dalies " + target.affectedName + " darbai visiskai suzlugdomi - viskas turi prasideti is naujo.\n" +
                "Statybos sustoja, amatininkai burnoja.";
    }

    private String bigText(Target target) {
        return "Bebru burys pastebejo sukrauta mediena statyboms ir ja issinese.\n" +
                "Darbininkai visa diena vaikosi zverelius aplink,\n" +
                target.affectedName + " statybos veluoja," +
                "amatininkai burnoja.";
    }

    private enum Target {
        FORT("fortifikaciju"),
        FARM("ukio pastatu"),
        ALTAR("sventvietes"),
        MARKET("turgaus ir sandeliu");

        private final String affectedName;

        Target(String affectedName) {
            this.affectedName = affectedName;
        }
    }
}
