package scripts.null_lines.aiothiever;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.powerbot.script.MessageEvent;
import org.powerbot.script.MessageListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;

import scripts.null_lines.aiothiever.tasks.ClearInv;
import scripts.null_lines.aiothiever.tasks.Steal;
import scripts.null_lines.aiothiever.tasks.Task;


@Script.Manifest(name = "NULL AIO Thiever", description = "AIO Thiever developed by Null_Lines")
public class NullAIOThiever extends PollingScript<ClientContext> implements MessageListener {
	
	public static double version = 1.0D;
	public static GUI gui;

	public static int chatmsgcount = 0;
	
	public static List<Task> taskList = new ArrayList<>();
	
	@Override
	public void start() {
		System.out.println("Started NullAIOThiever!");
		gui = new GUI();
		taskList.addAll((Collection<? extends Task>) Arrays.asList(new Steal(ctx), new ClearInv(ctx)));
	}
	@Override
	public void poll() {

		if (gui.start()) {
			for (Task t : taskList) {
				if (t.activate()) {
					System.out.println(t.status());
					t.execute();
				}
			}
		}
	}
	@Override
	public void messaged(MessageEvent arg0) {
		chatmsgcount += 1;
		
	}

}
