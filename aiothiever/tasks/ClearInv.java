package scripts.null_lines.aiothiever.tasks;

import java.util.ArrayList;
import java.util.List;

import org.powerbot.script.Condition;
import org.powerbot.script.Random;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Item;

import scripts.null_lines.aiothiever.NullAIOThiever;

public class ClearInv extends Task {

	private boolean drop = false;
	public List<String> itemsToDrop = new ArrayList();
	public List<String> food = new ArrayList();

	public ClearInv(ClientContext ctx) {
		super(ctx);
	}

	@Override
	public void init() {
		System.out.println("initing in ");
		if (NullAIOThiever.gui.getFoodText().length() > 0) {
			for (String s : NullAIOThiever.gui.items(NullAIOThiever.gui.getFoodText())) {
				this.food.add(s);
			}
		}

		if (NullAIOThiever.gui.getDropString().length() > 0) {
			for (String s : NullAIOThiever.gui.items(NullAIOThiever.gui.getDropString())) {
				this.itemsToDrop.add(s);
			}
		}
	}

	@Override
	public boolean activate() {
		return ctx.inventory.isFull();
	}

	@Override
	public void execute() {

		/*if (cfilter(new String[] { "Diamond" }).isEmpty()) {
			while (true) {
				SimpleItem i = this.ctx.getInventory().populate().filter(new String[] { "Diamond" }).next();
				System.out.println(i);
				if (i == null) {
					break;
				}

				final int freeSlotBefore = this.ctx.getInventory().getFreeSlots();

				this.ctx.getMagic().castSpellOnItem("High Level Alchemy", i.getId());
				this.ctx.sleepCondition(new BooleanSupplier() {
					public boolean getAsBoolean() {
						return (freeSlotBefore < ClearInventory.this.ctx.getInventory().getFreeSlots());
					}
				});
			}
			openInv();
			this.ctx.sleep(1000);
		}* POTENTIAL ALCHING CODE */
		
		if (!ctx.inventory.select().name(listToArray(this.itemsToDrop)).isEmpty()) {
			for (Item i : ctx.inventory) {
				if (!i.valid()) {
					System.out.println("i is null");
					return;
				}
				System.out.println("Sending drop for item");
				i.interact("Drop");
				Condition.sleep(Random.nextInt(0, 1000));
			}

			System.out.println("Out of drop if");
		} else if (!ctx.inventory.select().name(listToArray(this.food)).isEmpty()) {
			System.out.println("In food");
			Item eatItem = ctx.inventory.poll();
			eatItem.interact("Eat");
			Condition.sleep(Random.nextInt(0, 1000));
		}
	}

	@Override
	public String status() {
		return "Clearing inv.";
	}
	
	private String[] listToArray(List<String> l) {
		String[] a = new String[l.size()];
		for (int i = 0; i < a.length; i++) {
			a[i] = (String) l.get(i);
		}
		return a;
	}

}
