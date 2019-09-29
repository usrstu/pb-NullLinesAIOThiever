package scripts.null_lines.aiothiever.tasks;

import org.powerbot.script.rt4.ClientAccessor;
import org.powerbot.script.rt4.ClientContext;

public abstract class Task<C extends ClientContext> extends ClientAccessor {
	
	public Task(C ctx) {
		super(ctx);
	}

	public abstract boolean activate();

	public abstract void execute();
	
	public abstract String status();
	
	public abstract void init();
	
}

