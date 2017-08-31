package com.texasbruce.commons.utils;

import java.util.HashMap;
import java.util.Vector;

import javax.xml.transform.TransformerException;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtils {

    public static HashMap<String, Vector<Node>> convertNodeToHash(Node node) throws Exception {
        if (node == null) {
            return null;
        }
        
        HashMap<String, Vector<Node>> nameValueVectorHash = new HashMap<String, Vector<Node>>();

        NodeList nameNodes = org.apache.xpath.XPathAPI.selectNodeList(node, ".//*");

        String name;
        Node nameValueNode;

        Vector<Node> valueVector;
        for (int i = 0; i < nameNodes.getLength(); i++) {
            nameValueNode = nameNodes.item(i);
            name = nameValueNode.getNodeName();

            if (nameValueVectorHash.get(name) == null) {
                nameValueVectorHash.put(name, new Vector<Node>());
            }

            valueVector = nameValueVectorHash.get(name);
            valueVector.add(nameValueNode);

        }

        return nameValueVectorHash;
    }

    public static String getNodeValue(Node node) {
        if (node == null) {
            return null;
        }
        
        NodeList nodes = node.getChildNodes();
        for (int i = 0; i < nodes.getLength(); i++) {
            Node n = nodes.item(i);
            if (n.getNodeType() == Node.CDATA_SECTION_NODE) {
                return n.getNodeValue();
            }
            else if (n.getNodeType() == Node.TEXT_NODE) {
                return n.getTextContent();
            }
        }
        return node.getTextContent();
    }
    
    public static String getTagValueByTagNameFromNode(Node node, String tagName){
        Node resultNode = null;
        try {
            resultNode = org.apache.xpath.XPathAPI.selectSingleNode(node, tagName);
        }
        catch (TransformerException e) { }
        return getNodeValue(resultNode);
    }
    

    public static String getTagValueByTagNameFromString(String xml, String tagName) {

        if (xml == null || tagName == null) {
            return null;
        }

        String openTag = "<" + tagName;
        String closeTag = "</" + tagName;

        int openTagOpenBracketIndex = xml.indexOf(openTag);

        if (openTagOpenBracketIndex >= 0) {
            int openTagCloseBracketIndex = xml.indexOf(">", openTagOpenBracketIndex);
            int tagValueIndex = openTagCloseBracketIndex + 1;

            int closeIndex = xml.indexOf(closeTag, tagValueIndex);

            if (closeIndex >= 0) {
                return xml.substring(tagValueIndex, closeIndex);
            }
        }

        return null;

    }

}
