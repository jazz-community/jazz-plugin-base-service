package com.siemens.bt.jazz.services.base.rest;

/**
 * Interface for the command pattern of calling actions.
 *
 * <p>The command pattern is described with this name by the Gang of Four.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Command_pattern">Wikipedia: Command Pattern</a>
 */
public interface RestAction {
  /**
   * Calls the preparation hook of the service. This step is called before execute and provides a
   * hook where optional configurations can be applied to an action before executing it.
   */
  void prepare();

  /**
   * Executes the implemented action.
   *
   * @throws Exception Any exception that occurs while executing a service action.
   */
  void execute() throws Exception;

  /** Optional post-execution hook for actions that are run after the execute phase. */
  void cleanUp();
}
