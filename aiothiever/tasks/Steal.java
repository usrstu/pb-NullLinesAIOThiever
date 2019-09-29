package scripts.null_lines.aiothiever.tasks;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;
import org.powerbot.script.rt4.Npc;

import scripts.null_lines.aiothiever.NullAIOThiever;

public class Steal extends Task {

	public String npcName;

	public Steal(ClientContext ctx) { super(ctx); }

	@Override
	public void init() {
		System.out.println("initing");
		this.npcName = NullAIOThiever.gui.getNames();
	}
	
	@Override
	public boolean activate() {
		return !this.ctx.npcs.select().name(this.npcName).isEmpty();
	}

	@Override
	public void execute() {
		Npc npc = null;

		if (ctx.players.local().healthPercent() <= NullAIOThiever.gui.eatValue()
				&& !NullAIOThiever.gui.getFoodText().equalsIgnoreCase("")) {
			Item food = ctx.inventory.select().name(new String[] { NullAIOThiever.gui.getFoodText() }).poll();
			food.click(1);
			Condition.wait(() -> food.valid());
		}
		
	    if (ctx.inventory.select().name("Coin pouch").count(true) >= 28) {
	    	ctx.inventory.poll().click(1);
	    	Condition.sleep(Random.nextInt(0, 1000));
	    } else if (!ctx.npcs.select().name(this.npcName).isEmpty()) {
			if (ctx.inventory.count() <= 27) {
				npc = ctx.npcs.poll();
				if (!npc.valid()) {return;}
				System.out.println("Can click");
				int chatBefore = NullAIOThiever.chatmsgcount;
				if (npc.valid()) {
					npc.interact("Pickpocket");
					Condition.wait(() -> chatBefore + 1 < NullAIOThiever.chatmsgcount);
				} else {
					System.out.println("Error with npc");
				}
			}
		} else {
			System.out.println("No condition is true.");
		}
	}
	
	@Override
	public String status() {
		return "Stealing";
	}
}