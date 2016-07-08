package com.searchtechnologies.aspire.components;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.searchtechnologies.aspire.services.*;
import com.searchtechnologies.aspire.framework.*;

import junit.framework.TestCase;


public class TestAspireBZip2ExpanderStage extends TestCase {


  public void testAspireBZip2ExpanderStage() throws AspireException, IOException {
    System.setProperty(AspireApplication.ASPIRE_HOME_PROPERTY, "./testdata");
    
    Stage s = new AspireBZip2ExpanderStage();
    
    s.initialize(AXML.stringToDom(
        "<config>" +
        "</config>"
        ));

    AspireObject doc = new AspireObject(Standards.Basic.DOCUMENT_ROOT);

    Job j = JobFactory.newInstance(doc,"TEST-1");
    Standards.Basic.setContentStream(j, new FileInputStream("testdata/HelloWorld.txt.bz2"));

    s.process(j);

    BufferedReader r = new BufferedReader(new InputStreamReader(Standards.Basic.getContentAsStream(j, s)));
    assertEquals(r.readLine(),"Hello World!!");
    
    j.close();
  }
}
