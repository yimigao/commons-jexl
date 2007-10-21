/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.commons.jexl.parser;

import org.apache.commons.jexl.JexlContext;
import org.apache.commons.jexl.util.introspection.Uberspect;

/**
 * A Useful implementation of {@link Node}. Mostly autogenerated by javacc
 *
 * @author <a href="mailto:geirm@apache.org">Geir Magnusson Jr.</a>
 * @version $Id$
 */
public class SimpleNode implements Node {
    /** parent node. */
    protected Node parent;

    /** children of this node. */
    protected Node[] children;

    /** id of the node. */
    protected int id;

    /** parser that created the node. */
    protected Parser parser;

    /**
     * Create the node given an id.
     *
     * @param i node id.
     */
    public SimpleNode(int i) {
        id = i;
    }

    /**
     * Create a node with the given parser and id.
     *
     * @param p a parser.
     * @param i node id.
     */
    public SimpleNode(Parser p, int i) {
        this(i);
        parser = p;
    }

    /**
     * Start of the node.
     */
    public void jjtOpen() {
    }

    /**
     * End of the node.
     */
    public void jjtClose() {
    }

    /** {@inheritDoc} */
    public void jjtSetParent(Node n) {
        parent = n;
    }

    /** {@inheritDoc} */
    public Node jjtGetParent() {
        return parent;
    }

    /** {@inheritDoc} */
    public void jjtAddChild(Node n, int i) {
        if (children == null) {
            children = new Node[i + 1];
        } else if (i >= children.length) {
            Node[] c = new Node[i + 1];
            System.arraycopy(children, 0, c, 0, children.length);
            children = c;
        }

        children[i] = n;
    }

    /** {@inheritDoc} */
    public Node jjtGetChild(int i) {
        return children[i];
    }

    /** {@inheritDoc} */
    public int jjtGetNumChildren() {
        return (children == null) ? 0 : children.length;
    }

    /**
     * Accept the visitor.
     *
     * @param visitor a {@link ParserVisitor}.
     * @param data data to be passed along to the visitor.
     * @return the value from visiting.
     * @see ParserVisitor#visit
     */
    public Object jjtAccept(ParserVisitor visitor, Object data) {
        return visitor.visit(this, data);
    }

    /**
     * Visit all children.
     *
     * @param visitor a {@link ParserVisitor}.
     * @param data data to be passed along to the visitor.
     * @return the value from visiting.
     * @see ParserVisitor#visit
     */
    public Object childrenAccept(ParserVisitor visitor, Object data) {
        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                children[i].jjtAccept(visitor, data);
            }
        }
        return data;
    }

    /**
     * Gets a string representation of the node.
     * @return the node name.
     */
    public String toString() {
        return ParserTreeConstants.jjtNodeName[id];
    }

    /**
     * Used during dumping to output the node with a prefix.
     * @param prefix text to prefix {@link #toString()}
     * @return text.
     */
    public String toString(String prefix) {
        return prefix + toString();
    }

    /**
     * Dump the node and all children.
     * @param prefix text to prefix the node output.
     */
    public void dump(String prefix) {
        System.out.println(toString(prefix));

        if (children != null) {
            for (int i = 0; i < children.length; ++i) {
                SimpleNode n = (SimpleNode) children[i];

                if (n != null) {
                    n.dump(prefix + " ");
                }
            }
        }
    }

    /**
     * basic interpret - just invoke interpret on all children.
     * @param pc the {@link JexlContext context} to interpret against.
     * @return true if interpretation worked.
     * @throws Exception on any error.
     */
    public boolean interpret(JexlContext pc) throws Exception {
        for (int i = 0; i < jjtGetNumChildren(); i++) {
            SimpleNode node = (SimpleNode) jjtGetChild(i);
            if (!node.interpret(pc)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the value of this node.
     *
     * @param context the context to retrieve values from.
     * @return the value of the node.
     * @throws Exception when evaluating the operands fails.
     */
    public Object value(JexlContext context) throws Exception {
        return null;
    }

    /**
     * Sets the value for the node - again, only makes sense for some nodes but
     * lazyness tempts me to put it here. Keeps things simple.
     *
     * @param context the context to retrieve values from.
     * @param value the value.
     * @return the result.
     * @throws Exception when evaluating the operands fails.
     */
    public Object setValue(JexlContext context, Object value) throws Exception {
        return null;
    }

    /**
     * Used to let a node calcuate it's value..
     * @param o the object to calculate with.
     * @param ctx the context to retrieve values from.
     * @throws Exception when calculating the value fails.
     * @return the result of the calculation.
     */
    public Object execute(Object o, JexlContext ctx) throws Exception {
        return null;
    }

    protected Uberspect getUberspect() {
        return parser.getUberspect();
    }
}
