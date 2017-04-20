package com.siemens.bt.jazz.services.base.test.router.tree;

import com.ibm.team.jfs.app.http.util.HttpConstants.HttpMethod;
import com.siemens.bt.jazz.services.base.test.rest.RestRequest;
import com.siemens.bt.jazz.services.base.test.router.factory.ServiceFactory;


/**
 * Tree for efficient REST routing.
 * <p>
 * <p>This problem has been solved multiple times, but for implementing a service inside a service, a bit of extra work
 * is required. JAX-RS can't be used because the underlying class loader can't be configured. Regex in older java
 * versions is fairly poor in terms of performance, as are the String methods, such as split. So instead, this Router
 * uses a multidimensional tree for routing REST paths. Performance doesn't degrade further than O(n log n) and the
 * space complexity is negligible for the current use case.</p>
 * <p>
 * <p>In further comments, "elements" describe single actions (such as a combination of GET /this/resource/ BuilderClass).
 * A node can have many elements, just as the tree can have many nodes. More precisely, every node's method collection
 * is a subtree consisting of a single root with only leaves.</p>
 */
public class ActionTree {
    private final ActionNode root;
    private final ServiceFactory defaultFactory;

    /**
     * Creates an ActionTree with a root node and a passed in root action.
     */
    public ActionTree(ServiceFactory factory) {
        this.defaultFactory = factory;
        root = new ActionNode(HttpMethod.GET, "", defaultFactory);
    }

    /**
     * Adds an element to the tree, overwriting a node if it already exists for the path.
     * <p>
     * <p>Adds a new element to the tree. If one doesn't exist, a new node is created, otherwise the new builder class is
     * attached to the existing node's corresponding method. If an element with the exact same method and path already
     * exists, it is overwritten.</p>
     *
     * @param method   HTTP method for which the element should be added
     * @param nodePath Full path to the node
     * @param factory  Factory for creating actions
     */
    public synchronized void addElement(HttpMethod method, String nodePath, ServiceFactory factory) {
        RestRequest restRequest = new RestRequest(method, nodePath);
        ActionNode currentNode = root;

        while (restRequest.hasNextpath()) {
            String path = restRequest.nextPath();

            // go to the next node, a path already exists that we can take.
            if (currentNode.hasChild(path)) {
                currentNode = currentNode.getChild(path);
                continue;
            }

            // the path goes on, but we don't have a node. We create a dummy node without an action to route on.
            if (restRequest.hasNextpath()) {
                ActionNode n = new ActionNode(path, defaultFactory);
                currentNode.addChild(path, n);
                currentNode = n;
                continue;
            }

            // the action that we want to assign belongs to the current node.
            if (currentNode.getPath().equals(path)) {
                currentNode.setBuilderClass(method, factory);
                continue;
            }

            // we are where we want to be, there are no more paths to take and we want a new child with an action.
            currentNode.addChild(path, new ActionNode(method, path, factory));
            return;
        }

        // TODO: This is never reached, so it's obviously wrong. However, I don't have the time right now to check.
        currentNode.setBuilderClass(method, factory);
    }

    /**
     * Retrieve an action builder at the given path.
     *
     * @param restRequest Request information containing the path at which the builder should be retrieved.
     * @return Action builder at the given path, or a default action builder if the given path doesn't exist
     */
    public ServiceFactory getFactory(RestRequest restRequest) {
        // TODO: This needs reworking badly...
        ActionNode currentNode = this.root;
        String path;

        // TODO: we need to loop on a different condition here, so that the restRequest stays sane for the called service.
        while (restRequest.hasNextpath()) {
            path = restRequest.nextPath();

            // we've found the action we want.
            if (currentNode.getPath().equals(path)) {
                return currentNode.getActionBuilder(restRequest.getMethod());
            }

            // we need to continue further down the path.
            if (currentNode.hasChild(path)) {
                currentNode = currentNode.getChild(path);
            }

            // we check if the child is a leaf. Don't even ask, but this is just how it's done at the moment.
            // clarification: actions that take parameters as part of the url and not part of a parameter list need to
            // be a leaf, this is a restriction that I might fix eventually.
            if (currentNode.hasChild(path) && currentNode.getChild(path).getPath().equals(path)) {
                return currentNode.getChild(path).getActionBuilder(restRequest.getMethod());
            }
        }

        // we always pop from the stack one too early, so before we return, we have to check if our last pop was the
        // actual target. This could probably be solved by using a nicer way to iterate the restRequest, but works for now.
        // If there are weird routing errors, this is most likely the place to have a look. This ensures that actions
        // which take /parameters/like/this are not routed anywhere. However, it also means that something like
        // /this/is/info will route to the info service, which is obviously wrong.
        if (currentNode != this.root) {
            return currentNode.getActionBuilder(restRequest.getMethod());
        }

        // if we reach this point, the action we're looking for doesn't exist.
        return defaultFactory;
    }
}
