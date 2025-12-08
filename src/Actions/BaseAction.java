package Actions;

import Core.ActionResult;
import Core.GameAction;
import Core.Piliakalnis;

public abstract class BaseAction implements GameAction {
    private final String name;
    private final String category1;
    private final String category2;
    private final String description;
    private final String costDescription;
    private final String requirementDescription;

    protected BaseAction(String name,
                         String category1,
                         String category2,
                         String description,
                         String costDescription,
                         String requirementDescription) {
        this.name = name;
        this.category1 = category1;
        this.category2 = category2;
        this.description = description;
        this.costDescription = costDescription;
        this.requirementDescription = requirementDescription;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory1() {
        return category1;
    }

    @Override
    public String getCategory2() {
        return category2;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public String getCostDescription() {
        return costDescription;
    }

    @Override
    public String getRequirementDescription() {
        return requirementDescription;
    }

    @Override
    public abstract boolean isAvailable(Piliakalnis p);

    @Override
    public abstract ActionResult execute(Piliakalnis p);
}
