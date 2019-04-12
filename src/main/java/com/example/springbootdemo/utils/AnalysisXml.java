package com.example.springbootdemo.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnalysisXml {
    private Logger log = LoggerFactory.getLogger(AnalysisXml.class);
    /**
     * 解析XML格式转为map格式
     * @param request
     * @return
     */
    public static Map<String , String> xmlToMap(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        try {
            InputStream inputStream = null;
            inputStream = request.getInputStream();
            SAXReader reader = new SAXReader();
            Document doc = reader.read(inputStream);
            Element rootElement = doc.getRootElement();
            List<Element> elements = rootElement.elements();
            for (Element el : elements) {
                map.put(el.getName(), el.getText());
            }
            inputStream.close();
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
