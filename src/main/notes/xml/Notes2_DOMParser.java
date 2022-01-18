package main.notes.xml;

/*
 * DOM Parser ------------------------------------------------------------------
 * 
 * The Document Object Model (DOM) is an official recommendation of the World
 * Wide Web Consortium (W3C). It defines an interface that enables programs to
 * access and update the style, structure, and contents of XML documents. XML
 * parsers that support DOM implement this interface.
 * 
 * 
 * When to use a DOM parser ----------------------------------------------------
 * 
 * You need to know a lot about the structure of a document.
 * 
 * You need to move parts of an XML document around (you might want to sort
 * certain elements, for example).
 * 
 * You need to use the information in an XML document more than once.
 * 
 * 
 * What You Get? ---------------------------------------------------------------
 * 
 * When you parse an XML document with a DOM parser, you get back a tree
 * structure that contains all of the elements of your document.
 * 
 * 
 * Advantages ------------------------------------------------------------------
 * 
 * The DOM is a common interface for manipulating document structures. One of
 * its design goals is that Java code written for one DOM-compliant parser
 * should run on any other DOM-compliant parser without having to do any
 * modifications.
 * 
 * 
 * Common Java interfaces for DOM ----------------------------------------------
 * 
 * Node − The base datatype of the DOM.
 * 
 * Element − The vast majority of the objects you'll deal with are Elements.
 * 
 * Attr − Represents an attribute of an element.
 * 
 * Text − The actual content of an Element or Attr.
 * 
 * Document − Represents the entire XML document. A Document object is often
 * referred to as a DOM tree.
 * 
 * 
 * Common DOM methods ----------------------------------------------------------
 * 
 * Document.getDocumentElement() − Returns the root element of the document.
 * 
 * Node.getFirstChild() − Returns the first child of a given Node.
 * 
 * Node.getLastChild() − Returns the last child of a given Node.
 * 
 * Node.getNextSibling() − These methods return the next sibling of a given
 * Node.
 * 
 * Node.getPreviousSibling() − These methods return the previous sibling of a
 * given Node.
 * 
 * Node.getAttribute(attrName) − For a given Node, it returns the attribute with
 * the requested name.
 */

// Import XML-related libraries
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.*;

public class Notes2_DOMParser {

	public static void main(String[] args) {

		// Create a DocumentBuilder
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}

		// Create a Document from a stream (StringBuilder -> String -> byte[] ->
		// ByteArrayInputStream)
		StringBuilder xmlStreamSB = buildSB();
		String xmlStreamStr = xmlStreamSB.toString();
		byte[] xmlStreamBytes = null;
		try {
			xmlStreamBytes = xmlStreamStr.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e2) {
			e2.printStackTrace();
		}
		ByteArrayInputStream input = new ByteArrayInputStream(xmlStreamBytes);
		Document docFromStream = null;
		try {
			docFromStream = builder.parse(input);
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Extract the root element (this would be <Gw2Cast></Gw2Cast>
		Element rootFromStream = docFromStream.getDocumentElement();
		System.out.println("Element:" + rootFromStream);
		System.out.println("Element.getNodeName:" + rootFromStream.getNodeName());
		System.out.println("Element.getNamespaceURI:" + rootFromStream.getNamespaceURI());
		System.out.println("Element.getBaseURI:" + rootFromStream.getBaseURI());
		System.out.println("Element.getLocalName:" + rootFromStream.getLocalName());
		System.out.println("Element.getNodeType:" + rootFromStream.getNodeType());
		System.out.println("Element.getNodeValue:" + rootFromStream.getNodeValue());
		System.out.println("Element.getPrefiax:" + rootFromStream.getPrefix());
		System.out.println("Element.getTagName:" + rootFromStream.getTagName());
		System.out.println("Element.getTextContent:" + rootFromStream.getTextContent());
		System.out.println("Element.getFirstChild:" + rootFromStream.getFirstChild());
		System.out.println("Element.getLastChild:" + rootFromStream.getLastChild());
		System.out.println("Element.getNextSibling:" + rootFromStream.getNextSibling());
		System.out.println("Element.getPreviousSibling:" + rootFromStream.getPreviousSibling());
		System.out.println("Element.getAttribute(attrName):" + rootFromStream.getAttribute(""));

// Create a Document from a file

// Document docFromFile = null;
// File testXML = new File("/JavaDev/src/main/notes/xml/testXML.xml");
// try {
// docFromFile = builder.parse(testXML);
// } catch (SAXException e) {
// e.printStackTrace();
// } catch (IOException e) {
// e.printStackTrace();
// }
// 
// docFromFile.getDocumentElement().normalize();

// Element rootFromFile = docFromFile.getDocumentElement();

// System.out.println(rootFromFile);
// System.out.println(rootFromFile.getAttribute("Name"));

// System.out.println(rootFromFile);

// 
// /*
// * Examine attributes
// */
// //returns specific attribute
// getAttribute("attributeName");
//
// //returns a Map (table) of names/values
// getAttributes();
// Examine sub-elements
// //returns a list of subelements of specified name
// getElementsByTagName("subelementName");
//
// //returns a list of all child nodes
// getChildNodes();

	}

	private static StringBuilder buildSB() {
		StringBuilder xmlStringBuilder = new StringBuilder();
		xmlStringBuilder.append("<?xml version=\"1.0\"?>");
		xmlStringBuilder.append("<Gw2Cast>");
		xmlStringBuilder.append("    <Wow>First</Wow>");
		xmlStringBuilder.append("    <Generations>");
		xmlStringBuilder.append("        <Generation>");
		xmlStringBuilder.append("            <Name>Dragon Watch</Name>");
		xmlStringBuilder.append("            <Members>");
		xmlStringBuilder.append("                <Student>Rytlock</Student>");
		xmlStringBuilder.append("                <Student>Taimi</Student>");
		xmlStringBuilder.append("                <Student>Braham</Student>");
		xmlStringBuilder.append("            </Members>");
		xmlStringBuilder.append("        </Generation>");
		xmlStringBuilder.append("        <Generation>");
		xmlStringBuilder.append("            <Name>Destiny Edge</Name>");
		xmlStringBuilder.append("            <Members>");
		xmlStringBuilder.append("                <Student>Caithe</Student>");
		xmlStringBuilder.append("                <Student>Logan</Student>");
		xmlStringBuilder.append("            </Members>");
		xmlStringBuilder.append("        </Generation>");
		xmlStringBuilder.append("    </Generations>");
		xmlStringBuilder.append("</Gw2Cast>");
		return xmlStringBuilder;
	}
}