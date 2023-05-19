package com.example.laser.service;

import org.apache.batik.anim.dom.SAXSVGDocumentFactory;
import org.apache.batik.anim.dom.SVGOMPathElement;
import org.apache.batik.bridge.BridgeContext;
import org.apache.batik.bridge.DocumentLoader;
import org.apache.batik.bridge.GVTBuilder;
import org.apache.batik.bridge.UserAgentAdapter;
import org.apache.batik.util.XMLResourceDescriptor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.io.IOException;

@Component
public class ImageLengthCalculator {
    public double calculateLength(MultipartFile file) throws IOException {
        // Читать SVG изображение из MultipartFile
        String parser = XMLResourceDescriptor.getXMLParserClassName();
        SAXSVGDocumentFactory factory = new SAXSVGDocumentFactory(parser);
        Document document = factory.createDocument(null, file.getInputStream());

        // Создать GVTBuilder и BridgeContext
        UserAgentAdapter userAgentAdapter = new UserAgentAdapter();
        DocumentLoader documentLoader = new DocumentLoader(userAgentAdapter);
        BridgeContext bridgeContext = new BridgeContext(userAgentAdapter, documentLoader);
        GVTBuilder builder = new GVTBuilder();
        builder.build(bridgeContext, document);

        // Рассчитать длину контура с помощью библиотеки Batik
        NodeList pathNodes = document.getElementsByTagName("path");
        double totalLength = 0;
        for (int i = 0; i < pathNodes.getLength(); i++) {
            SVGOMPathElement pathElement = (SVGOMPathElement) pathNodes.item(i);
            totalLength += pathElement.getTotalLength();
        }

        return totalLength;
    }
}
