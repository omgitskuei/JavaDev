package main.notes.xml;

/**
 * @author i92008cc63
 * @category notes
 * @see Notes2_DOMParser
 * @since 2022/01/17
 */
public class Notes1_XMLIntro {
	/**
	 * What is XML?
	 * 
	 * XML is a simple text-based language which was designed to store and transport
	 * data in plain text format. It stands for Extensible Markup Language.
	 * Following are some of the salient features of XML.
	 * 
	 * XML is a markup language.
	 * 
	 * XML is a tag based language like HTML.
	 * 
	 * XML tags are not predefined like HTML.
	 * 
	 * You can define your own tags which is why it is called extensible language.
	 * 
	 * XML tags are designed to be self-descriptive.
	 * 
	 * XML is W3C Recommendation for data storage and data transfer.
	 */

	/**
	 * Example XML file
	 */
	// See testXML.xml

	/**
	 * Advantages
	 * 
	 * Technology agnostic − Being plain text, XML is technology independent. It can
	 * be used by any technology for data storage and data transfer purpose.
	 * 
	 * Human readable − XML uses simple text format. It is human readable and
	 * understandable.
	 * 
	 * Extensible − In XML, custom tags can be created and used very easily.
	 * 
	 * Allow Validation − Using XSD, DTD and XML structures can be validated easily.
	 * 
	 * 
	 * Disadvantages
	 * 
	 * Redundant Syntax − Normally XML files contain a lot of repetitive terms.
	 * 
	 * Verbose − Being a verbose language, XML file size increases the transmission
	 * and storage costs.
	 */

	/**
	 * Java XML Parsers
	 * 
	 * XML Parsing refers to going through an XML document in order to access or
	 * modify data.
	 * 
	 * Java provides multiple options to parse XML documents.
	 * 
	 * Dom Parser − Parses an XML document by loading the complete contents of the
	 * document and creating its complete hierarchical tree in memory.
	 * 
	 * SAX Parser − Parses an XML document on event-based triggers. Does not load
	 * the complete document into the memory.
	 * 
	 * JDOM Parser − Parses an XML document in a similar fashion to DOM parser but
	 * in an easier way.
	 * 
	 * StAX Parser − Parses an XML document in a similar fashion to SAX parser but
	 * in a more efficient way.
	 * 
	 * XPath Parser − Parses an XML document based on expression and is used
	 * extensively in conjunction with XSLT.
	 * 
	 * DOM4J Parser − A java library to parse XML, XPath, and XSLT using Java
	 * Collections Framework. It provides support for DOM, SAX, and JAXP.
	 * 
	 * JAXB and XSLT APIs - handle XML parsing in object-oriented way.
	 */

}
