package com.siemens.bt.jazz.services.base.test.rest;


/**
 * <p>Interface for the command pattern of calling actions.</p>
 * <p>
 * <p>The command pattern is described with this name by the Gang of Four.</p>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Command_pattern">Wikipedia: Command Pattern</a>
 */
public interface RestAction {
    /**
     * Executes the implemented action.
     *
     * @throws Exception Any exception that occurs while executing a service action.
     */
    void execute() throws Exception;
}
