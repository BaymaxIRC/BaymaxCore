//package baymaxirc.coremodule;
//
//import baymaxirc.core.Baymax;
//import baymaxirc.core.event.IEventHandler;
//import baymaxirc.core.module.IModule;
//
///**
// * @author shadowfacts
// */
//public class BaymaxCoreModule implements IModule {
//
//	@Override
//	public String getName() {
//		return "CoreModule";
//	}
//
//	@Override
//	public void registerCommands() {
//		Baymax.commandManager.registerCommand(CommandSay.instance);
//	}
//
//	@Override
//	public IEventHandler getEventHandler() {
//		return new MyEventHandler();
//	}
//}
