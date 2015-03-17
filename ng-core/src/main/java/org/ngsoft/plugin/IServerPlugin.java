package org.ngsoft.plugin;

/**
 * Created by will on 2015-3-16.
 */
public interface IServerPlugin {

    void init();

    void start();

    void stop();

    void reload();
}
