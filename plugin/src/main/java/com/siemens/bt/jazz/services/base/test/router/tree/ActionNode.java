package com.siemens.bt.jazz.services.base.test.router.tree;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.siemens.bt.jazz.services.base.test.router.factory.ServiceFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Tree node used by ActionTree to represent a navigable node in the tree.
 * <p>
 * <p>It's usually sufficient to build a tree from nodes and not have separate classes. However, the very specific
 * functionality used by the ActionTree makes it easier if the classes are separated, as the insertion and retrieval
 * logic is fairly complex. This keeps the classes smaller and easier to handle.</p>
 * <p>
 * <p>Every node can have children, but also actions. Considered theoretically, this results in a multidimensional tree,
 * as nodes allow N number of children, and also N actions. To simplify this, methods and children are separate entities
 * even though this would make drawing and imagining the tree more complicated.</p>
 */
public class ActionNode {
    private final Map<String, ActionNode> children = new HashMap<>();
    private final Map<HttpMethod, ServiceFactory> factories = new HashMap<>();

    private final String path;

    /**
     * Creates an ActionNode with a method, a path to that node, and a builder class.
     *
     * @param method  An HTTP method
     * @param path    Full path to a service: {@code /path/to/service}
     * @param factory ServiceFactory for creating rest actions
     */
    public ActionNode(HttpMethod method, String path, ServiceFactory factory) {
        this(path, factory);
        this.factories.put(method, factory);
    }

    /**
     * Creates an ActionNode with only default actions for every method.
     * <p>
     * <p>This constructor is used to create a "dummy" node, which is required when a node is required for navigation,
     * but doesn't have an action attached to it. An example for this would be services {@code A/B, A/C, A/D}, where A
     * itself doesn't have an action. That node would only be used to reach B, C or D respectively.</p>
     *
     * @param path Full path to this node
     */
    public ActionNode(String path, ServiceFactory defaultFactory) {
        this.path = path;

        for (HttpMethod httpMethod : HttpMethod.values()) {
            this.factories.put(httpMethod, defaultFactory);
        }
    }

    /**
     * Returns the action builder assigned to this node, corresponding to the passed method.
     *
     * @param method HTTP method
     * @return Action builder corresponding to the passed method
     */
    public ServiceFactory getActionBuilder(HttpMethod method) {
        return this.factories.get(method);
    }

    /**
     * Checks if this node has a child corresponding to a passed path fragment.
     *
     * @param path Fragment to check for
     * @return Child node if found, null otherwise
     */
    public boolean hasChild(String path) {
        return this.children.containsKey(path);
    }

    /**
     * Returns the node's child corresponding to the passed path fragment.
     *
     * @param path Path fragment of the child node
     * @return The child node if present, null otherwise.
     */
    public ActionNode getChild(String path) {
        return this.children.get(path);
    }

    /**
     * Add a new node to children, corresponding to path fragment.
     * <p>
     * <p>This method will overwrite an existing child if the same path is added twice.</p>
     *
     * @param path Path fragment to add child to
     * @param node Child to be added
     */
    public void addChild(String path, ActionNode node) {
        children.put(path, node);
    }

    /**
     * Returns the path fragment this node is assigned to in the tree.
     *
     * @return The node's path fragment
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Sets the node's builder class.
     * <p>
     * <p>This method is required for being able to set the builder class of a dummy node. The only way to avoid having
     * this method would be to either implement the builder pattern before creating the tree, or enforcing the client
     * to add nodes in the correct order. The latter isn't feasible, but implementing a proper builder would be a solid
     * refactoring for the future.</p>
     *
     * @param method  HTTP method to assign class to
     * @param factory ServiceFactory for creating instances of rest actions
     */
    public void setBuilderClass(HttpMethod method, ServiceFactory factory) {
        this.factories.put(method, factory);
    }
}
