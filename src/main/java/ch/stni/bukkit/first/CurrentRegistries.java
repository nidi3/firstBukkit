package ch.stni.bukkit.first;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.World;
import org.bukkit.plugin.Plugin;


/**
 * Created by IntelliJ IDEA.
 * User: nidi
 * Date: 02.10.11
 * Time: 23:25
 * To change this template use File | Settings | File Templates.
 */
public class CurrentRegistries {
    private final Map<String, CurrentRegistry> registries = new HashMap<String, CurrentRegistry>();
    private final Plugin plugin;

    public CurrentRegistries(Plugin plugin) {
        this.plugin = plugin;
    }

    public void init(List<World> worlds) {
        for (World world : worlds) {
            getRegistry(world);
        }
    }

    public CurrentRegistry getRegistry(World world) {
        CurrentRegistry registry = registries.get(world.getName());
        if (registry == null) {
            registry = new CurrentRegistry(world, plugin.getDataFolder());
            try {
                registry.load(plugin, world.getName());
            } catch (IOException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            registries.put(world.getName(), registry);
        }
        return registry;
    }

    public void saveAll() throws IOException {
        for (Map.Entry<String, CurrentRegistry> entry : registries.entrySet()) {
            entry.getValue().save(entry.getKey());
        }
    }
}
