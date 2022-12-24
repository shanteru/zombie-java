package game;

import java.util.Objects;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.PickUpItemAction;

public class PickFreezeItemAction extends PickUpItemAction{

	public PickFreezeItemAction(Corpse corpse) {
		super(corpse);
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Objects.requireNonNull(actor, "Expected an actor parameter, yet null is given");
		Objects.requireNonNull(map, "Expected a map parameter, yet null is given");
		((Corpse) super.item).setFreeze(true);
		return super.execute(actor, map);
	}
	
}
